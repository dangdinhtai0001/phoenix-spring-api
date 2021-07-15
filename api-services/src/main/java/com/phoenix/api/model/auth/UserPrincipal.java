/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

package com.phoenix.api.model.auth;

import lombok.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@ToString
public class UserPrincipal implements Serializable {
    private Long id;
    private String username;
    private String password;
    private String hashAlgorithm;
    private String passwordSalt;
    private int permission;
    private int status;
    private String group;

    private Set<String> permissions;
    private List<String> listStatus;
    private final List<String> groups;


    public UserPrincipal(Long id, String username, String password, String hashAlgorithm, String passwordSalt, int permission, int status, String group) {
        this.id = id;
        this.username = username;
        this.hashAlgorithm = hashAlgorithm;
        this.password = "{" + hashAlgorithm + "}" + password;
        this.passwordSalt = passwordSalt;
        this.permission = permission;
        this.status = status;
        this.group = group;

        this.groups = Arrays.asList(this.group.replaceAll("\\s+", "").split(","));
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHashAlgorithm(String hashAlgorithm) {
        this.hashAlgorithm = hashAlgorithm;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getHashAlgorithm() {
        return hashAlgorithm;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public int getPermission() {
        return permission;
    }

    public int getStatus() {
        return status;
    }

    public String getGroup() {
        return group;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public List<String> getListStatus() {
        return listStatus;
    }

    public List<String> getGroups() {
        return groups;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    public void setListStatus(List<String> listStatus) {
        this.listStatus = listStatus;
    }
}
