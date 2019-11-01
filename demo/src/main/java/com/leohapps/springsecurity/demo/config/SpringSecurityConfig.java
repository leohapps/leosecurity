package com.leohapps.springsecurity.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leohapps.springsecurity.demo.handler.AuthenticationAccessDeniedHandler;
import com.leohapps.springsecurity.demo.pojo.RespBean;
import com.leohapps.springsecurity.demo.service.SecurityMetaDataSource;
import com.leohapps.springsecurity.demo.service.UserDetailsServiceImpl;
import com.leohapps.springsecurity.demo.util.JcSysUserUtils;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.validation.Cas20ProxyTicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Spring security配置
 * @author youyp
 * @date 2018-8-10
 */
@SuppressWarnings("ALL")
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private CasServerProperties casServerProperties;

    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    SecurityMetaDataSource metadataSource;
    @Autowired
    UrlAccessDecisionManager urlAccessDecisionManager;
    @Autowired
    AuthenticationAccessDeniedHandler deniedHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       super.configure(auth);
       auth.authenticationProvider(casAuthenticationProvider());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/index.html", "/static/**", "/login_p", "/favicon.ico");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http

                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        RespBean respBean = RespBean.ok("注销成功!");
                        ObjectMapper om = new ObjectMapper();
                        PrintWriter out = resp.getWriter();
                        out.write(om.writeValueAsString(respBean));
                        out.flush();
                        out.close();
                    }
                })
                .permitAll()
                .and()
                .authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(metadataSource);
                        o.setAccessDecisionManager(urlAccessDecisionManager);
                        return o;
                    }
                })
                .antMatchers("/leohapps","/").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(deniedHandler)
                .authenticationEntryPoint(casAuthenticationEntryPoint())
                .and()
                .addFilterAt(casAuthenticationFilter(), CasAuthenticationFilter.class)
                .addFilterBefore(casLogoutFilter(), LogoutFilter.class)
                .addFilterBefore(singleSignOutFilter(), CasAuthenticationFilter.class);
        /**
         *  FilterSecurityInterceptor本身属于过滤器，不能在外面定义为@Bean，
         *  如果定义在外面，则这个过滤器会被独立加载到webContext中，导致请求会一直被这个过滤器拦截
                *  加入到Springsecurity的过滤器链中，才会使它完整的生效
                */
        /*
                http.addFilterBefore(filterSecurityInterceptor(),FilterSecurityInterceptor.class);
        // 关闭spring security默认的frame访问限制
        http.headers().frameOptions().sameOrigin();*/
        /*http.csrf().disable();
            http.logout()
            .logoutUrl("/logout")
            .logoutSuccessHandler(new LogoutSuccessHandler() {
                @Override
                public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                    resp.setContentType("application/json;charset=utf-8");
                    RespBean respBean = RespBean.ok("注销成功!");
                    ObjectMapper om = new ObjectMapper();
                    PrintWriter out = resp.getWriter();
                    out.write(om.writeValueAsString(respBean));
                    out.flush();
                    out.close();
                }
            })
            .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/leohapps").permitAll()
                .anyRequest().authenticated();
            http
            .exceptionHandling()
                    .accessDeniedHandler(deniedHandler)
                    .authenticationEntryPoint(casAuthenticationEntryPoint())

            .and()
            .addFilterAt(casAuthenticationFilter(), CasAuthenticationFilter.class)
            .addFilterBefore(casLogoutFilter(), LogoutFilter.class)
            .addFilterBefore(singleSignOutFilter(), CasAuthenticationFilter.class);
        *//**
         *  FilterSecurityInterceptor本身属于过滤器，不能在外面定义为@Bean，
         *  如果定义在外面，则这个过滤器会被独立加载到webContext中，导致请求会一直被这个过滤器拦截
         *  加入到Springsecurity的过滤器链中，才会使它完整的生效
         *//*
        http.addFilterBefore(filterSecurityInterceptor(),FilterSecurityInterceptor.class);
        // 关闭spring security默认的frame访问限制
        http.headers().frameOptions().sameOrigin();*/
    }

    /**
     * 注意：这里不能加@Bean注解
     * @return
     * @throws Exception
     */
