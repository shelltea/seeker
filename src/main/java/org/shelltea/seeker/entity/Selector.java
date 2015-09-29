/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
@Entity
@Table(name = "seeker_selector")
public class Selector extends IdEntity {
    private String cssQuery;

    public Selector() {
    }

    public Selector(String cssQuery) {
        this.cssQuery = cssQuery;
    }

    @Column(nullable = false)
    public String getCssQuery() {
        return cssQuery;
    }

    public void setCssQuery(String cssQuery) {
        this.cssQuery = cssQuery;
    }
}
