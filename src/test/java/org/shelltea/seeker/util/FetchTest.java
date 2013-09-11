/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.util;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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
		Stopwatch stopwatch = new Stopwatch().start();

		String url = "http://www.cnbeta.com";

		Document doc = Jsoup.connect(url).userAgent(FetchService.USER_AGENT).get();
		Elements links = doc.select("#allnews_all dt > a");

		for (Element link : links) {
			logger.info("{}:{}{}", link.html(), url, link.attr("href"));
		}

		Document pageDoc = Jsoup.connect(url + links.get(0).attr("href")).userAgent(FetchService.USER_AGENT).get();

		Elements title = pageDoc.select("#news_title");
		logger.info("{}", title.get(0).html());

		Elements content = pageDoc.select(".content > .content");
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
	public void fetchOschinaTest() throws IOException {
		Stopwatch stopwatch = new Stopwatch().start();

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
}
