/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.service;

import java.sql.Timestamp;
import java.util.List;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
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

	public boolean createNewAccount(String email, String username, String password) {
		RandomNumberGenerator saltGenerator = new SecureRandomNumberGenerator();
		String salt = saltGenerator.nextBytes().toBase64();

		Account account = new Account();
		account.setEmail(email);
		account.setUsername(username);
		account.setSalt(salt);
		account.setPassword(new Sha256Hash(password, salt, 1024).toBase64());
		account.setLocked(false);
		account.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		accountRepository.save(account);
		return true;
	}

	public List<Account> findAll() {
		return (List<Account>) accountRepository.findAll();
	}

	public Account findByUsername(String username) {
		return accountRepository.findByUsername(username);
	}
}
