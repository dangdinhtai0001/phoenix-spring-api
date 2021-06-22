package com.phoenix.api.services.auth;

import com.phoenix.api.constant.BeanIds;
import com.phoenix.api.entities.auth.UserStatusEntity;
import com.phoenix.api.model.auth.DefaultUserDetails;
import com.phoenix.api.model.auth.UserPrincipal;
import com.phoenix.api.repositories.auth.AuthRepositoryImp;
import com.phoenix.others.BitUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("DefaultUserService")
public class DefaultUserDetailService implements UserDetailsService {

    private final AuthRepositoryImp authRepositoryImp;

    private final List<UserStatusEntity> userStatusEntities;

    public DefaultUserDetailService(
            @Qualifier("AuthRepositoryImp") AuthRepositoryImp authRepositoryImp,
            @Qualifier(BeanIds.ALL_USER_STATUS) List<UserStatusEntity> userStatusEntities) {
        this.authRepositoryImp = authRepositoryImp;
        this.userStatusEntities = userStatusEntities;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserPrincipal> optional = authRepositoryImp.findUserByUsername(username);

        if (!optional.isPresent()) {
            throw new UsernameNotFoundException(username);
        }

        UserPrincipal userPrincipal = optional.get();

        userPrincipal.setListStatus(getListStatus(userPrincipal.getStatus()));

        return new DefaultUserDetails(userPrincipal);
    }

    private List<String> getListStatus(int status) {
        List<String> listStatus;
        int[] positions = BitUtil.getAllBitOnePosition(status);

        listStatus = Arrays
                .stream(positions)
                .filter(position -> position > -1)
                .mapToObj(position -> userStatusEntities.get(position).getName())
                .collect(Collectors.toCollection(LinkedList::new));
        return listStatus;
    }
}
