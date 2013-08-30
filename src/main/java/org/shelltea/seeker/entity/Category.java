/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import com.google.common.collect.Lists;

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
	@NotBlank
	private String title;

	/**
	 * 子分类.
	 */
	private List<Category> categories = Lists.newArrayList();;

	/**
	 * 包含的频道.
	 */
	private List<Channel> channels = Lists.newArrayList();;

	/**
	 * 顺序.
	 */
	private Integer sequence;

	@Column(nullable = false)
	public Long getAccountId() {
		return accountId;
	}

	@OneToMany
	public List<Category> getCategories() {
		return categories;
	}

	@OneToMany
	public List<Channel> getChannels() {
		return channels;
	}

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

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public void setChannels(List<Channel> channels) {
		this.channels = channels;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
