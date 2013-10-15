/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.web.api;

import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.shelltea.seeker.entity.Channel;
import org.shelltea.seeker.entity.Feed;
import org.shelltea.seeker.repository.CategoryRepository;
import org.shelltea.seeker.repository.ChannelRepository;
import org.shelltea.seeker.service.CategoryService;
import org.shelltea.seeker.web.api.entity.ApiChannel;
import org.shelltea.seeker.web.api.entity.ApiFeed;
import org.shelltea.seeker.web.entity.Response;
import org.shelltea.seeker.web.entity.ShiroAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
@Controller
@RequestMapping(value = "api/channels")
public class ChannelApiController {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ChannelRepository channelRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private MessageSource messageSource;

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public Response list(final Locale locale) {
		// 获取用户已订阅Feed
		ShiroAccount loginAccount = (ShiroAccount) SecurityUtils.getSubject().getPrincipal();

		final Set<Feed> feeds = categoryRepository.findByAccountIdAndTitle(loginAccount.getId(),
				CategoryService.DEFAULT_ROOT_CATEGORY).getFeeds();

		// 获取所有Channel
		List<Channel> channels = channelRepository.findAll();

		// 转换为ApiChannel
		List<ApiChannel> apiChannels = Lists.transform(channels, new Function<Channel, ApiChannel>() {
			@Override
			public ApiChannel apply(Channel input) {
				List<ApiFeed> apiFeeds = Lists.transform(Lists.newArrayList(input.getFeeds()),
						new Function<Feed, ApiFeed>() {
							@Override
							public ApiFeed apply(Feed input) {
								return new ApiFeed(input.getId(), input.getTitle(), input.getLogoUrl(), feeds
										.contains(input));
							}
						});
				return new ApiChannel(input.getId(), messageSource.getMessage("channel." + input.getIconUrl(), null,
						locale), input.getIconUrl(), Sets.newHashSet(apiFeeds));
			}
		});

		return new Response(apiChannels);
	}
}
