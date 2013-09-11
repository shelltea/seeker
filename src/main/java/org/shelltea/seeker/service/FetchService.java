/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
@Service
public class FetchService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private FeedRepository feedRepository;
	@Autowired
	private EntryRepository entryRepository;

	public void fetch(Long feedId) {
		Preconditions.checkNotNull(feedId);

		Feed fetchFeed = feedRepository.findOne(feedId);

		if (fetchFeed == null) {
			return;
		}

		Stopwatch stopwatch = new Stopwatch().start();
		logger.info("begin to fetch:{}", fetchFeed.getTitle());

		// 抓取列表页
		Document listDocument = null;
		try {
			listDocument = Jsoup.connect(fetchFeed.getFetchUrl()).get();
		} catch (IOException e) {
			logger.warn("fetching {} failure", fetchFeed.getTitle());
			return;
		}

		Elements links = listDocument.select(fetchFeed.getListSelector());

		for (Element link : links) {
			// 处理路径可能出现的问题
			String entryRelativePath = link.attr("href");
			if (entryRelativePath.charAt(0) != '/') {
				entryRelativePath = '/' + entryRelativePath;
			}

			String entryAbsolutePath = fetchFeed.getFetchUrl() + entryRelativePath;

			logger.debug("begin to fetch entry:{}", entryAbsolutePath);

			// 根据url查询Entry
			Entry entry = entryRepository.findByUrl(fetchFeed.getFetchUrl() + link.attr("href"));

			// 判断Entry是否已经存在，如果上次抓取时间和本次抓取时间间隔15分钟，将重新抓取
			Date now = new Date(System.currentTimeMillis());
			if (entry != null) {
				if (now.getTime() - entry.getLastFetchTime().getTime() >= 15 * 60 * 1000) {
					// 抓取单个页面
					Document pageDocument;
					try {
						Thread.sleep(500);

						pageDocument = Jsoup.connect(entryAbsolutePath).get();

						// 获取页面数据
						String title = getData(pageDocument, fetchFeed.getTitleSelector());
						String author = getData(pageDocument, fetchFeed.getAuthorSelector());
						String publishedTime = getData(pageDocument, fetchFeed.getPublishedTimeSelector());
						String originContent = getData(pageDocument, fetchFeed.getOriginContentSelector());

						// 创建文章条目并保存
						entry.setLastFetchTime(now);

						entry.setTitle(title == null ? link.html() : title);
						entry.setAuthor(author == null ? fetchFeed.getTitle() : author);
						entry.setPublishedTime(publishedTime == null ? now : new SimpleDateFormat(fetchFeed
								.getPublishedTimePattern()).parse(publishedTime));
						entry.setOriginContent(originContent);

						// TODO 清理内容
						entry.setContent(originContent);

						entryRepository.save(entry);
					} catch (IOException | ParseException e) {
						logger.warn("fetching entry {} failure", entryAbsolutePath);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					logger.info("have been fetching {} in 15 minutes, skip this time", entryAbsolutePath);
				}
			} else {
				// 抓取单个页面
				Document pageDocument;
				try {
					Thread.sleep(500);

					pageDocument = Jsoup.connect(entryAbsolutePath).get();

					// 获取页面数据
					String title = getData(pageDocument, fetchFeed.getTitleSelector());
					String author = getData(pageDocument, fetchFeed.getAuthorSelector());
					String publishedTime = getData(pageDocument, fetchFeed.getPublishedTimeSelector());
					String originContent = getData(pageDocument, fetchFeed.getOriginContentSelector());

					// 创建文章条目并保存
					entry = new Entry();
					entry.setFeedId(feedId);
					entry.setUrl(entryAbsolutePath);
					entry.setLastFetchTime(now);

					entry.setTitle(title == null ? link.html() : title);
					entry.setAuthor(author == null ? fetchFeed.getTitle() : author);
					entry.setPublishedTime(publishedTime == null ? now : new SimpleDateFormat(fetchFeed
							.getPublishedTimePattern()).parse(publishedTime));
					entry.setOriginContent(originContent);

					// TODO 清理内容
					entry.setContent(originContent);

					entryRepository.save(entry);
				} catch (IOException | ParseException e) {
					logger.warn("fetching entry {} failure", entryAbsolutePath);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			logger.debug("fetching {} completed", entryAbsolutePath);
		}

		fetchFeed.setLastFetchTime(new Date(System.currentTimeMillis()));
		feedRepository.save(fetchFeed);

		logger.info("fetching {} completed:{}", fetchFeed.getTitle(), stopwatch.toString());
	}

	public String getData(Document document, String selector) {
		Elements elements = document.select(selector);

		if (elements.size() == 1) {
			return elements.get(0).html();
		} else {
			return null;
		}
	}
}
