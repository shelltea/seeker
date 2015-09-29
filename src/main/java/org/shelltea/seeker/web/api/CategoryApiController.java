/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.web.api;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.SecurityUtils;
import org.shelltea.seeker.entity.Category;
import org.shelltea.seeker.entity.Feed;
import org.shelltea.seeker.repository.CategoryRepository;
import org.shelltea.seeker.repository.FeedRepository;
import org.shelltea.seeker.service.CategoryService;
import org.shelltea.seeker.web.api.entity.ApiCategory;
import org.shelltea.seeker.web.entity.Response;
import org.shelltea.seeker.web.entity.ShiroAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;

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
        // 验证用户是否有添加Feed到此Category的权限
        if (SecurityUtils.getSubject().isPermitted("categories:add-feed:" + id)) {
            Category category = categoryRepository.findOne(id);
            feed = feedRepository.findOne(feed.getId());

            if (category != null && feed != null) {
                category.getFeeds().add(feed);
                categoryRepository.save(category);
                return new Response(true);
            }
        }

        return new Response();
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public Response list() throws IllegalAccessException, InvocationTargetException {
        ShiroAccount loginAccount = (ShiroAccount) SecurityUtils.getSubject().getPrincipal();

        ApiCategory apiCategory = new ApiCategory();
        BeanUtils
                .copyProperties(apiCategory, categoryRepository.findByAccountIdAndTitle(loginAccount.getId(),
                        CategoryService.DEFAULT_ROOT_CATEGORY));

        return new Response(apiCategory);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Response removeFeedFromCategory(@PathVariable long id, @RequestBody Feed feed) {
        // 验证用户是否有从此Category移除Feed的权限
        if (SecurityUtils.getSubject().isPermitted("categories:remove-feed:" + id)) {
            Category category = categoryRepository.findOne(id);
            feed = feedRepository.findOne(feed.getId());

            if (category != null && feed != null) {
                category.getFeeds().remove(feed);
                categoryRepository.save(category);
                return new Response(true);
            }
        }

        return new Response();
    }
}
