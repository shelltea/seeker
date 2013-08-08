/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
@Entity
@Table(name = "seeker_account")
public class Account extends IDEntity {
	/**
	 * 用户身份.例如用户名或者电子邮箱.
	 */
	@Size(min = 4)
	protected String principal;

	/**
	 * 身份凭证.一般情况下为密码.
	 */
	protected String credential;

	/**
	 * 账户创建时间.
	 */
	@NotNull
	protected Timestamp createTime = new Timestamp(System.currentTimeMillis());

	/**
	 * 账户最后更新时间.
	 */
	@NotNull
	protected Timestamp updateTime;

	/**
	 * 是否上锁.
	 */
	@NotNull
	protected Boolean locked;

	@Column(nullable = false, updatable = false)
	public Timestamp getCreateTime() {
		return createTime;
	}

	@Column(nullable = false)
	public String getCredential() {
		return credential;
	}

	@Column(nullable = false)
	public Boolean getLocked() {
		return locked;
	}

	@Column(nullable = false, unique = true, updatable = false)
	public String getPrincipal() {
		return principal;
	}

	@Column(nullable = false)
	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setCreateTime(final Timestamp createTime) {
		this.createTime = createTime;
	}

	public void setCredential(final String credential) {
		this.credential = credential;
	}

	public void setLocked(final Boolean locked) {
		this.locked = locked;
	}

	public void setPrincipal(final String principal) {
		this.principal = principal;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
}
