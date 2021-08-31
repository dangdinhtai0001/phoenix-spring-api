package com.phoenix.api.base.service.imp;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.model.MenuWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles(profiles = "dev")
class MenuServiceImpTest {
    @Autowired
    @Qualifier(BeanIds.MENU_SERVICES)
    private MenuServiceImp menuServiceImp;

    @Autowired
    @Qualifier(BeanIds.DEFAULT_USER_DETAIL_SERVICES)
    private UserDetailsService userDetailsService;

    @BeforeEach
    void setup() {
        UserDetails userDetails = userDetailsService.loadUserByUsername("admin");

        if (userDetails != null) {
            UsernamePasswordAuthenticationToken authenticationToken
                    = new UsernamePasswordAuthenticationToken(
                    userDetails.getUsername(), userDetails.getPassword(),
                    userDetails.getAuthorities()
            );

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
    }

    @Test
    public void testFindAll() {
        List<MenuWrapper> list = menuServiceImp.findAll();

        for (MenuWrapper wrapper : list) {
            System.out.println(String.format("Menu: %s, children size: %d", wrapper.getDisplayName(), wrapper.getChildren().size()));
        }
    }

}