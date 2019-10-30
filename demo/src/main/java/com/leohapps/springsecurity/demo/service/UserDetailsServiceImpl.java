/*
 * Copyright (c) 2019. Sohu.
 * All Rights Reserved.
 */
package com.leohapps.springsecurity.demo.service;


import com.leohapps.springsecurity.demo.pojo.JcSysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * ClassName: com.sohu.jc.op.server.secure.service.UserDetailsServiceImpl
 * Description:
 *
 * @author lx
 * @date 2019-10-28 18:10
 */
@Slf4j
@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService,
        AuthenticationUserDetailsService<CasAssertionAuthenticationToken> {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        JcSysUser user = sysUserService.findByUserName(userName);
        return user;
    }

    @Override
    public UserDetails loadUserDetails(CasAssertionAuthenticationToken casAssertionAuthenticationToken) throws UsernameNotFoundException {
        String name = casAssertionAuthenticationToken.getName();
        log.info("get User name : {}", name);
        JcSysUser user = sysUserService.findByUserName(name);
        if (user == null) {
            throw new UsernameNotFoundException(name+" not exist");
        }
        return user;
    }
}


