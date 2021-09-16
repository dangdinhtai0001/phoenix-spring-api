package com.phoenix.base.aop;

import com.phoenix.base.constant.BeanIds;
import com.phoenix.base.service.AuthorizationService;
import com.phoenix.base.service.BaseService;
import com.phoenix.core.config.DefaultExceptionCode;
import com.phoenix.core.exception.ApplicationException;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.casbin.jcasbin.main.Enforcer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("ApplicationAuthorizationAspect")
@Aspect
@Log4j2
public class ApplicationAuthorizationAspect extends BaseService {

    private final Enforcer authorizationEnforcer;
    private final AuthorizationService authorizationService;

    protected ApplicationAuthorizationAspect(
            ApplicationContext applicationContext,
            @Qualifier(BeanIds.AUTHORIZATION_ENFORCE) Enforcer authorizationEnforcer,
            @Qualifier(BeanIds.AUTHORIZATION_SERVICES) AuthorizationService authorizationService
    ) {
        super(applicationContext);
        this.authorizationEnforcer = authorizationEnforcer;
        this.authorizationService = authorizationService;
    }

    @Before(value = "@within(com.phoenix.core.annotation.ApplicationAuthorization) || @annotation(com.phoenix.core.annotation.ApplicationAuthorization)")
    public void before(JoinPoint joinPoint) throws ApplicationException {
        //log.info("monitor.before, class: " + joinPoint.getSignature().getDeclaringType().getName() + ", method: " + joinPoint.getSignature().getName());

        String principal = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        String resource = joinPoint.getSignature().getDeclaringType().getName();
        String action = joinPoint.getSignature().getName();
        boolean isAllow = false;

        try {
            authorizationService.clearPolicies(authorizationEnforcer);
            authorizationService.loadPolicies(authorizationEnforcer);

            String[] enforceArgs = new String[]{principal, resource, action};
            isAllow = authorizationService.enforce(authorizationEnforcer, enforceArgs);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }

        if (!isAllow) {
            throw getApplicationException(DefaultExceptionCode.ACCESS_DENIED);
        }

    }
}
