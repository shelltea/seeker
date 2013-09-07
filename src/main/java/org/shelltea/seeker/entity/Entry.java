/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.entity;

import java.sql.Timestamp;
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
	 * 描述.
	 */
	private String description;

	/**
	 * 原内容.
	 */
	private String originContent;

	/**
	 * 处理后的内容.
	 */
	private String content;

	/**
	 * 抓取时间.
	 */
	private Date fetchTime;

	/**
	 * 发布时间.
	 */
	private Date publishedTime;

	/**
	 * 更新时间.
	 */
	private Date updatedTime;

	public String getAuthor() {
		return author;
	}

	@Lob
	public String getContent() {
		return content;
	}

	@Lob
	public String getDescription() {
		return description;
	}

	@Column(nullable = false)
	public Long getFeedId() {
		return feedId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getFetchTime() {
		return fetchTime;
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

	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdatedTime() {
		return updatedTime;
	}

	@Column(nullable = false)
	public String getUrl() {
		return url;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setFeedId(Long feedId) {
		this.feedId = feedId;
	}

	public void setFetchTime(Date fetchTime) {
		this.fetchTime = fetchTime;
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

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public void setUpdatedTime(Timestamp updatedTime) {
		this.updatedTime = updatedTime;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
