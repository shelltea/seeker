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
    private String logoUrl;
    private Long entryCount;
    private Boolean subscribe;

    public ApiFeed() {
    }

    public ApiFeed(Long id, String title, String logoUrl, Boolean subscribe) {
        this.id = id;
        this.title = title;
        this.logoUrl = logoUrl;
        this.subscribe = subscribe;
    }

    public ApiFeed(Long id, String title, String faviconUrl, Long entryCount) {
        this.id = id;
        this.title = title;
        this.faviconUrl = faviconUrl;
        this.entryCount = entryCount;
    }

    public Long getEntryCount() {
        return entryCount;
    }

    public void setEntryCount(Long entryCount) {
        this.entryCount = entryCount;
    }

    public String getFaviconUrl() {
        return faviconUrl;
    }

    public void setFaviconUrl(String faviconUrl) {
        this.faviconUrl = faviconUrl;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Boolean getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Boolean subscribe) {
        this.subscribe = subscribe;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
