package com.phoenix.api.base.config;

import com.phoenix.api.base.constant.ApplicationConstant;
import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.service.FilterMetadataService;
import com.phoenix.api.core.model.DomainObject;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class DefaultApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    private final FilterMetadataService filterMetadataService;

    public DefaultApplicationListener(
            @Qualifier(BeanIds.FILTER_METADATA_SERVICES) FilterMetadataService filterMetadataService) {
        this.filterMetadataService = filterMetadataService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.info("-------------- start");

        ClassPathScanningCandidateComponentProvider scanner =
                new ClassPathScanningCandidateComponentProvider(true);

        scanner.addExcludeFilter(new AnnotationTypeFilter(Component.class));
        scanner.addIncludeFilter(new AnnotationTypeFilter(DomainObject.class));

        String className;
        for (BeanDefinition beanDefinition : scanner.findCandidateComponents(ApplicationConstant.BASE_PACKAGE_NAME)) {
            className = beanDefinition.getBeanClassName();
            filterMetadataService.saveDataByClassName(className);
        }


    }
}
