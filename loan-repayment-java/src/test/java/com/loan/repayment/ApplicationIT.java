package com.loan.repayment;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.env.Environment;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Ramachandra
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Slf4j
class ApplicationIT {
    
    /**
     * Environment
     */
    @Autowired
    private Environment env;

    /**
     * This test for testing the Application main class for Code Coverage
     */
    @Test
    void main() {
        Application.main(new String[] {});
        assertNotNull(env, "Expected the environment as not null object");
    }

}
