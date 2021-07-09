/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 9:03 PM
 */

/*
 *  @Author Đặng Đình Tài
 *  @Created date: 6/20/21, 3:21 PM
 */

package com.phoenix.api.base.entities;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        // Use below commented code when will use Spring Security.
        // return Optional.empty();
        // ------------------ Use below code for spring security --------------------------
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        return Optional.of(authentication.getPrincipal().toString());
    }


}

