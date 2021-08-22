package com.phoenix.api.base.service.imp;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.entities.ExceptionEntity;
import com.phoenix.api.base.entities.ResourceActionEntity;
import com.phoenix.api.base.repositories.ResourceActionRepository;
import com.phoenix.api.base.service.ResourceActionService;
import com.phoenix.api.core.repository.specification.Specifications;
import com.phoenix.api.core.service.AbstractBaseService;
import com.phoenix.common.util.ReflectionUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service(BeanIds.RESOURCE_ACTION_SERVICES)
@Log4j2
public class ResourceActionServiceImp extends AbstractBaseService implements ResourceActionService {
    private final ResourceActionRepository resourceActionRepository;

    protected ResourceActionServiceImp(
            @Qualifier(BeanIds.ALL_EXCEPTION) List<ExceptionEntity> exceptionEntities,
            @Qualifier(BeanIds.RESOURCE_ACTION_REPOSITORY_IMP) ResourceActionRepository resourceActionRepository
    ) {
        super(exceptionEntities);
        this.resourceActionRepository = resourceActionRepository;
    }

    @Override
    public List saveDataByListClassName(List<String> listClassName) {
        List<ResourceActionEntity> resourceActionList = new LinkedList<>();
        List<String> allMethodsNamesList = new LinkedList<>();
        Class aClass;
        List<String> methodsName;

        for (String className : listClassName) {
            try {
                aClass = Class.forName(className);
                methodsName = ReflectionUtil.getAllDeclaredMethodsMethodNames(aClass);

                allMethodsNamesList.addAll(methodsName);

                for (String methodName : methodsName) {
                    resourceActionList.add(getResourceAction(className, methodName, null));
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        Specification<ResourceActionEntity> specification = Specifications.<ResourceActionEntity>and()
                .in("resource", listClassName)
                .in("action", allMethodsNamesList)
                .build();

        List<ResourceActionEntity> exitsResourceAction = resourceActionRepository.findAll(specification);

        resourceActionList.removeAll(exitsResourceAction);

        return resourceActionRepository.saveAllAndFlush(resourceActionList);
    }

    //******************************************************************************************************
    //******************************** region private methods
    //******************************************************************************************************

    private ResourceActionEntity getResourceAction(String resource, String action, String description) {
        ResourceActionEntity resourceAction = new ResourceActionEntity();

        resourceAction.setResource(resource);
        resourceAction.setAction(action);
        resourceAction.setDescription(description);

        return resourceAction;
    }
}
