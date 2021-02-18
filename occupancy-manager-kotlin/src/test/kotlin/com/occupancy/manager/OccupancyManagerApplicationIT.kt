package com.occupancy.manager

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.core.env.Environment

/**
 * 
 * @author Ramachandra
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class OccupancyManagerApplicationIT {
    /**
     * Environment
     */
    @Autowired
    private lateinit var env : Environment

    /**
     * This test for testing the OccupancyManagerApplication main class for Code Coverage
     */
    @Test
    fun main() {
        assertNotNull(env , "Expected the environment as not null object")
    }

}
