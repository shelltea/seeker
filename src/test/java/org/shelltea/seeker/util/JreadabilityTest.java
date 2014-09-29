/**
 * 
 */
package org.shelltea.seeker.util;

import java.io.IOException;
import java.text.ParseException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.shelltea.seeker.service.FetchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Stopwatch;
import com.wuman.jreadability.Readability;

/**
 * @author Xiong Shuhong(xiongsh@youyuan.com)
 */
public class JreadabilityTest {
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

		Document pageDoc = Jsoup.connect(url + links.get(3).attr("href")).userAgent(FetchService.USER_AGENT).get();

		Readability readability = new Readability(pageDoc.html()) {
			@Override
			protected void dbg(String msg) {
				logger.debug("{}", msg);
			}

			@Override
			protected void dbg(String msg, Throwable t) {
				logger.debug("{}", msg, t);
			}
		};

		readability.init();
		logger.debug("html:{}", readability.html());

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

		Document pageDoc = Jsoup.connect(entryUrlPrefix + links.get(0).attr("href")).userAgent(FetchService.USER_AGENT).get();

		Readability readability = new Readability(pageDoc.html()) {
			@Override
			protected void dbg(String msg) {
				logger.debug("{}", msg);
			}

			@Override
			protected void dbg(String msg, Throwable t) {
				logger.debug("{}", msg, t);
			}
		};

		readability.init();
		logger.debug("html:{}", readability.html());
		logger.debug("html:{}", readability.outerHtml());

		logger.debug("Jsoup:{}", stopwatch.toString());
	}
}
