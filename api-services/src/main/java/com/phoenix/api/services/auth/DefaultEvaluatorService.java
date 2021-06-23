/*
 * @Author: Đặng Đình Tài
 * @Created_date: 6/23/21, 9:29 PM
 */

package com.phoenix.api.services.auth;

import com.phoenix.api.component.security.DefaultGrantedAuthority;
import com.phoenix.api.constant.BeanIds;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service(BeanIds.DEFAULT_EVALUATOR_SERVICE)
public class DefaultEvaluatorService implements PermissionEvaluator {
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if ((authentication == null) || (targetDomainObject == null) || !(permission instanceof String)) {
            return false;
        }
        return hasPrivilege(authentication, targetDomainObject.toString().toUpperCase(), permission.toString().toUpperCase());
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        if ((authentication == null) || (targetType == null) || !(permission instanceof String)) {
            return false;
        }
        return hasPrivilege(authentication, targetType.toUpperCase(), permission.toString().toUpperCase());
    }


    private boolean hasPrivilege(Authentication auth, String targetType, String permission) {
        for (DefaultGrantedAuthority grantedAuth : (List<DefaultGrantedAuthority>) auth.getAuthorities()) {
            for (String grantedPermission : grantedAuth.getPermissions()) {
                if (grantedPermission.startsWith(targetType) && grantedPermission.contains(permission)) {
                    return true;
                }
            }
        }
        return false;
    }
}
