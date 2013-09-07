/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.common.collect.Sets;

/**
 * 频道.
 * 
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
@Entity
@Table(name = "seeker_channel")
public class Channel extends IDEntity {
	/**
	 * 标题.
	 */
	private String title;

	/**
	 * 图标地址.
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

	@Lob
	public String getDescription() {
		return description;
	}

	@OneToMany
	public Set<Feed> getFeeds() {
		return feeds;
	}

	@Column(nullable = false, unique = true)
	public String getIconUrl() {
		return iconUrl;
	}

	@Column(nullable = false, unique = true)
	public String getTitle() {
		return title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setFeeds(Set<Feed> feeds) {
		this.feeds = feeds;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
