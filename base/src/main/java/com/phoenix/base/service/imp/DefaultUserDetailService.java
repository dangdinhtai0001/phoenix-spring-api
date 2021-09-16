package com.phoenix.base.service.imp;

import com.phoenix.base.constant.BeanIds;
import com.phoenix.base.model.DefaultUserDetails;
import com.phoenix.base.model.UserPrincipal;
import com.phoenix.base.repository.imp.DefaultUserDetailsRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        List<Integer> groups = userRepositoryImp.findGroupIdsByUsername(username);
        userPrincipal.setGroups(groups.stream().map(String::valueOf).collect(Collectors.toList()));

        return new DefaultUserDetails(userPrincipal);
    }
}
