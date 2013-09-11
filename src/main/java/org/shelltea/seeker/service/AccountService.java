/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.service;

import java.sql.Timestamp;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.shelltea.seeker.entity.Account;
import org.shelltea.seeker.entity.Category;
import org.shelltea.seeker.repository.AccountRepository;
import org.shelltea.seeker.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
@Service
public class AccountService {
	public static final int HASH_ITERATIONS = 1024;
	public static final String HASH_ALGORITHM_NAME = "SHA-512";

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	public Account create(String email, String username, String password) {
		RandomNumberGenerator saltGenerator = new SecureRandomNumberGenerator();
		String salt = saltGenerator.nextBytes().toBase64();

		Account account = new Account();
		account.setEmail(email.trim());
		account.setUsername(username.trim());
		account.setSalt(salt);
		account.setPassword(new SimpleHash(HASH_ALGORITHM_NAME, password.trim(), salt, HASH_ITERATIONS).toBase64());
		account.setLocked(false);
		account.setUpdateTime(new Timestamp(System.currentTimeMillis()));

		return account;
	}

	public Account initialize(Account account) {
		accountRepository.save(account);

		// 初始化根分类
		Category category = new Category();
		category.setAccountId(account.getId());
		category.setSequence(1);
		category.setTitle(CategoryService.DEFAULT_ROOT_CATEGORY);
		categoryRepository.save(category);

		return account;
	}
}
