package com.phoenix.api.base.config;

import com.phoenix.api.base.constant.BeanIds;
import lombok.extern.log4j.Log4j2;
import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
@Log4j2
public class DroolsConfiguration {
    private static final String RULES_PATH = "drools/";

    private final ConcurrentHashMap<String, KieContainer> containerMap = new ConcurrentHashMap<>();

    @Bean(BeanIds.KIE_CONTAINER)
    public ConcurrentHashMap<String, KieContainer> getKieContainerMap() throws IOException {
        for (Resource file : getRuleFiles()) {
            KieFileSystem kieFileSystem = kieServices().newKieFileSystem();
            kieFileSystem.write(ResourceFactory.newClassPathResource(RULES_PATH + file.getFilename(), "UTF-8"));

            KieContainer kieContainer = getKieContainer(kieFileSystem);

            // create container is heavy, we only create once and cache it
            containerMap.put(Objects.requireNonNull(file.getFilename()), kieContainer);

            // remove the repo immediately
            //kieServices().getRepository().removeKieModule(releaseId);
        }
        return containerMap;
    }

    private Resource[] getRuleFiles() throws IOException {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        return resourcePatternResolver.getResources("classpath*:" + RULES_PATH + "**/*.*");
    }

    private KieRepository kieRepository() {
        final KieRepository kieRepository = kieServices().getRepository();
        kieRepository.addKieModule(new KieModule() {
            public ReleaseId getReleaseId() {
                return kieRepository.getDefaultReleaseId();
            }
        });
        return kieRepository;
    }

    private KieServices kieServices() {
        return KieServices.Factory.get();
    }

    private KieContainer getKieContainer(KieFileSystem kieFileSystem) {
        final KieRepository kieRepository = kieRepository();
        KieBuilder kieBuilder = kieServices().newKieBuilder(kieFileSystem);
        kieBuilder.buildAll();
        return kieServices().newKieContainer(kieRepository.getDefaultReleaseId());
    }
}
