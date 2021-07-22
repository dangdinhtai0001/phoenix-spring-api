package com.phoenix.api.base.service;

import com.phoenix.api.base.constant.BeanIds;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(BeanIds.DEFAULT_USER_DETAIL_SERVICES)
@Log4j2
public class DefaultUserDetailService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       return null;
    }
}
