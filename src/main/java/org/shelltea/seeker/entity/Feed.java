/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.entity;

import com.google.common.collect.Sets;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * 订阅源.
 *
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
@Entity
@Table(name = "seeker_feed")
public class Feed extends IdEntity {
    /**
     * 网站地址.
     */
    private String url;

    /**
     * Favicon地址.
     */
    private String faviconUrl;

    /**
     * Logo地址.
     */
    private String logoUrl;

    /**
     * 列表抓取地址.
     */
    private String fetchUrl;

    /**
     * 文章条目url前缀
     */
    private String entryUrlPrefix;

    /**
     * 标题.
     */
    private String title;

    /**
     * 描述.
     */
    private String description;

    /**
     * 创建时间.
     */
    private Date createTime = new Date(System.currentTimeMillis());

    /**
     * 最后抓取时间.
     */
    private Date lastFetchTime;

    /**
     * 列表选择器.
     */
    private String listSelector;

    /**
     * 标题选择器.
     */
    private String titleSelector;

    /**
     * 内容选择器.
     */
    private String originContentSelector;

    /**
     * 发布时间选择器.
     */
    private String publishedTimeSelector;

    /**
     * 发布时间格式.
     */
    private String publishedTimePattern;

    /**
     * 来源选择器.
     */
    private String authorSelector;

    /**
     * 移除选择器.
     */
    private Set<Selector> removeSelectors = Sets.newHashSet();

    public String getAuthorSelector() {
        return authorSelector;
    }

    public void setAuthorSelector(String authorSelector) {
        this.authorSelector = authorSelector;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Lob
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(nullable = false)
    public String getEntryUrlPrefix() {
        return entryUrlPrefix;
    }

    public void setEntryUrlPrefix(String entryUrlPrefix) {
        this.entryUrlPrefix = entryUrlPrefix;
    }

    public String getFaviconUrl() {
        return faviconUrl;
    }

    public void setFaviconUrl(String faviconUrl) {
        this.faviconUrl = faviconUrl;
    }

    @Column(nullable = false, unique = true)
    public String getFetchUrl() {
        return fetchUrl;
    }

    public void setFetchUrl(String fetchUrl) {
        this.fetchUrl = fetchUrl;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getLastFetchTime() {
        return lastFetchTime;
    }

    public void setLastFetchTime(Date lastFetchTime) {
        this.lastFetchTime = lastFetchTime;
    }

    @Column(nullable = false)
    public String getListSelector() {
        return listSelector;
    }

    public void setListSelector(String listSelector) {
        this.listSelector = listSelector;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    @Column(nullable = false)
    public String getOriginContentSelector() {
        return originContentSelector;
    }

    public void setOriginContentSelector(String originContentSelector) {
        this.originContentSelector = originContentSelector;
    }

    public String getPublishedTimePattern() {
        return publishedTimePattern;
    }

    public void setPublishedTimePattern(String publishedTimePattern) {
        this.publishedTimePattern = publishedTimePattern;
    }

    public String getPublishedTimeSelector() {
        return publishedTimeSelector;
    }

    public void setPublishedTimeSelector(String publishedTimeSelector) {
        this.publishedTimeSelector = publishedTimeSelector;
    }

    @OneToMany(fetch = FetchType.EAGER)
    public Set<Selector> getRemoveSelectors() {
        return removeSelectors;
    }

    public void setRemoveSelectors(Set<Selector> removeSelectors) {
        this.removeSelectors = removeSelectors;
    }

    @Column(nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleSelector() {
        return titleSelector;
    }

    public void setTitleSelector(String titleSelector) {
        this.titleSelector = titleSelector;
    }

    @Column(nullable = false, unique = true)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
