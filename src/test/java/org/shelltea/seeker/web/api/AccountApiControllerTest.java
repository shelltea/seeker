/*
 * Copyright (C) CCRISE.
 */
package org.shelltea.seeker.web.api;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.shelltea.seeker.web.entity.LoginAccount;
import org.shelltea.seeker.web.entity.RegisterAccount;
import org.shelltea.seeker.web.entity.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
public class AccountApiControllerTest {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	public void testRegister() {
		RegisterAccount registerAccount = new RegisterAccount();
		Response result = new RestTemplate().postForObject("http://localhost:8080/api/accounts?locale=en_US",
				registerAccount, Response.class);
		logger.debug("{}", result);

		registerAccount.setEmail("11");
		registerAccount.setUsername("22");
		result = new RestTemplate()
				.postForObject("http://localhost:8080/api/accounts", registerAccount, Response.class);
		logger.debug("{}", result);

		registerAccount.setEmail("11@11");
		registerAccount.setUsername("2222222222222");
		result = new RestTemplate().postForObject("http://localhost:8080/api/accounts?locale=en_US", registerAccount,
				Response.class);
		logger.debug("{}", result);

		String username = RandomStringUtils.randomAlphabetic(6);

		registerAccount.setEmail(username + "@gmail.com");
		registerAccount.setUsername("admin");
		registerAccount.setPassword("admin");
		result = new RestTemplate()
				.postForObject("http://localhost:8080/api/accounts", registerAccount, Response.class);
		logger.debug("{}", result);

		registerAccount.setEmail("shelltea@gmail.com");
		registerAccount.setUsername(username);
		registerAccount.setPassword("admin");
		result = new RestTemplate().postForObject("http://localhost:8080/api/accounts?locale=en_US", registerAccount,
				Response.class);
		logger.debug("{}", result);

		registerAccount.setEmail(username + "@gmail.com");
		registerAccount.setUsername(username);
		registerAccount.setPassword(username);
		result = new RestTemplate()
				.postForObject("http://localhost:8080/api/accounts", registerAccount, Response.class);
		logger.debug("{}", result);
	}

	@Test
	public void testValidator() {
		LoginAccount loginAccount = new LoginAccount();
		Response result = new RestTemplate().postForObject("http://localhost:8080/api/accounts/validator?locale=zh_CN",
				loginAccount, Response.class);
		logger.debug("{}", result);

		loginAccount.setUsername("11");
		loginAccount.setPassword("22");
		result = new RestTemplate().postForObject("http://localhost:8080/api/accounts/validator?locale=en_US",
				loginAccount, Response.class);
		logger.debug("{}", result);

		loginAccount.setUsername("shelltea1");
		loginAccount.setPassword("shelltea1");
		result = new RestTemplate().postForObject("http://localhost:8080/api/accounts/validator?locale=fr_FR",
				loginAccount, Response.class);
		logger.debug("{}", result);

		loginAccount.setUsername("shelltea");
		loginAccount.setPassword("shelltea1");
		result = new RestTemplate().postForObject("http://localhost:8080/api/accounts/validator?locale=en_US",
				loginAccount, Response.class);
		logger.debug("{}", result);

		loginAccount.setUsername("shelltea");
		loginAccount.setPassword("shelltea");
		result = new RestTemplate().postForObject("http://localhost:8080/api/accounts/validator", loginAccount,
				Response.class);
		logger.debug("{}", result);

		loginAccount.setRememberMe("on");
		result = new RestTemplate().postForObject("http://localhost:8080/api/accounts/validator", loginAccount,
				Response.class);
		logger.debug("{}", result);
	}
}
