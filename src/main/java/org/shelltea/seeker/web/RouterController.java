/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.web;

import org.shelltea.seeker.repository.FeedRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
@Controller
public class RouterController {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private FeedRepository feedRepository;

	@RequestMapping(value = "explore", method = RequestMethod.GET)
	public String explore() {
		return "explore";
	}

	@RequestMapping(value = "feed/{title}", method = RequestMethod.GET)
	public String feed(@PathVariable String title) {
		return "index";
	}

	@RequestMapping(value = "inbox", method = RequestMethod.GET)
	public String inbox() {
		return "index";
	}
}
