package com.loan.repayment.client

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.web.client.RestTemplateBuilderConfigurer
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.util.StdDateFormat
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule
import java.time.Duration

/**
 *
 * @author Ramachandra
 */
@Configuration
class ClientConfiguration {
    /**
     * Read Request Timeout.
     */
    @Value("\${read.timeout}")
    private var readTimeout: Int = 0

    /**
     * Connection Timeout.
     */
    @Value("\${connection.timeout}")
    private var connectionTimeout: Int = 0

    /**
     * This method builds the rest template with time outs from properties.
     *
     * @param configure Builder that can be used to configure and create Rest
     * Template
     * @return RestTemplate
     */
    @Bean
    fun restTemplate(configure : RestTemplateBuilderConfigurer) : RestTemplate {
        return configure.configure(RestTemplateBuilder())
                .setConnectTimeout(Duration.ofSeconds(connectionTimeout.toLong()))
                .setReadTimeout(Duration.ofSeconds(readTimeout.toLong())).build()
    }

    /**
     * This method returns the object mapper with custom modules.
     *
     * @return ObjectMapper
     */
    @Bean
    fun objectMapper() : ObjectMapper {
        //@formatter:off
        val mapper = ObjectMapper()
                .registerModule(ParameterNamesModule())
                .registerModule(Jdk8Module())
                .registerModule(JavaTimeModule())
        with(mapper) {
            disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            dateFormat = StdDateFormat()
        }
        //@formatter:on
        return mapper
    }
}