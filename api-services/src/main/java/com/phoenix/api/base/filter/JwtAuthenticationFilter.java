package com.phoenix.api.base.filter;

import com.phoenix.api.base.constant.ApplicationConstant;
import com.phoenix.common.auth.JwtProvider;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
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
    protected boolean shouldNotFilter(@NotNull HttpServletRequest request) {
        return Arrays.stream(ApplicationConstant.PUBLIC_URLS_MATCHER)
                .anyMatch(e -> new AntPathMatcher().match(e, request.getServletPath()));
    }

    /**
     * Filter the incoming request for a valid token in the request header
     */
    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest httpServletRequest,
                                    @NotNull HttpServletResponse httpServletResponse,
                                    @NotNull FilterChain filterChain) throws IOException {
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
        } catch (MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            logger.warn(e.getMessage());
            httpServletResponse.addHeader("Exception", e.getMessage());
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
        } catch (Exception e) {
//            e.printStackTrace();
            logger.warn(e.getMessage());
            httpServletResponse.addHeader("Exception", e.getMessage());
            httpServletResponse.sendError(HttpServletResponse.SC_FOUND, e.getMessage());
        }
    }

    private String getTokenFromHeader(String header) {
        return header.substring(ApplicationConstant.JWT_TOKEN_TYPE.length());
    }
}
