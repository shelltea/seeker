/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.repository;

import org.apache.commons.lang.math.RandomUtils;
import org.junit.Test;
import org.shelltea.seeker.entity.Channel;
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
public class ChannelRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ChannelRepository channelRepository;

	@Test
	public void testSave() {
		String techChannelString = "新闻";

		Channel techChannel = channelRepository.findByTitle(techChannelString);

		if (techChannel == null) {
			techChannel = new Channel();
			techChannel.setTitle(techChannelString);
			techChannel.setIconUrl("icon_" + RandomUtils.nextInt(1000));
			techChannel.setDescription("");
			channelRepository.save(techChannel);
		}

		logger.debug("{}", techChannel);
	}
}
