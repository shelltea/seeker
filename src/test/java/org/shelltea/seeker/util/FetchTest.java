/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.shelltea.seeker.service.FetchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Stopwatch;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
public class FetchTest {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	public void fetchCnbetaTest() throws IOException {
		Stopwatch stopwatch = Stopwatch.createStarted();

		String url = "http://www.cnbeta.com";

		Document doc = Jsoup.connect(url).userAgent(FetchService.USER_AGENT).get();
		Elements links = doc.select("#allnews_all dt > a");

		for (Element link : links) {
			logger.info("{}:{}{}", link.html(), url, link.attr("href"));
		}

		Document pageDoc = Jsoup.connect(url + links.get(0).attr("href")).userAgent(FetchService.USER_AGENT).get();

		Elements title = pageDoc.select("#news_title");
		logger.info("{}", title.get(0).html());

		Elements content = pageDoc.select(".content");
		Elements imgs = pageDoc.select(".content > .introduction > div").remove();
		logger.info("{}", imgs.get(0).html());
		logger.info("{}", content.get(0).html());

		Elements date = pageDoc.select(".date");
		logger.info("{}", date.get(0).html());

		Elements author = pageDoc.select(".where > a");
		logger.info("{}", author.get(0).html());

		Elements notFound = pageDoc.select("#not_found");
		logger.info("{}", notFound.get(0).html());

		logger.debug("Jsoup:{}", stopwatch.toString());
	}

	@Test
	public void fetchIfengTest() throws IOException, ParseException {
		Stopwatch stopwatch = Stopwatch.createStarted();

		String fetchUrl = "http://news.ifeng.com/rt-channel/rtlist_0/";
		String entryUrlPrefix = "";

		Document doc = Jsoup.connect(fetchUrl).userAgent(FetchService.USER_AGENT).get();
		Elements links = doc.select(".newsList ul a");

		for (Element link : links) {
			logger.info("{}:{}{}", link.html(), entryUrlPrefix, link.attr("href"));
		}

		Document pageDoc = Jsoup.connect(entryUrlPrefix + links.get(0).attr("href")).userAgent(FetchService.USER_AGENT)
				.get();

		Elements title = pageDoc.select("#artical_topic");
		logger.info("{}", title.get(0).html());

		Elements content = pageDoc.select("#main_content");
		logger.info("{}", content.get(0).html());

		Elements date = pageDoc.select("#artical_sth > p > span");
		logger.info("{}", date.get(0).html(), new SimpleDateFormat("yyyy年MM月dd日 HH:mm").parse(date.get(0).html()));

		logger.debug("Jsoup:{}", stopwatch.toString());
	}

	@Test
	public void fetchOschinaTest() throws IOException {
		Stopwatch stopwatch = Stopwatch.createStarted();

		String fetchUrl = "http://www.oschina.net/news/list?show=industry";
		String entryUrlPrefix = "http://www.oschina.net";

		Document doc = Jsoup.connect(fetchUrl).userAgent(FetchService.USER_AGENT).get();
		Elements links = doc.select(".List > li > h2 > a");

		for (Element link : links) {
			logger.info("{}:{}{}", link.html(), entryUrlPrefix, link.attr("href"));
		}

		Document pageDoc = Jsoup.connect(entryUrlPrefix + links.get(0).attr("href")).userAgent(FetchService.USER_AGENT)
				.get();

		Elements title = pageDoc.select(".OSCTitle");
		logger.info("{}", title.get(0).html());

		Elements content = pageDoc.select(".NewsContent");
		logger.info("{}", content.get(0).html());

		Elements author = pageDoc.select(".PubDate > a");
		logger.info("{}", author.get(0).html());

		logger.debug("Jsoup:{}", stopwatch.toString());
	}

	@Test
	public void imgPathFilterTest() throws IOException {
		Document pageDoc = Jsoup.connect("http://www.oschina.net/news/44548/are-we-witnessing-the-decline-of-ubuntu")
				.userAgent(FetchService.USER_AGENT).get();

		Elements title = pageDoc.select(".OSCTitle");
		if (title.size() > 0) {
			logger.info("{}", title.get(0).html());
		}

		Elements content = pageDoc.select(".NewsContent");

		String cleanHtml = Jsoup.clean(content.get(0).html(), "http://www.oschina.net/", Whitelist.relaxed());
		logger.info("{}", cleanHtml);
	}

	@Test
	public void imgPathTest() throws IOException {
		Stopwatch stopwatch = Stopwatch.createStarted();

		String fetchUrl = "http://www.oschina.net/news/list?show=industry";
		String entryUrlPrefix = "http://www.oschina.net";

		Document doc = Jsoup.connect(fetchUrl).userAgent(FetchService.USER_AGENT).get();
		Elements links = doc.select(".List > li > h2 > a");

		for (Element link : links) {
			if (link.attr("href").startsWith("http://")) {
				continue;
			}
			Document pageDoc = Jsoup.connect(entryUrlPrefix + link.attr("href")).userAgent(FetchService.USER_AGENT)
					.get();

			Elements title = pageDoc.select(".OSCTitle");
			if (title.size() > 0) {
				logger.info("{}", title.get(0).html());
			}

			Elements content = pageDoc.select(".NewsContent");

			if (content.size() > 0) {
				Elements images = content.get(0).select("img");

				for (Element image : images) {
					logger.info("{}:{}", image, image.attr("src"));
				}
			}
		}

		logger.debug("Jsoup:{}", stopwatch.toString());
	}
}
