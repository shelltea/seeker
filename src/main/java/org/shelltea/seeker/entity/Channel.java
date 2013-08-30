/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 频道.
 * 
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
@Entity
@Table(name = "seeker_channel")
public class Channel extends IDEntity {
	/**
	 * 网站地址.
	 */
	private String url;

	/**
	 * RSS地址.
	 */
	@NotBlank
	private String rssUrl;

	/**
	 * Atom地址.
	 */
	private String baseUri;

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
	private Timestamp createTime = new Timestamp(System.currentTimeMillis());

	/**
	 * 最后抓取时间.
	 */
	private Timestamp lastFetchTime;

	public String getBaseUri() {
		return baseUri;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	@Lob
	public String getDescription() {
		return description;
	}

	public Timestamp getLastFetchTime() {
		return lastFetchTime;
	}

	@Column(nullable = false)
	public String getRssUrl() {
		return rssUrl;
	}

	public String getTitle() {
		return title;
	}

	public String getUrl() {
		return url;
	}

	public void setBaseUri(String baseUri) {
		this.baseUri = baseUri;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLastFetchTime(Timestamp lastFetchTime) {
		this.lastFetchTime = lastFetchTime;
	}

	public void setRssUrl(String rssUrl) {
		this.rssUrl = rssUrl;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
