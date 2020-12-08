package com.loan.repayment.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MapperConfig {
    @Bean
    fun mapper(): ObjectMapper {
        val mapper = ObjectMapper()
        mapper.registerModule(KotlinModule())
        return mapper
    }
}