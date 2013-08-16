/*
 * Copyright (C) CCRISE.
 */
package org.shelltea.seeker.web.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
public class WebEntity {
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
