package com.phoenix.api.base.repositories.imp;

import com.phoenix.api.base.model.UserPrincipal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@SpringBootTest
@ActiveProfiles(profiles = "dev")
class DefaultUserDetailsRepositoryTest {

    @Autowired
    private DefaultUserDetailsRepository repository;

    @Test
    public void testFindUserPrincipalByUsername(){
        Optional<UserPrincipal> optional = repository.findUserPrincipalByUsername("admin");

        System.out.println(optional.orElse(null));
    }

}