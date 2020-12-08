package com.loan.repayment.client;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.client.RestTemplateBuilderConfigurer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

/**
 *
 * @author Ramachandra
 *
 */
@Configuration
public class ClientConfiguration {

    /**
     * Read Request Timeout.
     */
    @Value("${read.timeout}")
    private int readTimeout;

    /**
     * Connection Timeout.
     */
    @Value("${connection.timeout}")
    private int connectionTimeout;

    /**
     * This method builds the rest template with time outs from properties.
     *
     * @param configure Builder that can be used to configure and create Rest
     *                   Template
     * @return RestTemplate
     */
    @Bean
    public RestTemplate restTemplate(final RestTemplateBuilderConfigurer configure) {
        return configure.configure(new RestTemplateBuilder())
                .setConnectTimeout(Duration.ofSeconds(connectionTimeout))
                .setReadTimeout(Duration.ofSeconds(readTimeout)).build();
    }

    /**
     * This method returns the object mapper with custom modules.
     *
     * @return ObjectMapper
     */
    @Bean
    public ObjectMapper objectMapper() {
        //@formatter:off
        final var mapper = new ObjectMapper()
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.setDateFormat(new StdDateFormat());
        //@formatter:on
        return mapper;

    }
}
