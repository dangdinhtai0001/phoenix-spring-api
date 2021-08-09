package com.phoenix.api.base.repository;

import com.phoenix.api.base.entities.UserEntity;
import com.phoenix.api.base.model.UserPrincipal;
import com.phoenix.api.base.repositories.imp.UserRepositoryImp;
import com.phoenix.api.core.repository.specification.Specifications;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@SpringBootTest
@ActiveProfiles(profiles = "dev")
public class TestUserRepository {
    @Autowired
    private UserRepositoryImp userRepositoryImp;

    @Test
    public void testFindOne() {
        Specification specification = Specifications.and().eq("username", "test").build();
        Optional<UserEntity> optional = userRepositoryImp.findOne(specification);

        System.out.println(optional.orElse(null));
    }

    @Test
    public void testFindUserPrincipal() {
        Optional<UserPrincipal> optional = userRepositoryImp.findUserPrincipalByUsername("test1");

        System.out.println(optional.orElse(null));
    }

    @Test
    public void findRefreshTokenByUsername() {
        Optional<String> optional = userRepositoryImp.findRefreshTokenByUsername("user1");

        System.out.println(optional.orElse(null));
    }
}
