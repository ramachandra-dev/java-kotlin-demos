package com.loan.repayment.security;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 *
 * @author Ramachandra.
 *
 */

@Configuration
@Order(SecurityProperties.BASIC_AUTH_ORDER)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * This method to configure the {@link HttpSecurity}.
     *
     *
     * @param http the {@link HttpSecurity} to modify
     * @throws Exception if an error occurs
     */
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        //@formatter:off
        http
        .csrf()
        .disable()
        .authorizeRequests()
        .anyRequest()
        .authenticated()
        .and()
        .httpBasic()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //@formatter:on
    }
}