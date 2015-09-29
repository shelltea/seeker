/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.web.api;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.apache.shiro.SecurityUtils;
import org.shelltea.seeker.entity.Feed;
import org.shelltea.seeker.repository.CategoryRepository;
import org.shelltea.seeker.repository.EntryRepository;
import org.shelltea.seeker.service.CategoryService;
import org.shelltea.seeker.web.api.entity.ApiFeed;
import org.shelltea.seeker.web.entity.Response;
import org.shelltea.seeker.web.entity.ShiroAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Set;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
@Controller
@RequestMapping(value = "api/feeds")
public class FeedApiController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private EntryRepository entryRepository;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public Response list() {
        ShiroAccount loginAccount = (ShiroAccount) SecurityUtils.getSubject().getPrincipal();

        Set<Feed> feeds = categoryRepository.findByAccountIdAndTitle(loginAccount.getId(),
                CategoryService.DEFAULT_ROOT_CATEGORY).getFeeds();

        List<ApiFeed> apiFeeds = Lists.transform(Lists.newArrayList(feeds), new Function<Feed, ApiFeed>() {
            @Override
            public ApiFeed apply(Feed input) {
                return new ApiFeed(input.getId(), input.getTitle(), input.getFaviconUrl(), entryRepository
                        .countByFeedId(input.getId()));
            }
        });

        return new Response(apiFeeds);
    }
}
