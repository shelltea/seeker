/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.web.api;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.shelltea.seeker.entity.Category;
import org.shelltea.seeker.entity.Feed;
import org.shelltea.seeker.repository.CategoryRepository;
import org.shelltea.seeker.repository.FeedRepository;
import org.shelltea.seeker.service.CategoryService;
import org.shelltea.seeker.web.entity.Response;
import org.shelltea.seeker.web.entity.ShiroAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
@Controller
@RequestMapping(value = "api/categories")
public class CategoryApiController {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private FeedRepository feedRepository;

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public Response addFeedToCategory(@PathVariable long id, @RequestBody Feed feed) {
		feed = feedRepository.findOne(feed.getId());

		if (feed == null) {
			return new Response();
		}

		// 验证用户是否有添加Feed到此Category的权限
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isPermitted("categories:add-feed:" + id)) {
			Category category = categoryRepository.findOne(id);

			if (category == null) {
				return new Response();
			}

			category.getFeeds().add(feed);
			return new Response(categoryRepository.save(category));
		} else {
			return new Response();
		}
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public Response list() {
		ShiroAccount loginAccount = (ShiroAccount) SecurityUtils.getSubject().getPrincipal();

		return new Response(categoryRepository.findByAccountIdAndTitle(loginAccount.getId(),
				CategoryService.DEFAULT_ROOT_CATEGORY));
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Response removeFeedFromCategory(@PathVariable long id, @RequestBody Feed feed) {
		feed = feedRepository.findOne(feed.getId());

		if (feed == null) {
			return new Response();
		}

		// 验证用户是否有从此Category移除Feed的权限
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isPermitted("categories:remove-feed:" + id)) {
			Category category = categoryRepository.findOne(id);

			if (category == null) {
				return new Response();
			}

			category.getFeeds().remove(feed);
			return new Response(categoryRepository.save(category));
		} else {
			return new Response();
		}
	}
}
