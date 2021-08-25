package com.phoenix.api.base.repositories.imp;

import com.phoenix.api.base.model.UserPrincipal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
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

    @Test
    public void testFindGroupId(){
        List groupIds = repository.findGroupIdsByUsername("admin");

        System.out.println(groupIds);
    }

    @Test
    public void testFindRefreshTokenByUsername(){
        Optional optional = repository.findRefreshTokenByUsername("admin");

        System.out.println(optional.orElse(null));
    }

    @Test
    public void testUpdateRefreshTokenByUsername(){
        int result = repository.updateRefreshTokenByUsername("cf39408e-a539-4ea7-b570-5e37322c9b84", "admin");

        System.out.println(result);
    }

}