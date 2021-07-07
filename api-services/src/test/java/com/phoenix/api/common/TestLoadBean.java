/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/7/21, 9:25 PM
 */

package com.phoenix.api.common;

import com.phoenix.api.constant.BeanIds;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedHashMap;

@SpringBootTest
public class TestLoadBean {
    @Autowired
    @Qualifier(value = BeanIds.ALL_RESOURCE_PERMISSIONS_REQUIRED)
    private LinkedHashMap allResourcePermissionRequirement;

    @Test
    public void testLoadAllResourcesPermissionRequired() {
        System.out.println(allResourcePermissionRequirement);
    }
}
