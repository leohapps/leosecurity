/*
 * Copyright (c) 2019. Sohu.
 * All Rights Reserved.
 */
package com.leohapps.springsecurity.demo.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * ClassName: com.leohapps.springsecurity.demo.util.EncodeUtil
 * Description:
 *
 * @author lx
 * @date 2019-10-29 23:10
 */
public class EncodeUtil {


    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);
        String s = encoder.encode("123");
        System.out.println(s);
    }
}
