/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 已读的文章条目.
 * 
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
@Entity
@Table(name = "seeker_read_entry")
public class ReadEntry extends IdEntity {
	/**
	 * 账户.
	 */
	private Long accountId;

	/**
	 * 文章条目.
	 */
	private Long entryId;

	@Column(nullable = false)
	public Long getAccountId() {
		return accountId;
	}

	@Column(nullable = false)
	public Long getEntryId() {
		return entryId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public void setEntryId(Long entryId) {
		this.entryId = entryId;
	}
}
