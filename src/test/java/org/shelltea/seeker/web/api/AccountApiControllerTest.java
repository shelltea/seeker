/*
 * Copyright (C) CCRISE.
 */
package org.shelltea.seeker.web.api;

import org.junit.Test;
import org.shelltea.seeker.web.entity.RegisterAccount;
import org.shelltea.seeker.web.entity.Response;
import org.springframework.web.client.RestTemplate;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
public class AccountApiControllerTest {
	@Test
	public void testRegister() {
		RegisterAccount registerAccount = new RegisterAccount();
		registerAccount.setEmail("");
		registerAccount.setUsername("");
		registerAccount.setPassword("");
		Response result = new RestTemplate().postForObject("http://localhost:8080/api/accounts", registerAccount,
				Response.class);
		System.out.println(result);

		registerAccount.setEmail("11");
		registerAccount.setUsername("22");
		result = new RestTemplate()
				.postForObject("http://localhost:8080/api/accounts", registerAccount, Response.class);
		System.out.println(result);

		registerAccount.setEmail("11@11");
		registerAccount.setUsername("2222222222222");
		result = new RestTemplate()
				.postForObject("http://localhost:8080/api/accounts", registerAccount, Response.class);
		System.out.println(result);

		registerAccount.setEmail("shelltea@gmail.com");
		registerAccount.setUsername("admin");
		registerAccount.setPassword("admin");
		result = new RestTemplate()
				.postForObject("http://localhost:8080/api/accounts", registerAccount, Response.class);
		System.out.println(result);

		registerAccount.setEmail("shelltea1@gmail.com");
		registerAccount.setUsername("admin1");
		registerAccount.setPassword("admin");
		result = new RestTemplate()
				.postForObject("http://localhost:8080/api/accounts", registerAccount, Response.class);
		System.out.println(result);
	}
}
