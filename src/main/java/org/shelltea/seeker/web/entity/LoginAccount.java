/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.web.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
public class LoginAccount extends WebEntity {
    @NotBlank
    @Size(min = 3, max = 12)
    private String username;
    @NotBlank
    @Size(min = 6, max = 16)
    private String password;
    private String rememberMe;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(String rememberMe) {
        this.rememberMe = rememberMe;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
