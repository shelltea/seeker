/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.web.entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

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
    @Size(min = 6, max = 16)
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
