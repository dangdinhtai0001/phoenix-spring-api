/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 9:25 PM
 */

/*
 * @Author Đặng Đình Tài
 * @Date 6/29/21, 10:04 AM
 */

package com.phoenix.api.base.services;

import com.phoenix.api.base.component.exception.DefaultHandlerException;
import com.phoenix.api.entities.common.ExceptionEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * Mặc định cách throw exception.
 *
 * Các controller sẽ tự động response nếu ngoại lệ DefaultHandlerException đc ném ra
 */
public abstract class AbstractBaseService {
    private final List<ExceptionEntity> exceptionEntities;

    protected AbstractBaseService(List<ExceptionEntity> exceptionEntities) {
        this.exceptionEntities = exceptionEntities;
    }

    protected DefaultHandlerException getDefaultException(String code) {
        ExceptionEntity exceptionEntity = findExceptionByCode(code);

        return new DefaultHandlerException(exceptionEntity.getMessage(), code, this.getClass().getName(),
                HttpStatus.valueOf(exceptionEntity.getHttpCode()));
    }

    private ExceptionEntity findExceptionByCode(String code) {
        return exceptionEntities
                .stream()
                .filter(exceptionEntity -> code.equals(exceptionEntity.getCode()))
                .findFirst().orElse(null);
    }
}
