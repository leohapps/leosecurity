/*
 * Copyright (c) 2019. Sohu.
 * All Rights Reserved.
 */
package com.leohapps.springsecurity.demo.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * ClassName: com.leohapps.springsecurity.demo.api.LoginController
 * Description:
 *
 * @author lx
 * @date 2019-10-29 22:28
 */
@Slf4j
@RestController
public class LoginController {

    //@Autowired
    //private AuthenticationManager authenticationManager;

    @RequestMapping(value = "/jc/op/refund/api", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String refund() {
      log.info("refund api ...");
      return "refund";
    }

    @RequestMapping(value = "/jc/op/refund/api/v1/sales/pricestrategy" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String price() {
        log.info("price api ...");
        return "price";
    }

    @RequestMapping(value = "/login.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String login(@RequestParam String username,@RequestParam String password, HttpSession session) {

           /* System.out.println("进入登录请求..........");
            //cas单点登录的用户名就是：_cas_stateful_ ，用户凭证是server传回来的ticket
            UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(username,password);
            Authentication authentication=authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
            System.out.println("登录成功");*/
            return "redirect:home.html";

    }

    @RequestMapping(value = "/logout")
    public String logout() {
        return "logout";
    }



}
