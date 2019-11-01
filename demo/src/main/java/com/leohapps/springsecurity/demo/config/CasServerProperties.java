/*
 * Copyright (c) 2019. Sohu.
 * All Rights Reserved.
 */
package com.leohapps.springsecurity.demo.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * ClassName: com.leohapps.springsecurity.demo.config.CasServerProperties
 * Description:
 *
 * @author lx
 * @date 2019-10-31 10:41
 */
@Component
@Data
public class CasServerProperties {

    @Value("${sso.server.host}")
    private String casServerhost;

    @Value("${sso.url.login}")
    private String casServerLoginUrl;

    @Value("${sso.url.logout}")
    private String casServerLogoutUrl;

    @Value("${sso.client.signKey}")
    private String ssoSignKey;

    @Value("${sso.client.secret}")
    private String clientSecret;

    @Value("${sso.client.appid}")
    private String appId;

    @Value("${app.url}")
    private String appService;

    @Value("${app.path}")
    private String appPath;
    @Value("${app.logout}")
    private String appLogoutService;


}
