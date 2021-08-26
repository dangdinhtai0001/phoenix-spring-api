package com.phoenix.api.base.service.imp;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.core.model.SearchCriteria;
import com.phoenix.api.core.model.SearchOperation;
import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootTest
@ActiveProfiles(profiles = "dev")
public class TestKieServices {

    @Autowired
    @Qualifier(BeanIds.KIE_CONTAINER)
    private ConcurrentHashMap<String, KieContainer> kieContainerMap;

    @Test
    public void testLoadKieContainer() {
        for (String key : kieContainerMap.keySet()) {
            System.out.println(key);
        }
    }

    @Test
    public void testFireRules() {
        SearchCriteria criteria = new SearchCriteria("key", SearchOperation.EQUAL, "123", "123");

        long timeCounter = System.currentTimeMillis();

        KieContainer kieContainer = kieContainerMap.get("test-rule-1.drl");
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(criteria);
        kieSession.fireAllRules();
        kieSession.dispose();

        timeCounter = System.currentTimeMillis() - timeCounter;

        System.out.println(timeCounter);

    }
}
