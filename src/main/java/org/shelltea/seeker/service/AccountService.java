/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.service;

import java.sql.Timestamp;
import java.util.List;

import org.shelltea.seeker.access.AccountDao;
import org.shelltea.seeker.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
@Service
@Transactional
public class AccountService {
	@Autowired
	private AccountDao accountDao;

	public long count() {
		return accountDao.count();
	}

	public List<Account> findAll() {
		return (List<Account>) accountDao.findAll();
	}

	public boolean init() {
		Account account = new Account();
		account.setPrincipal("admin");
		account.setCredential("admin");
		account.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		account.setLocked(false);

		accountDao.save(account);
		return true;
	}
}
