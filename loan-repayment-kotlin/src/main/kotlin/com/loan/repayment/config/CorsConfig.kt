package com.loan.repayment.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

/**
 *
 * @author Ramachandra
 */
@Configuration
class CorsConfig {
    /**
     * This Bean defines to allow all the requests with out cross origin issues.
     *
     * @return corsfilter
     */
    @Bean
    fun corsFilter() : CorsFilter {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()
        config.setAllowCredentials(true)
        config.addAllowedOrigin("*")
        config.addAllowedHeader("*")
        config.addAllowedMethod(HttpMethod.GET)
        config.addAllowedMethod(HttpMethod.POST)
        config.addAllowedMethod(HttpMethod.PUT)
        config.addAllowedMethod(HttpMethod.PATCH)
        config.addAllowedMethod(HttpMethod.DELETE)
        source.registerCorsConfiguration("/**" , config)
        return CorsFilter(source)
    }
}