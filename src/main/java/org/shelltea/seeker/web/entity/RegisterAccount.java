/*
 * Copyright (C) CCRISE.
 */
package org.shelltea.seeker.web.entity;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
public class RegisterAccount extends WebEntity {
	@NotBlank
	@Email
	private String email;
	@NotBlank
	@Size(min = 3, max = 12)
	private String username;
	@NotBlank
	private String password;

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
