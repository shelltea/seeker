/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.web.api.entity;

import com.google.common.collect.Sets;
import org.shelltea.seeker.web.entity.WebIdEntity;

import java.util.Set;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
public class ApiChannel extends WebIdEntity {
    private String title;
    private String iconUrl;
    private Set<ApiFeed> feeds = Sets.newHashSet();

    public ApiChannel() {
    }

    public ApiChannel(Long id, String title, String iconUrl, Set<ApiFeed> feeds) {
        this.id = id;
        this.title = title;
        this.iconUrl = iconUrl;
        this.feeds = feeds;
    }

    public Set<ApiFeed> getFeeds() {
        return feeds;
    }

    public void setFeeds(Set<ApiFeed> feeds) {
        this.feeds = feeds;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
