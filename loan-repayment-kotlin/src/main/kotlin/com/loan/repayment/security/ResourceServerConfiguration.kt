package com.loan.repayment.security

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter

/**
 *
 * @author Rama chandra
 */
@Configuration
@EnableResourceServer
class ResourceServerConfiguration : ResourceServerConfigurerAdapter() {
    /**
     * Open Urls.
     */
    @Value("\${open.urls}")
    private lateinit var openUrls : String

    /**
     * Secured URLS.
     */
    @Value("\${secured.urls}")
    private lateinit var securedUrls : String

    /**
     * Use this to configure the access rules for secure resources. By default all
     * resources *not* in "/oauth/ **" are protected.
     *
     * @param http the current http filter configuration
     * @throws Exception if there is a problem
     */
    @Throws(java.lang.Exception::class)
    override fun configure(http : HttpSecurity) {
        // Spread operator * is only defined for arrays, and cannot be used on a list directly.
        http.authorizeRequests().antMatchers(*openUrls.split(",").toTypedArray()).permitAll()
        http.authorizeRequests().antMatchers(*securedUrls.split(",").toTypedArray()).access(ROLE_EXPRESSION)
    }

    companion object {
        /**
         * Custom Role.
         */
        const val ROLE_EXPRESSION = "hasRole('ROLE_USER')"
    }
}