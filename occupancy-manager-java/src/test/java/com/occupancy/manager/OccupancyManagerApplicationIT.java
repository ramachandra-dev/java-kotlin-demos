package com.occupancy.manager;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.env.Environment;

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
    private Environment env;

    /**
     * This test for OccupancyManagerApplication
     */
    @Test
    void main() {
        OccupancyManagerApplication.main(new String[] {});
        assertNotNull(env, "Expected the environment as not null object");
    }

}
