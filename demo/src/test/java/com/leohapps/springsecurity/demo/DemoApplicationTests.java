package com.leohapps.springsecurity.demo;


import com.leohapps.springsecurity.demo.mapper.JcSysPermissionMapper;
import com.leohapps.springsecurity.demo.mapper.JcSysUserMapper;
import com.leohapps.springsecurity.demo.pojo.JcSysPermission;
import com.leohapps.springsecurity.demo.pojo.JcSysUser;
import com.leohapps.springsecurity.demo.pojo.example.JcSysPermissionExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private JcSysUserMapper jcSysUserMapper;

    @Autowired
    private JcSysPermissionMapper jcSysPermissionMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testMapper() {

        JcSysUser user = jcSysUserMapper.loadUserByUsername("xiaoliu208902");
    }

    @Test
    public void testPermissionMapper() {

        List<JcSysPermission> permissions = jcSysPermissionMapper.getAllPermison();

        System.out.println(permissions);
    }

    @Test
    public void testMapper3() {
        JcSysPermissionExample example = new JcSysPermissionExample();

        List<JcSysPermission> permissions = jcSysPermissionMapper.selectByExample(example);
        System.out.println();
    }

}
