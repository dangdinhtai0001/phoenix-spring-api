/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/6/21, 11:34 PM
 */

package com.phoenix.api.aop;

import com.phoenix.api.component.exception.DefaultHandlerException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityAspect {
    @Before("execution(* com.phoenix.api.services.base.AbstractCrudService+.*(..))")
    public void checkPermission(JoinPoint joinPoint) throws DefaultHandlerException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println(String.format("Method name: %s", joinPoint.getSignature().getName()));
        System.out.println(String.format("Auth name: %s", authentication));
        System.out.println(String.format("Long name: %s", joinPoint.getSignature().toLongString()));
        System.out.println(String.format("Short name: %s", joinPoint.getSignature().toShortString()));
        System.out.println(String.format("Service name: %s", joinPoint.getTarget().getClass().getName()));
        throw new DefaultHandlerException("bla","bla");
    }
}
