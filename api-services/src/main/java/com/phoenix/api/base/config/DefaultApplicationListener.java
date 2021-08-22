package com.phoenix.api.base.config;

import com.phoenix.api.base.constant.ApplicationConstant;
import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.service.ResourceActionService;
import com.phoenix.api.core.annotation.ApplicationResource;
import com.phoenix.api.core.annotation.BusinessObject;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;

import java.lang.annotation.Annotation;
import java.util.LinkedList;
import java.util.List;

@Configuration
@Log4j2
@Getter
@Setter
public class DefaultApplicationListener implements EnvironmentAware {


    private Environment environment;
    private ResourceLoader resourceLoader;
    private ClassLoader classLoader;

    private final ResourceActionService resourceActionService;


    public DefaultApplicationListener(
            @Qualifier(BeanIds.RESOURCE_ACTION_SERVICES)ResourceActionService resourceActionService
    ) {
        this.resourceActionService = resourceActionService;
    }

    @EventListener(classes = {ContextRefreshedEvent.class})
    public void handleContextRefreshEvent() {
        //scanFilterMetadata();
        scanApplicationResources();
    }

    //******************************************************************************************************
    //region private methods
    //******************************************************************************************************

    private void scanFilterMetadata() {
        List<String> listClassName = new LinkedList<>();
        ClassPathScanningCandidateComponentProvider scanner = getScanner();

        AnnotationTypeFilter annotationTypeFilter = new AnnotationTypeFilter(BusinessObject.class);
        scanner.addIncludeFilter(annotationTypeFilter);

        for (BeanDefinition beanDefinition : scanner.findCandidateComponents(ApplicationConstant.BASE_PACKAGE_NAME)) {
            listClassName.add(beanDefinition.getBeanClassName());
        }

//        List result = filterMetadataService.saveDataByListClassName(listClassName);

//        log.info(String.format("Loaded: %d field of %d business Object", result.size(), listClassName.size()));
    }

    private void scanApplicationResources() {
        ClassPathScanningCandidateComponentProvider scanner = getScanner();

        AnnotationTypeFilter annotationTypeFilter = new AnnotationTypeFilter(ApplicationResource.class);
        scanner.addIncludeFilter(annotationTypeFilter);

        List<String> listClassName = new LinkedList<>();
        for (BeanDefinition beanDefinition : scanner.findCandidateComponents(ApplicationConstant.BASE_PACKAGE_NAME)) {
            String className = beanDefinition.getBeanClassName();
            listClassName.add(className);
        }

        List result = resourceActionService.saveDataByListClassName(listClassName);

        log.info(String.format("Loaded: %d application resource action of %d resource.", result.size(), listClassName.size()));
    }

    private ClassPathScanningCandidateComponentProvider getScanner() {

        return new ClassPathScanningCandidateComponentProvider(false, this.environment) {

            @Override
            protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
                if (beanDefinition.getMetadata().isIndependent()) {

                    if (beanDefinition.getMetadata().isInterface()
                            && Annotation.class.getName().equals(beanDefinition.getMetadata().getInterfaceNames()[0])) {
                        try {
                            Class<?> target = ClassUtils.forName(beanDefinition.getMetadata().getClassName(),
                                    DefaultApplicationListener.this.classLoader);
                            return !target.isAnnotation();
                        } catch (Exception ex) {
                            log.error(String.format("Could not load target class: %s", beanDefinition.getMetadata().getClassName()), ex);
                        }
                    }
                    return true;
                }
                return false;

            }
        };
    }
}
