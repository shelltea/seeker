/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 加星的文章条目.
 * 
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
@Entity
@Table(name = "seeker_starred_item")
public class StarredItem extends IDEntity {
	/**
	 * 账户.
	 */
	private Long accountId;

	/**
	 * 文章条目.
	 */
	private Long itemId;

	public Long getAccountId() {
		return accountId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
}
