/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.web.api;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.shelltea.seeker.entity.Category;
import org.shelltea.seeker.entity.Feed;
import org.shelltea.seeker.repository.CategoryRepository;
import org.shelltea.seeker.repository.EntryRepository;
import org.shelltea.seeker.web.api.entity.Response;
import org.shelltea.seeker.web.entity.ShiroAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
@Controller
@RequestMapping(value = "api/entries")
public class EntryApiController {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private EntryRepository entryRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public Response list(@RequestParam(defaultValue = "0") Integer page, Integer size) {
		ShiroAccount loginAccount = (ShiroAccount) SecurityUtils.getSubject().getPrincipal();

		// 查询当前用户包含的分类
		List<Category> categories = categoryRepository.findByAccountId(loginAccount.getId());

		// 获取所有分类中的Feed
		List<Feed> feeds = Lists.newArrayList();
		for (Category category : categories) {
			feeds.addAll(category.getFeeds());
		}

		List<Long> feedIds = Lists.transform(feeds, new Function<Feed, Long>() {
			@Override
			public Long apply(Feed input) {
				return input.getId();
			}
		});

		return new Response(entryRepository.findByFeedIdIn(feedIds, new PageRequest(page, size, new Sort(
				Direction.DESC, "id"))));
	}
}
