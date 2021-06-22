/*
 * @Author Đặng Đình Tài
 * @Created_date: 6/22/21, 9:42 AM
 */

package com.phoenix.api.services.auth;

import com.phoenix.api.constant.BeanIds;
import com.phoenix.api.model.auth.DefaultUserDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetailsService;

@SpringBootTest
public class TestDefaultUserDetailService {
    @Autowired
    @Qualifier(BeanIds.DEFAULT_USER_DETAIL_SERVICES)
    private UserDetailsService userDetailsService;

    @Test
    public void testLoadUserByUsername() {
        DefaultUserDetails userDetails = (DefaultUserDetails) userDetailsService.loadUserByUsername("admin_test");

        System.out.println(userDetails);
    }
}
