package com.phoenix.api.base.service.imp;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.model.DefaultUserDetails;
import com.phoenix.api.base.model.UserPrincipal;
import com.phoenix.api.base.repositories.imp.DefaultUserDetailsRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service(BeanIds.DEFAULT_USER_DETAIL_SERVICES)
@Log4j2
public class DefaultUserDetailService implements UserDetailsService {

    private final DefaultUserDetailsRepository userRepositoryImp;

    public DefaultUserDetailService(
            @Qualifier(BeanIds.BASE_USER_REPOSITORY_IMP) DefaultUserDetailsRepository userRepositoryImp) {
        this.userRepositoryImp = userRepositoryImp;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserPrincipal> optional = userRepositoryImp.findUserPrincipalByUsername(username);

        if (!optional.isPresent()) {
            throw new UsernameNotFoundException("Couldn't find a matching user username in the database for: " + username);
        }

        UserPrincipal userPrincipal = optional.get();

        return new DefaultUserDetails(userPrincipal);
    }
}
