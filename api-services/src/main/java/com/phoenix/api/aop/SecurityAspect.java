/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/6/21, 11:34 PM
 */

package com.phoenix.api.aop;

import com.phoenix.api.component.exception.DefaultHandlerException;
import com.phoenix.api.constant.BeanIds;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Aspect
@Component
public class SecurityAspect {

    private final HashMap<String, String> allResourcePermissionRequirement;

    public SecurityAspect(
            @Qualifier(BeanIds.ALL_RESOURCE_PERMISSIONS_REQUIRED) HashMap<String, String> allResourcePermissionRequirement
    ) {
        this.allResourcePermissionRequirement = allResourcePermissionRequirement;
    }

    @Before("execution(* com.phoenix.api.services.base.AbstractCrudService+.*(..))")
    public void checkPermission(JoinPoint joinPoint) throws DefaultHandlerException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String serviceName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        String methodFullName = serviceName + "." + methodName;


        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println(String.format("Method name: %s", methodName));
        System.out.println(String.format("Auth name: %s", authentication));
        System.out.println(String.format("Service name: %s", serviceName));
        System.out.println(String.format("Method full name: %s", methodFullName));
        System.out.println(String.format("Permission required: %s", allResourcePermissionRequirement.get(methodFullName)));



        throw new DefaultHandlerException("bla", "bla");
    }
}
