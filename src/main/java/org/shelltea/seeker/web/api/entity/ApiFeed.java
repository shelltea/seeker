/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.web.api.entity;

import org.shelltea.seeker.web.entity.WebIdEntity;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
public class ApiFeed extends WebIdEntity {
	private String title;
	private String faviconUrl;
	private Long entryCount;

	public Long getEntryCount() {
		return entryCount;
	}

	public String getFaviconUrl() {
		return faviconUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setEntryCount(Long entryCount) {
		this.entryCount = entryCount;
	}

	public void setFaviconUrl(String faviconUrl) {
		this.faviconUrl = faviconUrl;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
