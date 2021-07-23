package com.phoenix.api.base.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@ToString
public class UserPrincipal implements Serializable {
    private Long id;
    private String username;
    private String password;
    private String hashAlgorithm;
    private String passwordSalt;
    private String status;
    private List<String> groups;
    private List<String> permissions;

    public UserPrincipal(Long id, String username, String password, String hashAlgorithm, String passwordSalt,
                         String status, List<String> groups, List<String> permissions) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.hashAlgorithm = hashAlgorithm;
        this.passwordSalt = passwordSalt;
        this.status = status;
        this.groups = groups;
        this.permissions = permissions;
    }

    public UserPrincipal() {
        this.groups = new LinkedList<>();
        this.permissions = new LinkedList<>();
    }

    public String getPassword() {
        return "{" + hashAlgorithm + "}" + password;
    }
}
