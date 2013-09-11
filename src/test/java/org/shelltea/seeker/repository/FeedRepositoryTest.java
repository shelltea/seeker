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

		deleteFeed();
	}

	// @After
	public void deleteFeed() {
		Feed cnbetaFeed = feedRepository.findByTitle("cnBeta");

		if (cnbetaFeed != null) {
			feedRepository.delete(cnbetaFeed);
		}
	}

	@Test
	public void saveFeed() {
		Feed cnbetaFeed = new Feed();
		cnbetaFeed.setTitle("cnBeta");
		cnbetaFeed.setUrl("http://www.cnbeta.com");
		cnbetaFeed.setFetchUrl("http://www.cnbeta.com");
		cnbetaFeed.setListSelector("#allnews_all dt > a");
		cnbetaFeed.setTitleSelector("#news_title");
		cnbetaFeed.setOriginContentSelector(".content > .content");
		cnbetaFeed.setPublishedTimeSelector(".date");
		cnbetaFeed.setPublishedTimePattern("yyyy-MM-dd hh:mm:ss");
		cnbetaFeed.setAuthorSelector(".where > a");
		cnbetaFeed.setLastFetchTime(new Date(System.currentTimeMillis()));
		feedRepository.save(cnbetaFeed);

		Channel techChannel = channelRepository.findByTitle("科技");
		techChannel.getFeeds().add(cnbetaFeed);
		channelRepository.save(techChannel);
	}
}
