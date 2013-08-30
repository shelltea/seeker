/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * 文章条目.
 * 
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
@Entity
@Table(name = "seeker_item")
public class Item extends IDEntity {
	/**
	 * 所属频道.
	 */
	private Long channelId;

	/**
	 * 文章url.
	 */
	private String url;

	/**
	 * 文章Atom url.
	 */
	private String baseUri;

	/**
	 * GUID.
	 */
	private String guid;

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
	 * 更新时间.
	 */
	private Timestamp updatedTime;

	public String getAuthor() {
		return author;
	}

	public String getBaseUri() {
		return baseUri;
	}

	@Column(nullable = false)
	public Long getChannelId() {
		return channelId;
	}

	@Lob
	public String getDescription() {
		return description;
	}

	public String getGuid() {
		return guid;
	}

	@Column(nullable = false)
	public String getTitle() {
		return title;
	}

	public Timestamp getUpdatedTime() {
		return updatedTime;
	}

	@Column(nullable = false)
	public String getUrl() {
		return url;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setBaseUri(String baseUri) {
		this.baseUri = baseUri;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setUpdatedTime(Timestamp updatedTime) {
		this.updatedTime = updatedTime;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
