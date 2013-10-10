/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.service.security;

import java.util.Date;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.shelltea.seeker.entity.Account;
import org.shelltea.seeker.entity.Category;
import org.shelltea.seeker.repository.AccountRepository;
import org.shelltea.seeker.repository.CategoryRepository;
import org.shelltea.seeker.service.AccountService;
import org.shelltea.seeker.service.CategoryService;
import org.shelltea.seeker.web.entity.ShiroAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
@Service
public class ShiroDbRealm extends AuthorizingRealm {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	public ShiroDbRealm() {
		super();

		// 设置HashedCredentialsMatcher，采用Base64编码
		HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher(AccountService.HASH_ALGORITHM_NAME);
		credentialsMatcher.setHashIterations(AccountService.HASH_ITERATIONS);
		credentialsMatcher.setStoredCredentialsHexEncoded(false);
		setCredentialsMatcher(credentialsMatcher);
	}

	public AccountRepository getAccountRepository() {
		return accountRepository;
	}

	public void setAccountRepository(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;

		Account account = accountRepository.findByUsername(usernamePasswordToken.getUsername());

		if (account != null) {
			account.setUpdateTime(new Date(System.currentTimeMillis()));
			accountRepository.save(account);

			return new SimpleAuthenticationInfo(new ShiroAccount(account.getId(), account.getUsername(),
					account.getEmail()), account.getPassword(), ByteSource.Util.bytes(account.getSalt()), getName());
		} else {
			return null;
		}
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		ShiroAccount shiroAccount = (ShiroAccount) principals.getPrimaryPrincipal();

		// 设置用户权限
		Category category = categoryRepository.findByAccountIdAndTitle(shiroAccount.getId(),
				CategoryService.DEFAULT_ROOT_CATEGORY);

		Set<String> permissions = Sets.newHashSet();
		permissions.add("categories:read,create");
		permissions.add("categories:add-feed:" + category.getId());
		permissions.add("channels:read");
		permissions.add("entries:read,update");
		permissions.add("feeds:read");

		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.addStringPermissions(permissions);
		return simpleAuthorizationInfo;
	}
}
