/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.web.entity;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Size;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
public class UpdateAccount extends WebEntity {
    @Size(min = 6, max = 16)
    private String oldPassword;
    @Size(min = 6, max = 16)
    private String newPassword;
    @Size(min = 6, max = 16)
    private String confirmPassword;
    @Email
    private String email;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
