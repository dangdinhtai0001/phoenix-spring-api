package com.phoenix.base.service;

import com.phoenix.base.constant.BeanIds;
import com.phoenix.core.model.DefaultAuthenticationToken;
import com.phoenix.core.model.DefaultException;
import com.phoenix.core.service.AbstractCoreService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BaseService extends AbstractCoreService {
    private final ApplicationContext appContext;

    protected BaseService(ApplicationContext applicationContext) {
        this.appContext = applicationContext;
    }

    @Override
    public DefaultAuthenticationToken getCurrentSecurityToken() {
        return null;
    }

    @Override
    public List<DefaultException> getExceptions() {
        return (List<DefaultException>) appContext.getBean(BeanIds.ALL_EXCEPTION);
    }
}
