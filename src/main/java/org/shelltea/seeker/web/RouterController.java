/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
@Controller
public class RouterController {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping(value = "inbox", method = RequestMethod.GET)
	public String register() {
		return "index";
	}
}
