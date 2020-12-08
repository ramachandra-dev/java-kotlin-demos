package com.loan.repayment.client;

import java.io.IOException;
import java.net.http.HttpTimeoutException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loan.repayment.entity.RepaymentPlanRequest;
import com.loan.repayment.entity.RepaymentPlanResponse;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Ramachandra
 *
 */
@Component
@Slf4j
public class RepaymentPlanClientApi {

    /**
     * Synchronous client to perform HTTP requests, exposing a simple, template
     * method API over underlying HTTP client libraries such as the JDK
     * {@code HttpURLConnection}, Apache HttpComponents, and others.
     */
    @Autowired
    private RestTemplate restTemplate;

    /**
     * Client ID.
     */
    @Value("${security.oauth2.client.client-id}")
    private String clientId;

    /**
     * Client Secret.
     */
    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;

    /**
     * Retry Max Attempts.
     */
    @Value("${retry.max.attempts}")
    private String retryMaxAttempts;

    /**
     * This is the connection URL for the Plan Generator API.
     */
    private static final String PLAN_GENERATOR_API = "http://localhost:8080";

    /**
     * The method is to connect to PlanGenerator API and Get Response.
     *
     * @param repaymentPlanRequest Payload for Repayment Plan
     */
    @Retryable(value = { HttpTimeoutException.class }, maxAttemptsExpression = "${retry.max.attempts}")
    public void connectAndGetRepaymentPlan(final RepaymentPlanRequest repaymentPlanRequest) {

        final var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken());
        final var request = new HttpEntity<>(repaymentPlanRequest, headers);
        final var response = restTemplate.postForEntity(PLAN_GENERATOR_API + "/generate-plan", request,
                RepaymentPlanResponse.class);
        log.info("Response received {}", response);
    }

    /**
     * This method gets the access token.
     *
     * @return access token
     */
    private String getAccessToken() {
        try {
            final var payload = new LinkedMultiValueMap<>();
            payload.add("grant_type", "client_credentials");
            payload.add("scope", "read");
            final var headers = new HttpHeaders();
            //@formatter:off
            headers.setBasicAuth(new String(Base64
                    .getEncoder()
                    .encode((clientId + ":" + clientSecret)
                            .getBytes(StandardCharsets.UTF_8))));
            //@formatter:on
            final var request = new HttpEntity<>(payload, headers);
            var accessToken = new ObjectMapper().readValue(
                    restTemplate.postForEntity(PLAN_GENERATOR_API + "/oauth/token", request, String.class).getBody(),
                    JsonNode.class).get("access_token");
            //@formatter:on
            if (accessToken != null) {
                return accessToken.asText();
            }
            //@formatter:on
        } catch (final IOException e) {
            log.error("Exception Occurred while get the response");
        }
        return null;
    }
}
