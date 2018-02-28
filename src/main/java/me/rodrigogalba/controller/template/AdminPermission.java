package me.rodrigogalba.controller.template;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class AdminPermission {

    @NotNull
    Long userId;

    @NotNull
    Boolean admin;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean isAdmin() {
        return admin;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
