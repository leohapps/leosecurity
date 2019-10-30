/*
 * Copyright (c) 2019. Sohu.
 * All Rights Reserved.
 */
package com.leohapps.springsecurity.demo.service;


import com.leohapps.springsecurity.demo.mapper.JcSysPermissionMapper;
import com.leohapps.springsecurity.demo.pojo.JcSysPermission;
import com.leohapps.springsecurity.demo.pojo.example.JcSysPermissionExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ClassName: com.sohu.jc.op.server.secure.service.PermissionService
 * Description:
 *
 * @author lx
 * @date 2019-10-29 14:08
 */
@Component
public class PermissionService {

    @Autowired
    private JcSysPermissionMapper jcSysPermissionMapper;


    public List<JcSysPermission> getAllPermission() {

        List<JcSysPermission> jcSysPermissionList = jcSysPermissionMapper.getAllPermison();
        return jcSysPermissionList;
    }

}
