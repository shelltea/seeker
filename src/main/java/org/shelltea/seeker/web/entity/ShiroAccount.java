/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.web.entity;

import java.io.Serializable;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
public class ShiroAccount extends WebEntity implements Serializable {
	private static final long serialVersionUID = -3482500760998201817L;
	private long id;
	private String username;
	private String email;
	private String gravatarURL;

	public ShiroAccount(long id, String username, String email) {
		this.id = id;
		this.username = username;
		this.email = email;
		gravatarURL = "http://www.gravatar.com/avatar/" + new SimpleHash("MD5", email.trim().toLowerCase()).toString()
				+ "?d=mm&s=200";
	}

	public String getEmail() {
		return email;
	}

	public String getGravatarURL() {
		return gravatarURL;
	}

	public long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setGravatarURL(String gravatarURL) {
		this.gravatarURL = gravatarURL;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
