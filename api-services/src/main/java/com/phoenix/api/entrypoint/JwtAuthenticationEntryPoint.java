/*
 * @Author Đặng Đình Tài
 * @Date 6/22/21, 3:37 PM
 */

package com.phoenix.api.entrypoint;

import com.phoenix.api.base.constant.BeanIds;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@Component(value = BeanIds.JWT_AUTHENTICATION_ENTRY_POINT)
public class JwtAuthenticationEntryPoint extends BaseEntryPoint implements AuthenticationEntryPoint {

    private final String DEFAULT_ERROR_MESSAGE = "User is unauthorised. Routing from the entry point.";

    protected JwtAuthenticationEntryPoint(
            @Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        super(resolver);
    }

    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {
        handle(httpServletRequest, httpServletResponse, e);
    }

    @Override
    protected void handle(HttpServletRequest httpServletRequest,
                          HttpServletResponse httpServletResponse,
                          Exception e) throws IOException, ServletException {
        log.error(DEFAULT_ERROR_MESSAGE);

        if (httpServletRequest.getAttribute("javax.servlet.error.exception") != null) {
            Throwable throwable = (Throwable) httpServletRequest.getAttribute("javax.servlet.error.exception");
            resolver.resolveException(httpServletRequest, httpServletResponse, null, (Exception) throwable);
        }
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, DEFAULT_ERROR_MESSAGE);
    }
}
