/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.service.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.shelltea.seeker.entity.Account;
import org.shelltea.seeker.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
@Service
public class ShiroDbRealm extends AuthorizingRealm {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AccountService accountService;

	public ShiroDbRealm() {
		super();

		// 设置HashedCredentialsMatcher，采用Base64编码
		HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher(AccountService.HASH_ALGORITHM_NAME);
		credentialsMatcher.setHashIterations(AccountService.HASH_ITERATIONS);
		credentialsMatcher.setStoredCredentialsHexEncoded(false);
		setCredentialsMatcher(credentialsMatcher);
	}

	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;

		Account account = accountService.findByUsername(usernamePasswordToken.getUsername());

		if (account != null) {
			return new SimpleAuthenticationInfo(account, account.getPassword(),
					ByteSource.Util.bytes(account.getSalt()), getName());
		} else {
			return null;
		}
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}
}
