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

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
@Entity
@Table(name = "seeker_account")
public class Account extends IDEntity {
	/**
	 * 用户身份.例如用户名或者电子邮箱.
	 */
	@Size(min = 3, max = 12)
	private String username;

	/**
	 * 身份凭证.一般情况下为密码.
	 */
	@NotBlank
	private String password;

	/**
	 * 账户创建时间.
	 */
	@NotNull
	private Timestamp createTime = new Timestamp(System.currentTimeMillis());

	/**
	 * 账户最后更新时间.
	 */
	@NotNull
	private Timestamp updateTime;

	/**
	 * 是否上锁.
	 */
	@NotNull
	private Boolean locked;

	/**
	 * 电子邮箱.
	 */
	@Email
	private String email;

	/**
	 * 
	 */
	private String salt;

	@Column(nullable = false, updatable = false)
	public Timestamp getCreateTime() {
		return createTime;
	}

	@Column(nullable = false)
	public String getEmail() {
		return email;
	}

	@Column(nullable = false)
	public Boolean getLocked() {
		return locked;
	}

	@Column(nullable = false)
	public String getPassword() {
		return password;
	}

	public String getSalt() {
		return salt;
	}

	@Column(nullable = false)
	public Timestamp getUpdateTime() {
		return updateTime;
	}

	@Column(nullable = false, unique = true, updatable = false)
	public String getUsername() {
		return username;
	}

	public void setCreateTime(final Timestamp createTime) {
		this.createTime = createTime;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setLocked(final Boolean locked) {
		this.locked = locked;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
