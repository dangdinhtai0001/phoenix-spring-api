/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/6/21, 11:34 PM
 */

package com.phoenix.api.base.aop;

import com.google.common.collect.Multimap;
import com.phoenix.api.base.component.exception.DefaultHandlerException;
import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.entities.common.ExceptionEntity;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Aspect
@Log4j2
@Component
public class SecurityMethodAspect {

    private final Multimap<String, String> allResourcePermissionRequirement;
    private final List<ExceptionEntity> listExceptions;

    public SecurityMethodAspect(
            @Qualifier(BeanIds.ALL_RESOURCE_PERMISSIONS_REQUIRED) Multimap<String, String> allResourcePermissionRequirement,
            @Qualifier(BeanIds.ALL_EXCEPTION) List<ExceptionEntity> listExceptions
    ) {
        this.allResourcePermissionRequirement = allResourcePermissionRequirement;
        this.listExceptions = listExceptions;
    }

    @Before("execution(* com.phoenix.api.base.services.AbstractCrudService+.*(..))")
    public void checkPermission(JoinPoint joinPoint) throws DefaultHandlerException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String serviceName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        String methodFullName = serviceName + "." + methodName;
        Collection permissionRequirement = allResourcePermissionRequirement.get(methodFullName);

        boolean isDenied = true;
        for (GrantedAuthority simpleGrantedAuthority : authentication.getAuthorities()) {
            if (permissionRequirement.contains(simpleGrantedAuthority.getAuthority())) {
                isDenied = false;
                break;
            }
        }

        if (isDenied) {
            String code = "AUTH_004";
            ExceptionEntity exceptionEntity = findExceptionByCode(code);
            throw new DefaultHandlerException(exceptionEntity.getMessage(), code, this.getClass().getName(),
                    HttpStatus.valueOf(exceptionEntity.getHttpCode()));
        }
    }

    private ExceptionEntity findExceptionByCode(String code) {
        return listExceptions
                .stream()
                .filter(exceptionEntity -> code.equals(exceptionEntity.getCode()))
                .findFirst().orElse(null);
    }
}
