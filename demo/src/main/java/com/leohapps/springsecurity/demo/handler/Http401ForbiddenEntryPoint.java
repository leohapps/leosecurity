/*
 * Copyright (c) 2019. Sohu.
 * All Rights Reserved.
 */
package com.leohapps.springsecurity.demo.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: com.leohapps.springsecurity.demo.handler.Http401EndPoint
 * Description:
 *
 * @author lx
 * @date 2019-10-30 21:27
 */
public class Http401ForbiddenEntryPoint implements AuthenticationEntryPoint {
    private static final Log logger = LogFactory.getLog(org.springframework.security.web.authentication.Http403ForbiddenEntryPoint.class);

    public Http401ForbiddenEntryPoint() {
    }

    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg2) throws IOException, ServletException {
        if (logger.isDebugEnabled()) {
            logger.debug("Pre-authenticated entry point called. Rejecting access");
        }

        response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
    }
}
