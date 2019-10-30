package com.leohapps.springsecurity.demo.util;

import com.leohapps.springsecurity.demo.pojo.JcSysUser;
import org.springframework.security.core.context.SecurityContextHolder;

public class JcSysUserUtils {
    public static JcSysUser getCurrentUser() {
        return (JcSysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}