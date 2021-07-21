package com.phoenix.api.core.service;

import com.phoenix.api.base.entities.ExceptionEntity;
import com.phoenix.api.core.exception.ServiceException;

public interface BaseService {
    ServiceException getServiceException(String code);

    ExceptionEntity findExceptionByCode(String code);
}
