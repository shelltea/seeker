/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.service;

import java.sql.Timestamp;
import java.util.List;

import org.shelltea.seeker.entity.Account;
import org.shelltea.seeker.repository.AccountRepository;
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
	private AccountRepository accountRepository;

	public long count() {
		return accountRepository.count();
	}

	public List<Account> findAll() {
		return (List<Account>) accountRepository.findAll();
	}

	public Account findByUsername(String username) {
		return accountRepository.findByUsername(username);
	}

	public boolean init() {
		Account admin = new Account();
		admin.setUsername("admin");
		admin.setPassword("admin");
		admin.setEmail("shelltea@gmail.com");
		admin.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		admin.setLocked(false);

		accountRepository.save(admin);
		return true;
	}
}
