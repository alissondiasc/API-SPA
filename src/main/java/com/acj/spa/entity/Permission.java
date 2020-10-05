package br.com.se.entity.core.permissions;

import org.springframework.security.core.GrantedAuthority;

public class Permission implements GrantedAuthority {

    private String permission;

    public Permission() {
    }

    public Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }



    @Override
    public String getAuthority() {
        return getPermission();
    }
}