//    @Bean
    public FilterSecurityInterceptor filterSecurityInterceptor() throws Exception {
        FilterSecurityInterceptor filterSecurityInterceptor=new FilterSecurityInterceptor();
        filterSecurityInterceptor.setAuthenticationManager(authenticationManager());
        return filterSecurityInterceptor;
    }

    //@Bean
    public CasAuthenticationFilter casAuthenticationFilter() throws Exception {
        CasAuthenticationFilter casAuthenticationFilter=new CasAuthenticationFilter();
        //((AbstractAuthenticationProcessingFilter)casAuthenticationFilter).setFilterProcessesUrl(casServerProperties.getAppPath());
        casAuthenticationFilter.setAuthenticationManager(authenticationManager());
        casAuthenticationFilter.setFilterProcessesUrl(casServerProperties.getAppPath());
//        casAuthenticationFilter.setAuthenticationSuccessHandler(
//                new SimpleUrlAuthenticationSuccessHandler("/home.html"));
        return casAuthenticationFilter;
    }

    //@Bean
    public SingleSignOutFilter singleSignOutFilter(){
        SingleSignOutFilter singleSignOutFilter=new SingleSignOutFilter();
        singleSignOutFilter.setCasServerUrlPrefix(casServerProperties.getCasServerhost());
        singleSignOutFilter.setIgnoreInitConfiguration(true);
        return singleSignOutFilter;
    }

    //@Bean
    public LogoutFilter casLogoutFilter(){

        LogoutFilter logoutFilter = new LogoutFilter(casServerProperties.getCasServerLogoutUrl(), new SecurityContextLogoutHandler());
        logoutFilter.setFilterProcessesUrl(casServerProperties.getAppLogoutService());
        return logoutFilter;
    }


    /**
     * 重写AuthenticationManager获取的方法并且定义为Bean
     * @return
     * @throws Exception
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 认证入口
     *  <p>
     *    <b>Note:</b>浏览器访问不可直接填客户端的login请求,若如此则会返回Error页面，无法被此入口拦截
     *  </p>
     * @return
     */
    //TODO 1. 改为自己的配置重定向
    @Bean
    public MyCasAuthenticationEntryPoint casAuthenticationEntryPoint(){
        MyCasAuthenticationEntryPoint casAuthenticationEntryPoint=new MyCasAuthenticationEntryPoint();
        casAuthenticationEntryPoint.setLoginUrl(casServerProperties.getCasServerLoginUrl());
        casAuthenticationEntryPoint.setServiceProperties(serviceProperties());
        return casAuthenticationEntryPoint;
    }

    // 扩展ServiceProperties，支持 客户端加解密ticket之后再加密验票
    @Bean
    public MyServiceProperties serviceProperties() {
        MyServiceProperties serviceProperties=new MyServiceProperties();
        serviceProperties.setService(casServerProperties.getAppService());
        serviceProperties.setAuthenticateAllArtifacts(true);

        serviceProperties.setAppId(casServerProperties.getAppId());
        serviceProperties.setClientSecret(casServerProperties.getClientSecret());
        serviceProperties.setSignKey(casServerProperties.getSsoSignKey());
        //serviceProperties.setSendRenew(false);
        return serviceProperties;
    }


    // 考虑重写，实现 sohuClient中的ticketValidate，支持四个参数
    @Bean
    public MyCasAuthenticationProvider casAuthenticationProvider(){
        MyCasAuthenticationProvider casAuthenticationProvider=new MyCasAuthenticationProvider();
        casAuthenticationProvider.setAuthenticationUserDetailsService(userDetailsService);
//        casAuthenticationProvider.setUserDetailsService(userDetailsService);
        casAuthenticationProvider.setServiceProperties(serviceProperties());
        casAuthenticationProvider.setTicketValidator(cas20ProxyTicketValidator());
        casAuthenticationProvider.setKey("casAuthenticationProviderKey");
        return casAuthenticationProvider;
    }

    @Bean
    public Cas20ProxyTicketValidator cas20ProxyTicketValidator() {
        //  配置一下，应该携带 signKey ，clientSecret
        Cas20ProxyTicketValidator validator = new Cas20ProxyTicketValidator(casServerProperties.getCasServerhost());
        validator.setAcceptAnyProxy(true);
        return validator;
    }

}
