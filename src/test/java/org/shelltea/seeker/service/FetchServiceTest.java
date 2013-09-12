/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.service;

import org.junit.Test;
import org.shelltea.seeker.entity.Feed;
import org.shelltea.seeker.repository.FeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
@ContextConfiguration(locations = { "/spring/applicationContext.xml" })
@TransactionConfiguration(defaultRollback = false)
public class FetchServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	private FetchService fetchService;
	@Autowired
	private FeedRepository feedRepository;

	@Test
	public void fetch() {
		Feed cnbeta = feedRepository.findByTitle("cnBeta");
		if (cnbeta != null) {
			fetchService.fetchFeed(cnbeta.getId());
		}

		Feed oschina = feedRepository.findByTitle("oschina");
		if (oschina != null) {
			fetchService.fetchFeed(oschina.getId());
		}
	}

	@Test
	public void fetchAll() {
		for (Feed feed : feedRepository.findAll()) {
			fetchService.fetchFeed(feed.getId());
		}
	}
}
