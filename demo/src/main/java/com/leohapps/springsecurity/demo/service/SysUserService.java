/*
 * Copyright (c) 2019. Sohu.
 * All Rights Reserved.
 */
package com.leohapps.springsecurity.demo.service;


import com.leohapps.springsecurity.demo.mapper.JcSysUserMapper;
import com.leohapps.springsecurity.demo.pojo.JcSysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: com.sohu.jc.op.server.secure.service.SysUserService
 * Description:
 *
 * @author lx
 * @date 2019-10-28 19:46
 */
@Service
public class SysUserService {

    @Autowired
    private JcSysUserMapper jcSysUserMapper;



    public JcSysUser findByUserName(String userName) {
        JcSysUser user = jcSysUserMapper.loadUserByUsername(userName);
        return user;
    }
}
