/*
 *  @Author Đặng Đình Tài
 *  @Created date: 6/20/21, 9:10 PM
 */

package com.phoenix.api.model.auth;

import com.phoenix.api.constant.ApplicationConstant;
import javafx.application.Application;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
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
        return !userPrincipal.getListStatus().contains(ApplicationConstant.USER_DETAILS_STATUS_EXPIRED);
    }

    @Override
    public boolean isAccountNonLocked() {
        return !userPrincipal.getListStatus().contains(ApplicationConstant.USER_DETAILS_STATUS_LOCKED);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return userPrincipal.getListStatus().contains(ApplicationConstant.USER_DETAILS_STATUS_ENABLED);
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
                "status=" + userPrincipal.getListStatus()  + ", " +
                "authorities=" + userPrincipal.getPermissions() +
                '}';
    }
}
