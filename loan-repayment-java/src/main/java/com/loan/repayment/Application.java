package com.loan.repayment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 *
 * @author Ramachandra
 *
 */
@SpringBootApplication
@EnableAuthorizationServer
public class Application {

    /**
     * This is the main program.
     *
     * @param args Application run time arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}