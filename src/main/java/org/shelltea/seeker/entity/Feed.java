/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.common.collect.Sets;

/**
 * 订阅源.
 * 
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
@Entity
@Table(name = "seeker_feed")
public class Feed extends IdEntity {
	/**
	 * 网站地址.
	 */
	private String url;

	/**
	 * Favicon地址.
	 */
	private String faviconUrl;

	/**
	 * Logo地址.
	 */
	private String logoUrl;

	/**
	 * 列表抓取地址.
	 */
	private String fetchUrl;

	/**
	 * 文章条目url前缀
	 */
	private String entryUrlPrefix;

	/**
	 * 标题.
	 */
	private String title;

	/**
	 * 描述.
	 */
	private String description;

	/**
	 * 创建时间.
	 */
	private Date createTime = new Date(System.currentTimeMillis());

	/**
	 * 最后抓取时间.
	 */
	private Date lastFetchTime;

	/**
	 * 列表选择器.
	 */
	private String listSelector;

	/**
	 * 标题选择器.
	 */
	private String titleSelector;

	/**
	 * 内容选择器.
	 */
	private String originContentSelector;

	/**
	 * 发布时间选择器.
	 */
	private String publishedTimeSelector;

	/**
	 * 发布时间格式.
	 */
	private String publishedTimePattern;

	/**
	 * 来源选择器.
	 */
	private String authorSelector;

	/**
	 * 移除选择器.
	 */
	private Set<Selector> removeSelectors = Sets.newHashSet();

	public String getAuthorSelector() {
		return authorSelector;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	public Date getCreateTime() {
		return createTime;
	}

	@Lob
	public String getDescription() {
		return description;
	}

	@Column(nullable = false)
	public String getEntryUrlPrefix() {
		return entryUrlPrefix;
	}

	public String getFaviconUrl() {
		return faviconUrl;
	}

	@Column(nullable = false, unique = true)
	public String getFetchUrl() {
		return fetchUrl;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getLastFetchTime() {
		return lastFetchTime;
	}

	@Column(nullable = false)
	public String getListSelector() {
		return listSelector;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	@Column(nullable = false)
	public String getOriginContentSelector() {
		return originContentSelector;
	}

	public String getPublishedTimePattern() {
		return publishedTimePattern;
	}

	public String getPublishedTimeSelector() {
		return publishedTimeSelector;
	}

	@OneToMany(fetch = FetchType.EAGER)
	public Set<Selector> getRemoveSelectors() {
		return removeSelectors;
	}

	@Column(nullable = false)
	public String getTitle() {
		return title;
	}

	public String getTitleSelector() {
		return titleSelector;
	}

	@Column(nullable = false, unique = true)
	public String getUrl() {
		return url;
	}

	public void setAuthorSelector(String authorSelector) {
		this.authorSelector = authorSelector;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setEntryUrlPrefix(String entryUrlPrefix) {
		this.entryUrlPrefix = entryUrlPrefix;
	}

	public void setFaviconUrl(String faviconUrl) {
		this.faviconUrl = faviconUrl;
	}

	public void setFetchUrl(String fetchUrl) {
		this.fetchUrl = fetchUrl;
	}

	public void setLastFetchTime(Date lastFetchTime) {
		this.lastFetchTime = lastFetchTime;
	}

	public void setListSelector(String listSelector) {
		this.listSelector = listSelector;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public void setOriginContentSelector(String originContentSelector) {
		this.originContentSelector = originContentSelector;
	}

	public void setPublishedTimePattern(String publishedTimePattern) {
		this.publishedTimePattern = publishedTimePattern;
	}

	public void setPublishedTimeSelector(String publishedTimeSelector) {
		this.publishedTimeSelector = publishedTimeSelector;
	}

	public void setRemoveSelectors(Set<Selector> removeSelectors) {
		this.removeSelectors = removeSelectors;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setTitleSelector(String titleSelector) {
		this.titleSelector = titleSelector;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
