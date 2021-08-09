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
import org.casbin.jcasbin.model.Model;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("ApplicationAuthorizationAspect")
@Aspect
@Log4j2
public class ApplicationAuthorizationAspect extends AbstractBaseService {

    private final Model authorizationModel;
    private final AuthorizationService authorizationService;
    private final List<ExceptionEntity> exceptionEntities;

    public ApplicationAuthorizationAspect(
            @Qualifier(BeanIds.AUTHORIZATION_MODEL) Model authorizationModel,
            @Qualifier(BeanIds.AUTHORIZATION_SERVICES) AuthorizationService authorizationService,
            @Qualifier(BeanIds.ALL_EXCEPTION) List<ExceptionEntity> exceptionEntities) {
        super(exceptionEntities);
        this.authorizationModel = authorizationModel;
        this.authorizationService = authorizationService;
        this.exceptionEntities = exceptionEntities;
    }

    @Before(value = "@within(ApplicationAuthorization) || @annotation(ApplicationAuthorization)")
    public void before(JoinPoint joinPoint) throws ApplicationException {
        String principal = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//        log.info("Principal: " + principal);
//        log.info("monitor.before, class: " + joinPoint.getSignature().getDeclaringType().getSimpleName() + ", method: " + joinPoint.getSignature().getName());


        authorizationService.clearPolicies(authorizationModel);
        authorizationService.loadPolicies(authorizationModel);
        String[] enforceArgs = new String[]{principal, joinPoint.getSignature().getDeclaringType().getSimpleName(), joinPoint.getSignature().getName()};
        boolean isAllow = authorizationService.enforce(authorizationModel, enforceArgs);

        if (!isAllow) {
            throw getApplicationException("AUTH_004");
        }


    }
}
