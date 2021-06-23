/*
 * @Author Đặng Đình Tài
 * @Date 6/23/21, 3:07 PM
 */

package com.phoenix.api.entrypoint;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class BaseEntryPoint {
    protected final HandlerExceptionResolver resolver;

    protected BaseEntryPoint(HandlerExceptionResolver resolver) {
        this.resolver = resolver;
    }

    protected abstract void handle(HttpServletRequest httpServletRequest,
                                   HttpServletResponse httpServletResponse,
                                   Exception e) throws IOException, ServletException;
}
