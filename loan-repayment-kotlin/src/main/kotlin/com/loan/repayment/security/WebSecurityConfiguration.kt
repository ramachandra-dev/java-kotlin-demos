package com.loan.repayment.security

import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy

/**
 *
 * @author Rama chandra.
 */
@Configuration
@Order(SecurityProperties.BASIC_AUTH_ORDER)
class WebSecurityConfiguration : WebSecurityConfigurerAdapter() {
    /**
     * This method to configure the [HttpSecurity].
     *
     *
     * @param http the [HttpSecurity] to modify
     * @throws Exception if an error occurs
     */
    @Throws(java.lang.Exception::class)
    override fun configure(http : HttpSecurity) {
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
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        //@formatter:on
    }
}