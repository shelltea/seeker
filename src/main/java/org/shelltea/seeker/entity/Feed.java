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
 * 订阅源.
 * 
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
@Entity
@Table(name = "seeker_feed")
public class Feed extends IDEntity {
	/**
	 * 网站地址.
	 */
	private String url;

	/**
	 * 抓取地址.
	 */
	private String fetchUrl;

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
	 * 内容选择器.
	 */
	private String contentSelector;

	@Column(nullable = false)
	public String getContentSelector() {
		return contentSelector;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	public Date getCreateTime() {
		return createTime;
	}

	@Lob
	public String getDescription() {
		return description;
	}

	@Column(nullable = false, unique = true)
	public String getFetchUrl() {
		return fetchUrl;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getLastFetchTime() {
		return lastFetchTime;
	}

	@Column(nullable = false)
	public String getListSelector() {
		return listSelector;
	}

	@Column(nullable = false)
	public String getTitle() {
		return title;
	}

	@Column(nullable = false, unique = true)
	public String getUrl() {
		return url;
	}

	public void setContentSelector(String contentSelector) {
		this.contentSelector = contentSelector;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setFetchUrl(String fetchUrl) {
		this.fetchUrl = fetchUrl;
	}

	public void setLastFetchTime(Date lastFetchTime) {
		this.lastFetchTime = lastFetchTime;
	}

	public void setListSelector(String listSelector) {
		this.listSelector = listSelector;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
