/*
 * Copyright (c) 2019. Sohu.
 * All Rights Reserved.
 */
package com.leohapps.springsecurity.demo.config;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.TicketValidationException;
import org.jasig.cas.client.validation.TicketValidator;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.cas.authentication.CasAuthenticationToken;
import org.springframework.security.cas.authentication.NullStatelessTicketCache;
import org.springframework.security.cas.authentication.StatelessTicketCache;
import org.springframework.security.cas.web.authentication.ServiceAuthenticationDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.Assert;


/**
 * ClassName: com.leohapps.springsecurity.demo.config.MyCasAuthenticationProvider
 * Description:
 *
 * @author lx
 * @date 2019-11-01 10:44
 */
public class MyCasAuthenticationProvider implements AuthenticationProvider, InitializingBean, MessageSourceAware {
    private static final Log logger = LogFactory.getLog(org.springframework.security.cas.authentication.CasAuthenticationProvider.class);
    private AuthenticationUserDetailsService<CasAssertionAuthenticationToken> authenticationUserDetailsService;
    private final UserDetailsChecker userDetailsChecker = new AccountStatusUserDetailsChecker();
    protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
    private StatelessTicketCache statelessTicketCache = new NullStatelessTicketCache();
    private String key;
    private TicketValidator ticketValidator;
    private ServiceProperties serviceProperties;
    private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();

    public MyCasAuthenticationProvider() {
    }

    public void afterPropertiesSet() throws Exception {
        Assert.notNull(this.authenticationUserDetailsService, "An authenticationUserDetailsService must be set");
        Assert.notNull(this.ticketValidator, "A ticketValidator must be set");
        Assert.notNull(this.statelessTicketCache, "A statelessTicketCache must be set");
        Assert.hasText(this.key, "A Key is required so CasAuthenticationProvider can identify tokens it previously authenticated");
        Assert.notNull(this.messages, "A message source must be set");
    }

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!this.supports(authentication.getClass())) {
            return null;
        } else if (authentication instanceof UsernamePasswordAuthenticationToken && !"_cas_stateful_".equals(authentication.getPrincipal().toString()) && !"_cas_stateless_".equals(authentication.getPrincipal().toString())) {
            return null;
        } else if (authentication instanceof CasAuthenticationToken) {
            if (this.key.hashCode() == ((CasAuthenticationToken)authentication).getKeyHash()) {
                return authentication;
            } else {
                throw new BadCredentialsException(this.messages.getMessage("CasAuthenticationProvider.incorrectKey", "The presented CasAuthenticationToken does not contain the expected key"));
            }
        } else if (authentication.getCredentials() != null && !"".equals(authentication.getCredentials())) {
            boolean stateless = false;
            if (authentication instanceof UsernamePasswordAuthenticationToken && "_cas_stateless_".equals(authentication.getPrincipal())) {
                stateless = true;
            }

            CasAuthenticationToken result = null;
            if (stateless) {
                result = this.statelessTicketCache.getByTicketId(authentication.getCredentials().toString());
            }

            if (result == null) {
                result = this.authenticateNow(authentication);
                result.setDetails(authentication.getDetails());
            }

            if (stateless) {
                this.statelessTicketCache.putTicketInCache(result);
            }

            return result;
        } else {
            throw new BadCredentialsException(this.messages.getMessage("CasAuthenticationProvider.noServiceTicket", "Failed to provide a CAS service ticket to validate"));
        }
    }

    private CasAuthenticationToken authenticateNow(Authentication authentication) throws AuthenticationException {
        try {
            MyServiceProperties myServiceProperties = (MyServiceProperties)serviceProperties;
            String appId = myServiceProperties.getAppId();
            String secret = myServiceProperties.getClientSecret();
            String signKey = myServiceProperties.getSignKey();
            Assertion assertion = this.ticketValidator.validate(authentication.getCredentials().toString(), appId, secret, signKey);
            UserDetails userDetails = this.loadUserByAssertion(assertion);
            this.userDetailsChecker.check(userDetails);
            return new CasAuthenticationToken(this.key, userDetails, authentication.getCredentials(), this.authoritiesMapper.mapAuthorities(userDetails.getAuthorities()), userDetails, assertion);
        } catch (TicketValidationException var4) {
            throw new BadCredentialsException(var4.getMessage(), var4);
        }
    }

    private String getServiceUrl(Authentication authentication) {
        String serviceUrl;
        if (authentication.getDetails() instanceof ServiceAuthenticationDetails) {
            serviceUrl = ((ServiceAuthenticationDetails)authentication.getDetails()).getServiceUrl();
        } else {
            if (this.serviceProperties == null) {
                throw new IllegalStateException("serviceProperties cannot be null unless Authentication.getDetails() implements ServiceAuthenticationDetails.");
            }

            if (this.serviceProperties.getService() == null) {
                throw new IllegalStateException("serviceProperties.getService() cannot be null unless Authentication.getDetails() implements ServiceAuthenticationDetails.");
            }

            serviceUrl = this.serviceProperties.getService();
        }

        if (logger.isDebugEnabled()) {
            logger.debug("serviceUrl = " + serviceUrl);
        }

        return serviceUrl;
    }

    protected UserDetails loadUserByAssertion(Assertion assertion) {
        CasAssertionAuthenticationToken token = new CasAssertionAuthenticationToken(assertion, "");
        return this.authenticationUserDetailsService.loadUserDetails(token);
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.authenticationUserDetailsService = new UserDetailsByNameServiceWrapper(userDetailsService);
    }

    public void setAuthenticationUserDetailsService(AuthenticationUserDetailsService<CasAssertionAuthenticationToken> authenticationUserDetailsService) {
        this.authenticationUserDetailsService = authenticationUserDetailsService;
    }

    public void setServiceProperties(ServiceProperties serviceProperties) {
        this.serviceProperties = serviceProperties;
    }

    protected String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public StatelessTicketCache getStatelessTicketCache() {
        return this.statelessTicketCache;
    }

    protected TicketValidator getTicketValidator() {
        return this.ticketValidator;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messages = new MessageSourceAccessor(messageSource);
    }

    public void setStatelessTicketCache(StatelessTicketCache statelessTicketCache) {
        this.statelessTicketCache = statelessTicketCache;
    }

    public void setTicketValidator(TicketValidator ticketValidator) {
        this.ticketValidator = ticketValidator;
    }

    public void setAuthoritiesMapper(GrantedAuthoritiesMapper authoritiesMapper) {
        this.authoritiesMapper = authoritiesMapper;
    }

    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication) || CasAuthenticationToken.class.isAssignableFrom(authentication) || CasAssertionAuthenticationToken.class.isAssignableFrom(authentication);
    }
}

