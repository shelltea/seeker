/*
 * Copyright (C) CCRISE.
 */
package org.shelltea.seeker.repository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
@ContextConfiguration(locations = { "/spring/applicationContext.xml" })
public class AccountRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	private AccountRepository accountRepository;

	@Test
	public void testFindByEmail() {
		Assert.assertEquals("admin", accountRepository.findByEmail("shelltea@gmail.com").getUsername());
	}
}
