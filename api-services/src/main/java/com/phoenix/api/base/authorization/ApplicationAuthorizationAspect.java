package com.phoenix.api.base.authorization;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Log4j2
public class ApplicationAuthorizationAspect {

    @Before(value = "@within(ApplicationAuthorization) || @annotation(ApplicationAuthorization)")
    public void before(JoinPoint joinPoint) {
        String principal = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        log.info("Principal: " + principal);
        log.info("monitor.before, class: " + joinPoint.getSignature().getDeclaringType().getSimpleName() + ", method: " + joinPoint.getSignature().getName());
    }
}
