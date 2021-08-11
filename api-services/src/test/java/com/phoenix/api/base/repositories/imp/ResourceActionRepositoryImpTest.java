package com.phoenix.api.base.repositories.imp;

import com.phoenix.api.base.entities.ResourceActionEntity;
import com.phoenix.api.base.repositories.ResourceActionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles(profiles = "dev")
class ResourceActionRepositoryImpTest {

    @Autowired
    private ResourceActionRepository resourceActionRepository;

    @Test
    public void testSaveAll() throws Exception {
        ResourceActionEntity resourceAction = new ResourceActionEntity();
        resourceAction.setResource("resource-1");
        resourceAction.setAction("action-1");
        resourceAction.setDescription("-----");

        ResourceActionEntity resourceAction2 = new ResourceActionEntity();
        resourceAction2.setResource("resource-2");
        resourceAction2.setAction("action-2");
        resourceAction2.setDescription("-----");

        List<ResourceActionEntity> resourceActionList = new ArrayList<>();
        resourceActionList.add(resourceAction);
        resourceActionList.add(resourceAction2);

        resourceActionRepository.saveAllAndFlush(resourceActionList);
    }

}