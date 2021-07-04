/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/3/21, 10:13 PM
 */

package com.phoenix.api.services.auth;

import com.phoenix.api.constant.BeanIds;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Iterator;

@Service(BeanIds.PERMISSION_EVALUATOR)
public class DefaultEvaluatorService implements PermissionEvaluator {
    @Override
    public boolean hasPermission(Authentication authentication, Object o, Object o1) {
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }


}
