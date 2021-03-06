/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.repository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
@ContextConfiguration(locations = {"/spring/applicationContext.xml"})
@TransactionConfiguration(defaultRollback = false)
public class AccountRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void testFindByEmail() {
        Assert.assertEquals("admin", accountRepository.findByEmail("shelltea@gmail.com").getUsername());
    }
}
