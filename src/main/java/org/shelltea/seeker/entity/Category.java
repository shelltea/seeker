/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.entity;

import com.google.common.collect.Sets;

import javax.persistence.*;
import java.util.Set;

/**
 * 分类.
 *
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
@Entity
@Table(name = "seeker_category")
public class Category extends IdEntity {
    /**
     * 所属账户.
     */
    private Long accountId;

    /**
     * 分类名称.
     */
    private String title;

    /**
     * 子分类.
     */
    private Set<Category> categories = Sets.newHashSet();

    /**
     * 包含的源.
     */
    private Set<Feed> feeds = Sets.newHashSet();

    /**
     * 顺序.
     */
    private Integer sequence;

    @Column(nullable = false)
    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    @OneToMany(fetch = FetchType.EAGER)
    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    @OneToMany(fetch = FetchType.EAGER)
    @OrderBy
    public Set<Feed> getFeeds() {
        return feeds;
    }

    public void setFeeds(Set<Feed> feeds) {
        this.feeds = feeds;
    }

    @Column(nullable = false)
    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    @Column(nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
