/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.web.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
@JsonInclude(Include.NON_NULL)
public class WebEntity {
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
