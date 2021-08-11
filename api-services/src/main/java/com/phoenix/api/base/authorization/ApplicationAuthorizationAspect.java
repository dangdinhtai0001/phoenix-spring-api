package com.phoenix.api.base.authorization;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.entities.ExceptionEntity;
import com.phoenix.api.base.service.AuthorizationService;
import com.phoenix.api.core.exception.ApplicationException;
import com.phoenix.api.core.service.AbstractBaseService;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.casbin.jcasbin.main.Enforcer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("ApplicationAuthorizationAspect")
@Aspect
@Log4j2
public class ApplicationAuthorizationAspect extends AbstractBaseService {

    private final Enforcer authorizationEnforcer;
    private final AuthorizationService authorizationService;

    public ApplicationAuthorizationAspect(
            @Qualifier(BeanIds.AUTHORIZATION_ENFORCE) Enforcer authorizationEnforcer,
            @Qualifier(BeanIds.AUTHORIZATION_SERVICES) AuthorizationService authorizationService,
            @Qualifier(BeanIds.ALL_EXCEPTION) List<ExceptionEntity> exceptionEntities
    ) {
        super(exceptionEntities);
        this.authorizationEnforcer = authorizationEnforcer;
        this.authorizationService = authorizationService;
    }

    @Before(value = "@within(com.phoenix.api.core.annotation.ApplicationAuthorization) || @annotation(com.phoenix.api.core.annotation.ApplicationAuthorization)")
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
            throw getApplicationException("AUTH_004");
        }

    }
}
