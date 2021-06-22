package com.phoenix.api.repositories.auth;

import com.phoenix.api.entities.auth.UserStatusEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestUserStatusRepository {
    @Autowired
    private UserStatusRepository userStatusRepository;

    @Test
    public void testFindAllUserStatus() {
        List<UserStatusEntity> userStatusEntities = userStatusRepository.findAll();

        for (UserStatusEntity entity : userStatusEntities) {
            System.out.println(entity.getId());
            System.out.println(entity.getCode());
        }
    }
}
