/*
 * Copyright (c) 2019. Sohu.
 * All Rights Reserved.
 */
package com.leohapps.springsecurity.demo.config;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * ClassName: com.leohapps.springsecurity.demo.config.RoleBasedVoter
 * Description:
 *
 * @author lx
 * @date 2019-10-30 10:47
 */
public class RoleBasedVoter implements AccessDecisionVoter<Object> {


    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
    // o 类型是 FilterInvocation
    @Override
    public int vote(Authentication authentication, Object o, Collection<ConfigAttribute> attributes) {
        // 运行时获取请求的url，从而根据url动态鉴权
        // FilterInvocation invocation = (FilterInvocation)o;
        // String url = invocation.getRequestUrl();
        if(authentication == null) {
            return ACCESS_DENIED;
        }
        int result = ACCESS_ABSTAIN;
        Collection<? extends GrantedAuthority> authorities = extractAuthorities(authentication);

        for (ConfigAttribute attribute : attributes) {
            if(attribute.getAttribute()==null){
                continue;
            }
            if (this.supports(attribute)) {
                result = ACCESS_DENIED;

                // Attempt to find a matching granted authority
                for (GrantedAuthority authority : authorities) {
                    if (attribute.getAttribute().equals(authority.getAuthority())) {
                        return ACCESS_GRANTED;
                    }
                }
            }
        }

        return result;
    }

    Collection<? extends GrantedAuthority> extractAuthorities(
            Authentication authentication) {
        return authentication.getAuthorities();
    }
}
