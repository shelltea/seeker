/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.web.api;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.shelltea.seeker.entity.Account;
import org.shelltea.seeker.repository.AccountRepository;
import org.shelltea.seeker.repository.CategoryRepository;
import org.shelltea.seeker.service.AccountService;
import org.shelltea.seeker.util.ValidationUtils;
import org.shelltea.seeker.web.entity.LoginAccount;
import org.shelltea.seeker.web.entity.RegisterAccount;
import org.shelltea.seeker.web.entity.Response;
import org.shelltea.seeker.web.entity.ShiroAccount;
import org.shelltea.seeker.web.entity.UpdateAccount;
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
	private AccountService accountService;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private MessageSource messageSource;

	@ResponseBody
	@RequestMapping(value = "checking/email", method = RequestMethod.GET)
	public String checkingEmail(String email, Locale locale) {
		if (Strings.isNullOrEmpty(email)) {
			return messageSource.getMessage("NotBlank.registerAccount.email", null, locale);
		}

		// 如果用户已登录，则做修改邮件时的验证
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			ShiroAccount loginAccount = (ShiroAccount) subject.getPrincipal();
			if (StringUtils.equals(email, loginAccount.getEmail())) {
				return "";
			}
		}

		Account account = accountRepository.findByEmail(email);
		if (null == account) {
			return "";
		} else {
			return messageSource.getMessage("Unique.registerAccount.email", null, locale);
		}
	}

	@ResponseBody
	@RequestMapping(value = "checking/password", method = RequestMethod.GET)
	public String checkingPassword(String oldPassword, Locale locale) {
		if (Strings.isNullOrEmpty(oldPassword)) {
			return messageSource.getMessage("NotBlank.updateAccount.oldPassword", null, locale);
		}

		ShiroAccount loginAccount = (ShiroAccount) SecurityUtils.getSubject().getPrincipal();

		if (loginAccount == null) {
			return messageSource.getMessage("NotLogin.updateAccount.oldPassword", null, locale);
		}

		if (accountService.isPasswordMatch(loginAccount.getId(), oldPassword)) {
			return "";
		} else {
			return messageSource.getMessage("Invalidate.updateAccount.oldPassword", null, locale);
		}
	}

	@ResponseBody
	@RequestMapping(value = "checking/username", method = RequestMethod.GET)
	public String checkingUsername(String username, Locale locale) {
		if (Strings.isNullOrEmpty(username)) {
			return messageSource.getMessage("NotBlank.registerAccount.username", null, locale);
		}

		Account account = accountRepository.findByUsername(username);
		if (null == account) {
			return "";
		} else {
			return messageSource.getMessage("Unique.registerAccount.username", null, locale);
		}
	}

	@ResponseBody
	@ExceptionHandler
	public Response handleMethodArgumentNotValidException(MethodArgumentNotValidException error,
			HttpServletRequest request, Locale locale) {
		return new Response(ValidationUtils.renderResultMap(error.getBindingResult(), messageSource, locale));
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public Response register(@Valid @RequestBody RegisterAccount registerAccount, Locale locale) {
		// 唯一性验证
		if (null != accountRepository.findByUsername(registerAccount.getUsername())) {
			return new Response(ValidationUtils.renderResultMap("Unique.registerAccount.username", messageSource,
					locale));
		}

		if (null != accountRepository.findByEmail(registerAccount.getEmail())) {
			return new Response(ValidationUtils.renderResultMap("Unique.registerAccount.email", messageSource, locale));
		}

		// 创建并初始化用户
		accountService.initialize(accountService.create(registerAccount.getEmail(), registerAccount.getUsername(),
				registerAccount.getPassword()));

		return new Response(true);
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT)
	public Response update(@Valid @RequestBody UpdateAccount updateAccount, Locale locale) {
		ShiroAccount loginAccount = (ShiroAccount) SecurityUtils.getSubject().getPrincipal();

		// 验证用户是否登录
		if (loginAccount == null) {
			return new Response(ValidationUtils.renderResultMap("NotLogin.updateAccount.newPassword", messageSource,
					locale));
		}

		if (StringUtils.isNotBlank(updateAccount.getEmail())) { // 更新邮箱
			if (StringUtils.equals(updateAccount.getEmail(), loginAccount.getEmail())) {
				return new Response(true);
			}

			Account account = accountRepository.findByEmail(updateAccount.getEmail());
			if (null == account) {
				Account accountInDb = accountRepository.findOne(loginAccount.getId());
				accountInDb.setEmail(updateAccount.getEmail());
				accountRepository.save(accountInDb);
				loginAccount.setEmail(updateAccount.getEmail()); // 同步更新ShiroAccount
				return new Response(true);
			} else {
				return new Response(ValidationUtils.renderResultMap("Unique.registerAccount.email", messageSource,
						locale));
			}
		} else { // 更新密码
			if (!StringUtils.equals(updateAccount.getNewPassword(), updateAccount.getConfirmPassword())) {
				return new Response(ValidationUtils.renderResultMap("Invalidate.updateAccount.confirmPassword",
						messageSource, locale));
			}

			if (!accountService.isPasswordMatch(loginAccount.getId(), updateAccount.getOldPassword())) {
				return new Response(ValidationUtils.renderResultMap("Invalidate.updateAccount.oldPassword",
						messageSource, locale));
			}

			accountService.updatePassword(loginAccount.getId(), updateAccount.getConfirmPassword());
			return new Response(true);
		}
	}

	@ResponseBody
	@RequestMapping(value = "validator", method = RequestMethod.POST)
	public Response validator(@Valid @RequestBody LoginAccount loginAccount, Locale locale) {
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(loginAccount.getUsername(),
				loginAccount.getPassword(), StringUtils.equals("on", loginAccount.getRememberMe()));
		Subject currentUser = SecurityUtils.getSubject();

		try {
			currentUser.login(usernamePasswordToken);
			return new Response(true);
		} catch (IncorrectCredentialsException e) {
			return new Response(ValidationUtils.renderResultMap("Invalidate.loginAccount.usernameOrPassword",
					messageSource, locale));
		} catch (UnknownAccountException e) {
			return new Response(
					ValidationUtils.renderResultMap("NotExist.loginAccount.username", messageSource, locale));
		}
	}
}
