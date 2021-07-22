package com.phoenix.api.base.config;


import com.phoenix.api.base.constant.ApplicationConstant;
import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.filter.JwtAuthenticationFilter;
import com.phoenix.api.base.model.RawPasswordEncoder;
import com.phoenix.api.base.entry_point.DefaultAccessDeniedEntryPoint;
import com.phoenix.api.base.entry_point.JwtAuthenticationEntryPoint;
import com.phoenix.common.auth.JwtProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.HashMap;
import java.util.Map;

/**
 * Application security config
 */
@Configuration
@EnableWebSecurity()
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final JwtProvider tokenProvider;
    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final DefaultAccessDeniedEntryPoint defaultAccessDeniedEntryPoint;


    public SecurityConfiguration(
            @Qualifier(BeanIds.JWT_PROVIDER) JwtProvider tokenProvider,
            @Qualifier(BeanIds.DEFAULT_USER_DETAIL_SERVICES) UserDetailsService userDetailsService,
            @Qualifier(BeanIds.JWT_AUTHENTICATION_ENTRY_POINT) JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
            @Qualifier(BeanIds.DEFAULT_ACCESS_DENIED_ENTRY_POINT) DefaultAccessDeniedEntryPoint defaultAccessDeniedEntryPoint
    ) {
        this.tokenProvider = tokenProvider;
        this.userDetailsService = userDetailsService;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.defaultAccessDeniedEntryPoint = defaultAccessDeniedEntryPoint;
    }

    @Bean(name = BeanIds.JWT_AUTHENTICATION_FILTER)
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(tokenProvider, userDetailsService);
    }

    @Bean(name = BeanIds.DEFAULT_AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean(name = BeanIds.PASSWORD_ENCODER)
    public PasswordEncoder delegatingPasswordEncoder() {
        String idForEncode = ApplicationConstant.PASSWORD_ENCODER_BCRYPT_ID;
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put(idForEncode, new BCryptPasswordEncoder());
        encoders.put(ApplicationConstant.PASSWORD_ENCODER_PBKDF2_ID, new Pbkdf2PasswordEncoder());
        encoders.put(ApplicationConstant.PASSWORD_ENCODER_SCRYPT_ID, new SCryptPasswordEncoder());
        encoders.put(ApplicationConstant.PASSWORD_ENCODER_RAW_ID, new RawPasswordEncoder());

        return new DelegatingPasswordEncoder(idForEncode, encoders);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable()
                .csrf()
                .disable();

        http
                .authorizeRequests()
                .antMatchers(ApplicationConstant.PUBLIC_URLS_MATCHER).permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .exceptionHandling().accessDeniedHandler(defaultAccessDeniedEntryPoint)
                .and()
                .sessionManagement((sessionManagement) -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                        .maximumSessions(2)
                );

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
