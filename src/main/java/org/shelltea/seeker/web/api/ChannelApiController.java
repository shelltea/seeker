/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.web.api;

import org.shelltea.seeker.repository.ChannelRepository;
import org.shelltea.seeker.web.api.entity.Response;
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
@RequestMapping(value = "api/channels")
public class ChannelApiController {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ChannelRepository channelRepository;

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public Response list() {
		return new Response(channelRepository.findAll());
	}
}
