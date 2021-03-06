/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

/*
 * @Author Đặng Đình Tài
 * @Date 6/22/21, 3:44 PM
 */

package com.phoenix.api.filter;

import com.phoenix.api.base.constant.ApplicationConstant;
import com.phoenix.auth.JwtProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtProvider tokenProvider;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(
            JwtProvider tokenProvider,
            UserDetailsService userDetailsService) {
        this.tokenProvider = tokenProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return Arrays.stream(ApplicationConstant.PUBLIC_URLS_MATCHER)
                .anyMatch(e -> new AntPathMatcher().match(e, request.getServletPath()));
    }

    /**
     * Filter the incoming request for a valid token in the request header
     */
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String tokenHeader = httpServletRequest.getHeader(ApplicationConstant.REQUEST_HEADER_AUTHORIZATION);

            if (tokenHeader == null) {
                filterChain.doFilter(httpServletRequest, httpServletResponse);
                return;
            }

            String token = getTokenFromHeader(tokenHeader);

            if (tokenProvider.validateToken(token)) {
                String username = tokenProvider.getSubjectFromToken(token);

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if (userDetails != null) {
                    UsernamePasswordAuthenticationToken authenticationToken
                            = new UsernamePasswordAuthenticationToken(
                            userDetails.getUsername(), userDetails.getPassword(),
                            userDetails.getAuthorities()
                    );

                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                    filterChain.doFilter(httpServletRequest, httpServletResponse);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
        }
    }

    private String getTokenFromHeader(String header) {
        return header.substring("bearer ".length());
    }
}
