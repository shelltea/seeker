/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.entity;

import com.google.common.collect.Sets;

import javax.persistence.*;
import java.util.Set;

/**
 * 频道.
 *
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
@Entity
@Table(name = "seeker_channel")
public class Channel extends IdEntity {
    /**
     * 标题.
     */
    private String title;

    /**
     * 图标地址/样式.
     */
    private String iconUrl;

    /**
     * 描述.
     */
    private String description;

    /**
     * 包含的源.
     */
    private Set<Feed> feeds = Sets.newHashSet();

    public Channel() {
    }

    public Channel(String title, String iconUrl, String description) {
        this.title = title;
        this.iconUrl = iconUrl;
        this.description = description;
    }

    @Lob
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(fetch = FetchType.EAGER)
    public Set<Feed> getFeeds() {
        return feeds;
    }

    public void setFeeds(Set<Feed> feeds) {
        this.feeds = feeds;
    }

    @Column(nullable = false, unique = true)
    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    @Column(nullable = false, unique = true)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
