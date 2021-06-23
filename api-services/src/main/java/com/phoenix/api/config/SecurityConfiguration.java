package com.phoenix.api.config;

import com.phoenix.api.constant.ApplicationConstant;
import com.phoenix.api.constant.BeanIds;
import com.phoenix.api.entrypoint.JwtAuthenticationEntryPoint;
import com.phoenix.api.filter.JwtAuthenticationFilter;
import com.phoenix.api.util.security.RawPasswordEncoder;
import com.phoenix.auth.JwtProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * Application security config
 */
@Configuration
@EnableWebSecurity()
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final JwtProvider tokenProvider;
    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    public SecurityConfiguration(
            @Qualifier(BeanIds.JWT_PROVIDER) JwtProvider tokenProvider,
            @Qualifier(BeanIds.DEFAULT_USER_DETAIL_SERVICES) UserDetailsService userDetailsService,
            @Qualifier(BeanIds.JWT_AUTHENTICATION_ENTRY_POINT) JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.tokenProvider = tokenProvider;
        this.userDetailsService = userDetailsService;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
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

        PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(idForEncode, encoders);

        return passwordEncoder;
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
                .sessionManagement((sessionManagement) -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                        .maximumSessions(2)
                );

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
