/*
 * @Author: Đặng Đình Tài
 * @Created_date: 6/23/21, 9:28 PM
 */

package com.phoenix.api.component.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
@AllArgsConstructor
public class DefaultGrantedAuthority implements GrantedAuthority {
    private String role;
    private List<String> permissions;
    @Override
    public String getAuthority() {
        return this.role;
    }
}
