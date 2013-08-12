/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
@Controller
public class IndexController {
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String uiWizard() {
		return "login";
	}
}
