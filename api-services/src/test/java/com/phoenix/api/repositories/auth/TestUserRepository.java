/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

package com.phoenix.api.repositories.auth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class TestUserRepository {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthRepositoryImp authRepositoryImp;

    @Test
    public void testFindByUsername() {
//        Optional optional = userRepository.findByUsername("admin_test");

        Optional optional = authRepositoryImp.findUserByUsername("admin_test");
        System.out.println(optional.orElse(null));
    }

    @Test
    public void testUpdateRefreshToken() {
//        Optional optional = userRepository.findByUsername("admin_test");

        int i  = userRepository.updateRefreshTokenByUsername("1234","admin_test");
        System.out.println(i);
    }
}
