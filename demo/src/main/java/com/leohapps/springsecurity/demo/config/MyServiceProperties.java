/*
 * Copyright (c) 2019. Sohu.
 * All Rights Reserved.
 */
package com.leohapps.springsecurity.demo.config;

import org.springframework.security.cas.ServiceProperties;

/**
 * ClassName: com.leohapps.springsecurity.demo.config.MyServiceProperties
 * Description:
 *
 * @author lx
 * @date 2019-11-01 10:47
 */

public class MyServiceProperties extends ServiceProperties {
    /** sso 客户端Id*/
    private String appId;
    /** sso 秘钥，用该秘钥加密发送ticket*/
    private String signKey;
    /** sso 解密 用该密码解密 ticket，再用signKey加密*/
    private String clientSecret;

    public MyServiceProperties() {
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSignKey() {
        return signKey;
    }

    public void setSignKey(String signKey) {
        this.signKey = signKey;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
