/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 文章条目.
 * 
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
@Entity
@Table(name = "seeker_entry")
public class Entry extends IDEntity {
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

	public String getAuthor() {
		return author;
	}

	@Lob
	public String getContent() {
		return content;
	}

	@Column(nullable = false)
	public Long getFeedId() {
		return feedId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getLastFetchTime() {
		return lastFetchTime;
	}

	@Lob
	public String getOriginContent() {
		return originContent;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getPublishedTime() {
		return publishedTime;
	}

	@Column(nullable = false)
	public String getTitle() {
		return title;
	}

	@Column(nullable = false, unique = true)
	public String getUrl() {
		return url;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setFeedId(Long feedId) {
		this.feedId = feedId;
	}

	public void setLastFetchTime(Date lastFetchTime) {
		this.lastFetchTime = lastFetchTime;
	}

	public void setOriginContent(String originContent) {
		this.originContent = originContent;
	}

	public void setPublishedTime(Date publishedTime) {
		this.publishedTime = publishedTime;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}