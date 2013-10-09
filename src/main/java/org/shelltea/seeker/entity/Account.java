/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 账户.
 * 
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
@Entity
@Table(name = "seeker_account")
public class Account extends IDEntity implements Serializable {
	private static final long serialVersionUID = -3651803052145540378L;

	/**
	 * 用户身份.例如用户名或者电子邮箱.
	 */
	private String username;

	/**
	 * 身份凭证.一般情况下为密码.
	 */
	private String password;

	/**
	 * 账户创建时间.
	 */
	private Date createTime = new Date(System.currentTimeMillis());

	/**
	 * 账户最后更新时间：登录/修改账户信息.
	 */
	private Date updateTime;

	/**
	 * 是否上锁.
	 */
	private Boolean locked;

	/**
	 * 电子邮箱.
	 */
	private String email;

	/**
	 * Salt.
	 */
	private String salt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, updatable = false)
	public Date getCreateTime() {
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

	@Column(nullable = false)
	public String getSalt() {
		return salt;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdateTime() {
		return updateTime;
	}

	@Column(nullable = false, unique = true, updatable = false)
	public String getUsername() {
		return username;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
