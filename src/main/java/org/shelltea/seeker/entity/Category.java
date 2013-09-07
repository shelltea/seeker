/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.common.collect.Sets;

/**
 * 分类.
 * 
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
@Entity
@Table(name = "seeker_category")
public class Category extends IDEntity {
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

	@OneToMany
	public Set<Category> getCategories() {
		return categories;
	}

	@OneToMany
	public Set<Feed> getFeeds() {
		return feeds;
	}

	@Column(nullable = false)
	public Integer getSequence() {
		return sequence;
	}

	@Column(nullable = false)
	public String getTitle() {
		return title;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public void setFeeds(Set<Feed> feeds) {
		this.feeds = feeds;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
