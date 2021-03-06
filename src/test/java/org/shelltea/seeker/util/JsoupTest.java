/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.util;

import com.google.common.base.Charsets;
import com.google.common.base.Stopwatch;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
public class JsoupTest {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testHttpClient() throws IOException {
        Stopwatch stopwatch = Stopwatch.createStarted();

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse httpResponse = httpClient.execute(new HttpGet("http://www.cnbeta.com/"));
        String html = EntityUtils.toString(httpResponse.getEntity(), Charsets.UTF_8);

        Document doc = Jsoup.parse(html);
        Elements links = doc.select("#allnews_all dt > a");

        for (Element link : links) {
            logger.info("{}:{}", link.html(), link.attr("href"));
        }

        logger.debug("HttpClient:{}", stopwatch.toString());
    }

    @Test
    public void testJsoup() throws IOException {
        Stopwatch stopwatch = Stopwatch.createStarted();

        Document doc = Jsoup.connect("http://www.cnbeta.com/").get();
        Elements links = doc.select("#allnews_all dt > a");

        for (Element link : links) {
            logger.info("{}:{}", link.html(), link.attr("href"));
        }

        logger.debug("Jsoup:{}", stopwatch.toString());
    }
}
