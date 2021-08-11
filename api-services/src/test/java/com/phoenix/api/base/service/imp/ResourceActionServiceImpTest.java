package com.phoenix.api.base.service.imp;

import com.phoenix.api.base.entities.ResourceAction;
import com.phoenix.api.base.service.ResourceActionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles(profiles = "dev")
class ResourceActionServiceImpTest {

    @Autowired
    private ResourceActionService resourceActionService;

    @Test
    public void testSaveDataByListClassName() {
        List<String> list = new ArrayList<>();
        list.add("com.phoenix.api.business.services.imp.UserServiceImp");

        List<ResourceAction> actions = resourceActionService.saveDataByListClassName(list);

        for (ResourceAction action : actions) {
            System.out.println(action);
        }
    }

}