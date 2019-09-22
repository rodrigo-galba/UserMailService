package me.rodrigogalba.controller.template;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AdminPermission {

    @NotNull
    Long userId;

    @NotNull
    Boolean admin;

    public Boolean isAdmin() {
        return admin;
    }

}
