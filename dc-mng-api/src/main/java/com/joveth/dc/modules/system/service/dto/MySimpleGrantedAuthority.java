package com.joveth.dc.modules.system.service.dto;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Joveth
 * @date 2022-02-21 17:22
 */
public class MySimpleGrantedAuthority implements GrantedAuthority {

    public MySimpleGrantedAuthority() {
    }

    public MySimpleGrantedAuthority(String role) {
        this.role = role;
    }

    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return this.role;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof MySimpleGrantedAuthority) {
            return this.role.equals(((MySimpleGrantedAuthority) obj).role);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.role.hashCode();
    }

    @Override
    public String toString() {
        return this.role;
    }
}
