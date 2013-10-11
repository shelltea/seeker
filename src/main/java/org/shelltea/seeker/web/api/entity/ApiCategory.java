/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.web.api.entity;

import org.shelltea.seeker.web.entity.WebIdEntity;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
public class ApiCategory extends WebIdEntity {
	private String title;

	public ApiCategory() {
	}

	public ApiCategory(Long id, String title) {
		this.id = id;
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
