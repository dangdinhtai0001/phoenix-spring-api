/*
 * @Author Đặng Đình Tài
 * @Date 6/23/21, 3:04 PM
 */

package com.phoenix.api.entrypoint;

import com.phoenix.api.constant.BeanIds;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@Component(BeanIds.DEFAULT_ACCESS_DENIED_ENTRY_POINT)
public class DefaultAccessDeniedEntryPoint extends BaseEntryPoint implements AccessDeniedHandler {
    protected DefaultAccessDeniedEntryPoint(
            @Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        super(resolver);
    }

    @Override
    public void handle(HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse,
                       AccessDeniedException e) throws IOException, ServletException {
        handle(httpServletRequest, httpServletResponse, e);
    }

    @Override
    protected void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Exception e)
            throws IOException, ServletException {
        log.error("Access Denied, You don’t have permission to access on this server. Routing from the entry point");

        if (httpServletRequest.getAttribute("javax.servlet.error.exception") != null) {
            Throwable throwable = (Throwable) httpServletRequest.getAttribute("javax.servlet.error.exception");
            resolver.resolveException(httpServletRequest, httpServletResponse, null, (Exception) throwable);
        }
        httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
    }
}
