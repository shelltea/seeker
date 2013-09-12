/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.web.api;

import org.apache.shiro.SecurityUtils;
import org.shelltea.seeker.repository.CategoryRepository;
import org.shelltea.seeker.service.CategoryService;
import org.shelltea.seeker.web.api.entity.Response;
import org.shelltea.seeker.web.entity.ShiroAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
@Controller
@RequestMapping(value = "api/feeds")
public class FeedApiController {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CategoryRepository categoryRepository;

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public Response list() {
		ShiroAccount loginAccount = (ShiroAccount) SecurityUtils.getSubject().getPrincipal();

		return new Response(categoryRepository.findByAccountIdAndTitle(loginAccount.getId(),
				CategoryService.DEFAULT_ROOT_CATEGORY).getFeeds());
	}
}
