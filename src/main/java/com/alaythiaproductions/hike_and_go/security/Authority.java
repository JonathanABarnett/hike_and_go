package com.alaythiaproductions.hike_and_go.security;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {

    private final String authority;

    public Authority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
