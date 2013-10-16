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
	private Long id;
	private String username;
	private String email;
	private String gravatarURL;

	public ShiroAccount(Long id, String username, String email) {
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

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setEmail(String email) {
		gravatarURL = "http://www.gravatar.com/avatar/" + new SimpleHash("MD5", email.trim().toLowerCase()).toString()
				+ "?d=mm&s=200";
		this.email = email;
	}

	public void setGravatarURL(String gravatarURL) {
		this.gravatarURL = gravatarURL;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
