/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.shelltea.seeker.entity.Entry;
import org.shelltea.seeker.entity.Feed;
import org.shelltea.seeker.repository.EntryRepository;
import org.shelltea.seeker.repository.FeedRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
@Service
public class FetchService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	private static final int INTERVAL_MILLISECOND = 500;
	private static final int TIMEOUT_MILLISECOND = 10000;
	public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.28 Safari/537.36";

	@Autowired
	private FeedRepository feedRepository;
	@Autowired
	private EntryRepository entryRepository;

	public void fetchFeed(Long feedId) {
		// 验证
		Preconditions.checkNotNull(feedId);

		Feed fetchFeed = feedRepository.findOne(feedId);

		if (fetchFeed == null) {
			return;
		}

		Stopwatch stopwatch = new Stopwatch().start();
		logger.info("begin to fetch feed:{}", fetchFeed.getTitle());

		// 抓取列表页
		Document listDocument = null;
		try {
			listDocument = Jsoup.connect(fetchFeed.getFetchUrl()).userAgent(USER_AGENT).timeout(TIMEOUT_MILLISECOND)
					.get();
		} catch (IOException e) {
			logger.warn("fetching feed failure:{},{}", fetchFeed.getTitle(), e);
			return;
		}
		Elements links = listDocument.select(fetchFeed.getListSelector());

		// 抓取文章页
		for (Element link : Lists.reverse(links)) {
			// 处理路径可能出现的问题
			String entryAbsolutePath = getAbsolutePath(fetchFeed, link);

			// 根据url查询Entry
			Entry entry = entryRepository.findByUrl(entryAbsolutePath);

			// 当前时间
			Date now = new Date(System.currentTimeMillis());

			// 当Entry不存在时抓取页面
			if (entry == null) {
				logger.debug("begin to fetch entry:{}", entryAbsolutePath);

				Map<String, String> entryMap = fetchEntry(fetchFeed, entryAbsolutePath);

				// 如果抓取失败或者文章内容为空，则跳过
				if (entryMap == null || Strings.isNullOrEmpty(entryMap.get("originContent"))) {
					logger.warn("skip save entry:{}", entryAbsolutePath);
					continue;
				}

				// 处理内容
				String title = entryMap.get("title") == null ? link.html() : entryMap.get("title");
				String author = entryMap.get("author") == null ? fetchFeed.getTitle() : entryMap.get("author");
				Date publishedTime = now;
				try {
					publishedTime = entryMap.get("publishedTime") == null ? now : new SimpleDateFormat(
							fetchFeed.getPublishedTimePattern()).parse(entryMap.get("publishedTime"));
				} catch (ParseException e) {
				}
				String originContent = entryMap.get("originContent");

				// 保存或更新文章
				if (entry == null) {
					entry = new Entry();
					entry.setFeedId(feedId);
					entry.setUrl(entryAbsolutePath);
					entry.setPublishedTime(publishedTime);
				}

				entry.setTitle(title);
				entry.setAuthor(author);
				entry.setOriginContent(originContent);
				entry.setLastFetchTime(now);

				// TODO 清理内容
				entry.setContent(originContent);

				entryRepository.save(entry);
				logger.debug("save entry:{}", entryAbsolutePath);
			} else {
				logger.debug("already fetching entry:{}", entryAbsolutePath);
			}
		}

		fetchFeed.setLastFetchTime(new Date(System.currentTimeMillis()));
		feedRepository.save(fetchFeed);
		logger.info("fetching feed completed:{},{}", fetchFeed.getTitle(), stopwatch.toString());
	}

	/**
	 * 抓取文章条目.
	 * 
	 * @param fetchFeed
	 * @param entryAbsolutePath
	 * @return
	 */
	private Map<String, String> fetchEntry(Feed fetchFeed, String entryAbsolutePath) {
		try {
			Thread.sleep(INTERVAL_MILLISECOND);

			Document pageDocument = Jsoup.connect(entryAbsolutePath).userAgent(USER_AGENT).get();

			Map<String, String> entryMap = Maps.newHashMap();
			entryMap.put("title", getData(pageDocument, fetchFeed.getTitleSelector()));
			entryMap.put("author", getData(pageDocument, fetchFeed.getAuthorSelector()));
			entryMap.put("publishedTime", getData(pageDocument, fetchFeed.getPublishedTimeSelector()));
			entryMap.put("originContent", getData(pageDocument, fetchFeed.getOriginContentSelector()));

			logger.debug("fetching entry completed:{}", entryAbsolutePath);
			return entryMap;
		} catch (IOException | InterruptedException e) {
			logger.warn("fetching entry failure:{},{}", entryAbsolutePath, e);
			return null;
		}
	}

	/**
	 * 获取绝对路径.
	 * 
	 * @param fetchFeed
	 * @param link
	 * @return
	 */
	private String getAbsolutePath(Feed fetchFeed, Element link) {
		if (Strings.isNullOrEmpty(fetchFeed.getEntryUrlPrefix())) {
			return link.attr("href");
		}

		String entryRelativePath = link.attr("href");
		if (entryRelativePath.charAt(0) != '/') {
			entryRelativePath = '/' + entryRelativePath;
		}

		return fetchFeed.getEntryUrlPrefix() + entryRelativePath;
	}

	/**
	 * 根据选择器获取对应内容.
	 * 
	 * @param document
	 * @param selector
	 * @return
	 */
	private String getData(Document document, String selector) {
		if (selector == null) {
			return null;
		}

		Elements elements = document.select(selector);

		if (elements.size() == 1) {
			return elements.get(0).html();
		} else {
			return null;
		}
	}
}
