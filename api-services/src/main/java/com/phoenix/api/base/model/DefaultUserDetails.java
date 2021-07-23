package com.phoenix.api.base.model;

import com.phoenix.api.base.constant.ApplicationConstant;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class DefaultUserDetails implements UserDetails {
    private final UserPrincipal userPrincipal;

    public DefaultUserDetails(UserPrincipal userPrincipal) {
        this.userPrincipal = userPrincipal;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<GrantedAuthority> authorities = new HashSet<>();

        for (String permission : userPrincipal.getPermissions()) {
            authorities.add(new SimpleGrantedAuthority(permission));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return userPrincipal.getPassword();
    }

    @Override
    public String getUsername() {
        return userPrincipal.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !userPrincipal.getStatus().equals(ApplicationConstant.USER_DETAILS_STATUS_EXPIRED);
    }

    @Override
    public boolean isAccountNonLocked() {
        return !userPrincipal.getStatus().equals(ApplicationConstant.USER_DETAILS_STATUS_LOCKED);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return userPrincipal.getStatus().equals(ApplicationConstant.USER_DETAILS_STATUS_ENABLED);
    }

    public Long getId() {
        return userPrincipal.getId();
    }

    @Override
    public String toString() {
        return "DefaultUserDetails{" +
                "id=" + userPrincipal.getId() + ", " +
                "username=" + userPrincipal.getUsername() + ", " +
                "password=" + userPrincipal.getPassword() + ", " +
                "hashing algorithm=" + userPrincipal.getHashAlgorithm() + ", " +
                "status=" + userPrincipal.getStatus() + ", " +
                "authorities=" + userPrincipal.getPermissions() +
                '}';
    }
}
