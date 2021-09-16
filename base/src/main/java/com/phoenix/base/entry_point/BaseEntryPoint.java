package com.phoenix.base.entry_point;

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
