/*
 * Copyright (c) 2019. Sohu.
 * All Rights Reserved.
 */
package com.leohapps.springsecurity.demo.service;


import com.leohapps.springsecurity.demo.pojo.JcSysPermission;
import com.leohapps.springsecurity.demo.pojo.JcSysRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * ClassName: com.sohu.jc.op.server.secure.service.SecurityMetaDataSource
 * Description:
 *
 * @author lx
 * @date 2019-10-28 19:52
 */
@Component
public class SecurityMetaDataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    PermissionService permissionService;
    AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) {
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        List<JcSysPermission> allPermission = permissionService.getAllPermission();

        for (JcSysPermission permission : allPermission) {
            if (antPathMatcher.match(permission.getUrl(), requestUrl)
                    &&permission.getRoles().size()>0) {
                List<JcSysRole> roles = permission.getRoles();
                int size = roles.size();
                String[] values = new String[size];
                for (int i = 0; i < size; i++) {
                    values[i] = roles.get(i).getName();
                }
                return SecurityConfig.createList(values);
            }
        }
        //没有匹配上的资源，都是登录访问
        return SecurityConfig.createList("ROLE_LOGIN");

    }
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }
    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
