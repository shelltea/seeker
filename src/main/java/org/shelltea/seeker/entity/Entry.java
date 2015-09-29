/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

/**
 * 文章条目.
 *
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
@Entity
@Table(name = "seeker_entry")
@JsonIgnoreProperties({"originContent"})
public class Entry extends IdEntity {
    /**
     * 所属源.
     */
    private Long feedId;

    /**
     * 文章原地址.
     */
    private String url;

    /**
     * 标题.
     */
    private String title;

    /**
     * 作者.
     */
    private String author;

    /**
     * 原内容.
     */
    private String originContent;

    /**
     * 处理后的内容.
     */
    private String content;

    /**
     * 最后抓取时间.
     */
    private Date lastFetchTime;

    /**
     * 发布时间.
     */
    private Date publishedTime;

	/* 非持久化属性 */
    /**
     * 是否加星.
     */
    private Boolean starred;

    /**
     * 是否已读.
     */
    private Boolean read;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Lob
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(nullable = false)
    public Long getFeedId() {
        return feedId;
    }

    public void setFeedId(Long feedId) {
        this.feedId = feedId;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getLastFetchTime() {
        return lastFetchTime;
    }

    public void setLastFetchTime(Date lastFetchTime) {
        this.lastFetchTime = lastFetchTime;
    }

    @Lob
    public String getOriginContent() {
        return originContent;
    }

    public void setOriginContent(String originContent) {
        this.originContent = originContent;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getPublishedTime() {
        return publishedTime;
    }

    public void setPublishedTime(Date publishedTime) {
        this.publishedTime = publishedTime;
    }

    @Transient
    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    @Transient
    public Boolean getStarred() {
        return starred;
    }

    public void setStarred(Boolean starred) {
        this.starred = starred;
    }

    @Column(nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(nullable = false, unique = true)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
