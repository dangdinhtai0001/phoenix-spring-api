/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/6/21, 11:34 PM
 */

package com.phoenix.api.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityAspect {
    @Before("execution(* com.phoenix.api.services.base.AbstractCrudService.*(..))")
    public void logBeforeAllMethods(JoinPoint joinPoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("----------------------------------------------------------------------------------------");
    }
}
