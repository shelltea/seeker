/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.web;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.shelltea.seeker.web.entity.LoginAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Locale;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
@Controller
@RequestMapping(value = "account/login")
public class LoginController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET)
    public String loginGet() {
        return "account/login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String loginPost(LoginAccount loginAccount, RedirectAttributes redirectAttributes, Locale locale) {
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(loginAccount.getUsername(),
                loginAccount.getPassword(), StringUtils.equals("on", loginAccount.getRememberMe()));
        Subject currentUser = SecurityUtils.getSubject();

        try {
            currentUser.login(usernamePasswordToken);
            return "redirect:/";
        } catch (IncorrectCredentialsException e) {
            redirectAttributes.addFlashAttribute("loginMessage",
                    messageSource.getMessage("Invalidate.loginAccount.usernameOrPassword", null, locale));
            return "redirect:/account/login";
        } catch (UnknownAccountException e) {
            redirectAttributes.addFlashAttribute("loginMessage",
                    messageSource.getMessage("NotExist.loginAccount.username", null, locale));
            return "redirect:/account/login";
        }
    }
}
