package com.loan.repayment.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 *
 * @author Ramachandra
 *
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    /**
     *  Custom Role.
     */
    public static final String ROLE_EXPRESSION = "hasRole('ROLE_USER')";

    /**
     * Open Urls.
     */
    @Value("${open.urls}")
    private String openUrls;

    /**
     * Secured URLS.
     */
    @Value("${secured.urls}")
    private String securedUrls;

    /**
     * Use this to configure the access rules for secure resources. By default all
     * resources <i>not</i> in "/oauth/**" are protected.
     *
     * @param http the current http filter configuration
     * @throws Exception if there is a problem
     */
    @Override
    public void configure(final HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers(openUrls.split(",")).permitAll();
        http.authorizeRequests().antMatchers(securedUrls.split(",")).access(ROLE_EXPRESSION);
    }
}
