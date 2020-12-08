package com.loan.repayment

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.core.env.Environment

/**
 *
 * @author Ramachandra
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
internal class ApplicationIT {
    /**
     * Environment
     */
    @Autowired
    private lateinit var env : Environment

    /**
     * This test for testing the Application main class for Code Coverage
     */
    @Test
    fun main() {
        assertNotNull(env , "Expected the environment as not null object")
    }
}