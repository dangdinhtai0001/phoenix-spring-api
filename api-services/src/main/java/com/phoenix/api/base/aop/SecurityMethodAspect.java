/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/6/21, 11:34 PM
 */

package com.phoenix.api.base.aop;

import com.google.common.collect.Multimap;
import com.phoenix.api.base.component.exception.DefaultHandlerException;
import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.entities.common.ExceptionEntity;
import com.phoenix.api.model.auth.DefaultUserDetails;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
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

    @Around(value = "execution(* com.phoenix.api.base.services.AbstractCrudService+.*(..)) || " +
            "@within(CheckMethodPermission) || " +
            "@annotation(CheckMethodPermission)")
    public Object checkPermission(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isDenied = checkPermissionRequired(proceedingJoinPoint, authentication);

        if (isDenied) {
            String code = "AUTH_004";
            ExceptionEntity exceptionEntity = findExceptionByCode(code);
            throw new DefaultHandlerException(exceptionEntity.getMessage(), code, this.getClass().getName(),
                    HttpStatus.valueOf(exceptionEntity.getHttpCode()));
        }


        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        //String[] parametersName = signature.getParameterNames();

        Method method = signature.getMethod();
        NeedFilter needFilter = method.getAnnotation(NeedFilter.class);
        if (needFilter != null) {
            int index = needFilter.indexOfFilterConditionParameter();
            Object[] args = proceedingJoinPoint.getArgs();

            // todo: làm động số tham số truyền vào
            args[index] = String.format("where fu.username = '%s'", authentication.getPrincipal());
            return proceedingJoinPoint.proceed(args);
        }
        return proceedingJoinPoint.proceed();
    }

    private ExceptionEntity findExceptionByCode(String code) {
        return listExceptions
                .stream()
                .filter(exceptionEntity -> code.equals(exceptionEntity.getCode()))
                .findFirst().orElse(null);
    }


    private boolean checkPermissionRequired(ProceedingJoinPoint proceedingJoinPoint, Authentication authentication) {
        String serviceName = proceedingJoinPoint.getTarget().getClass().getName();
        String methodName = proceedingJoinPoint.getSignature().getName();
        String methodFullName = serviceName + "." + methodName;
        Collection permissionRequirement = allResourcePermissionRequirement.get(methodFullName);

        boolean isDenied = true;
        for (GrantedAuthority simpleGrantedAuthority : authentication.getAuthorities()) {
            if (permissionRequirement.contains(simpleGrantedAuthority.getAuthority())) {
                isDenied = false;
                break;
            }
        }

        return isDenied;
    }
}
