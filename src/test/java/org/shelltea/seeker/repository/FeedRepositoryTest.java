/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.repository;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.shelltea.seeker.entity.Channel;
import org.shelltea.seeker.entity.Feed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
@ContextConfiguration(locations = { "/spring/applicationContext.xml" })
@TransactionConfiguration(defaultRollback = false)
public class FeedRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private FeedRepository feedRepository;
	@Autowired
	private ChannelRepository channelRepository;

	@Before
	public void createChannel() {
		String techChannel = "科技";

		Channel channel = channelRepository.findByTitle(techChannel);

		if (channel == null) {
			channel = new Channel();
			channel.setTitle(techChannel);
			channel.setIconUrl("");
			channel.setDescription("");
			channelRepository.save(channel);
		}

		logger.debug("{}", channel);

		// deleteFeed();
	}

	// @After
	public void deleteFeed() {
		Feed cnbetaFeed = feedRepository.findByTitle("cnBeta");

		if (cnbetaFeed != null) {
			feedRepository.delete(cnbetaFeed);
		}
	}

	@Test
	public void saveCnbetaFeed() {
		Feed cnbetaFeed = new Feed();
		cnbetaFeed.setTitle("cnBeta");
		cnbetaFeed.setUrl("http://www.cnbeta.com");
		cnbetaFeed.setFaviconUrl("http://www.cnbeta.com/favicon.ico");
		cnbetaFeed.setFetchUrl("http://www.cnbeta.com");
		cnbetaFeed.setEntryUrlPrefix("http://www.cnbeta.com");
		cnbetaFeed.setListSelector("#allnews_all dt > a");
		cnbetaFeed.setTitleSelector("#news_title");
		cnbetaFeed.setOriginContentSelector(".content > .content");
		cnbetaFeed.setPublishedTimeSelector(".date");
		cnbetaFeed.setPublishedTimePattern("yyyy-MM-dd HH:mm:ss");
		cnbetaFeed.setAuthorSelector(".where > a");
		cnbetaFeed.setLastFetchTime(new Date(System.currentTimeMillis()));
		feedRepository.save(cnbetaFeed);

		Channel techChannel = channelRepository.findByTitle("科技");
		techChannel.getFeeds().add(cnbetaFeed);
		channelRepository.save(techChannel);
	}

	@Test
	public void saveIfengaFeed() {
		Feed ifengFeed = new Feed();
		ifengFeed.setTitle("iFeng");
		ifengFeed.setUrl("http://www.ifeng.com/");
		ifengFeed.setFaviconUrl("http://y0.ifengimg.com/index/favicon.ico");
		ifengFeed.setFetchUrl("http://news.ifeng.com/rt-channel/rtlist_0");
		ifengFeed.setEntryUrlPrefix("");
		ifengFeed.setListSelector(".newsList ul a");
		ifengFeed.setTitleSelector("#artical_topic");
		ifengFeed.setOriginContentSelector("#main_content");
		ifengFeed.setPublishedTimeSelector("#artical_sth > p > span");
		ifengFeed.setPublishedTimePattern("yyyy年MM月dd HH:mm");
		ifengFeed.setLastFetchTime(new Date(System.currentTimeMillis()));
		feedRepository.save(ifengFeed);

		Channel newsChannel = channelRepository.findByTitle("新闻");
		newsChannel.getFeeds().add(ifengFeed);
		channelRepository.save(newsChannel);
	}

	@Test
	public void saveOschinaFeed() {
		Feed oschinaFeed = new Feed();
		oschinaFeed.setTitle("oschina");
		oschinaFeed.setUrl("http://www.oschina.net");
		oschinaFeed.setFaviconUrl("http://www.oschina.net/img/favicon.ico");
		oschinaFeed.setFetchUrl("http://www.oschina.net/news/list?show=industry");
		oschinaFeed.setEntryUrlPrefix("http://www.oschina.net");
		oschinaFeed.setListSelector(".List > li > h2 > a");
		oschinaFeed.setTitleSelector(".OSCTitle");
		oschinaFeed.setOriginContentSelector(".NewsContent");
		oschinaFeed.setAuthorSelector(".PubDate > a");
		oschinaFeed.setLastFetchTime(new Date(System.currentTimeMillis()));
		feedRepository.save(oschinaFeed);

		Channel techChannel = channelRepository.findByTitle("科技");
		techChannel.getFeeds().add(oschinaFeed);
		channelRepository.save(techChannel);
	}
}
