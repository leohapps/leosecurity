/*
 * Copyright (c) 2019. Sohu.
 * All Rights Reserved.
 */
package com.leohapps.springsecurity.demo.config;

import org.jasig.cas.client.util.CommonUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: com.leohapps.springsecurity.demo.config.MyCasAuthenticationEntryPoint
 * Description:
 *
 * @author lx
 * @date 2019-10-31 19:10
 */
public class MyCasAuthenticationEntryPoint implements AuthenticationEntryPoint, InitializingBean {
    private ServiceProperties serviceProperties;
    private String loginUrl;
    private boolean encodeServiceUrlWithSessionId = true;

    public MyCasAuthenticationEntryPoint() {
    }

    public void afterPropertiesSet() throws Exception {
        Assert.hasLength(this.loginUrl, "loginUrl must be specified");
        Assert.notNull(this.serviceProperties, "serviceProperties must be specified");
        Assert.notNull(this.serviceProperties.getService(), "serviceProperties.getService() cannot be null.");
    }

    public final void commence(HttpServletRequest servletRequest, HttpServletResponse response, AuthenticationException authenticationException) throws IOException, ServletException {
        System.out.println("redirect :" + this.loginUrl);
        response.sendRedirect(this.loginUrl);
    }

    protected String createServiceUrl(HttpServletRequest request, HttpServletResponse response) {
        return CommonUtils.constructServiceUrl((HttpServletRequest)null, response, this.serviceProperties.getService(), (String)null, this.serviceProperties.getArtifactParameter(), this.encodeServiceUrlWithSessionId);
    }

    protected String createRedirectUrl(String serviceUrl) {
        return CommonUtils.constructRedirectUrl(this.loginUrl, this.serviceProperties.getServiceParameter(), serviceUrl, this.serviceProperties.isSendRenew(), false);
    }

    protected void preCommence(HttpServletRequest request, HttpServletResponse response) {
    }

    public final String getLoginUrl() {
        return this.loginUrl;
    }

    public final ServiceProperties getServiceProperties() {
        return this.serviceProperties;
    }

    public final void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public final void setServiceProperties(ServiceProperties serviceProperties) {
        this.serviceProperties = serviceProperties;
    }

    public final void setEncodeServiceUrlWithSessionId(boolean encodeServiceUrlWithSessionId) {
        this.encodeServiceUrlWithSessionId = encodeServiceUrlWithSessionId;
    }

    protected boolean getEncodeServiceUrlWithSessionId() {
        return this.encodeServiceUrlWithSessionId;
    }
}
