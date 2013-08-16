/*
 * Copyright (C) CCRISE.
 */
package org.shelltea.seeker.web.api;

import javax.validation.Valid;

import org.shelltea.seeker.entity.Account;
import org.shelltea.seeker.repository.AccountRepository;
import org.shelltea.seeker.util.ValidationUtils;
import org.shelltea.seeker.web.entity.RegisterAccount;
import org.shelltea.seeker.web.entity.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
@Controller
@RequestMapping(value = "api/accounts")
public class AccountApiController {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private MessageSource messageSource;

	@ResponseBody
	@RequestMapping(value = "checking/email", method = RequestMethod.GET)
	public String checkingEmail(String email) {
		logger.debug("{}", email);

		if (Strings.isNullOrEmpty(email)) {
			return "电子邮箱不能为空";
		}

		Account account = accountRepository.findByEmail(email);
		if (null == account) {
			return "";
		} else {
			return "电子邮箱已存在";
		}
	}

	@ResponseBody
	@RequestMapping(value = "checking/username", method = RequestMethod.GET)
	public String checkingUsername(String username) {
		logger.debug("{}", username);

		if (Strings.isNullOrEmpty(username)) {
			return "用户名不能为空";
		}

		Account account = accountRepository.findByUsername(username);
		if (null == account) {
			return "";
		} else {
			return "用户名已存在";
		}
	}

	@ResponseBody
	@ExceptionHandler
	public Response handleMethodArgumentNotValidException(MethodArgumentNotValidException error) {
		return new Response(ValidationUtils.renderResultMap(error.getBindingResult(), messageSource));
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public Response register(@Valid @RequestBody RegisterAccount registerAccount) {
		// 唯一性验证
		if (null != accountRepository.findByUsername(registerAccount.getUsername())) {
			return new Response(ValidationUtils.renderResultMap("Unique.registerAccount.username", messageSource));
		}

		if (null != accountRepository.findByEmail(registerAccount.getEmail())) {
			return new Response(ValidationUtils.renderResultMap("Unique.registerAccount.email", messageSource));
		}

		// TODO 创建用户

		return new Response(true);
	}
}
