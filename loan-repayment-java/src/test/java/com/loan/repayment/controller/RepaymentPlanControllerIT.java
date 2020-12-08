package com.loan.repayment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.servlet.ServletContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loan.repayment.entity.RepaymentPlanResponse;
import com.loan.repayment.exception.ErrorResponse;
import com.loan.repayment.factory.RepaymentPlanRequestFactory;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Ramachandra
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Slf4j
class RepaymentPlanControllerIT {

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private String baseUrl = "";
    private HttpHeaders headers;

    @Value("${security.oauth2.client.client-id}")
    private String clientId;
    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;

    private static final String HOST_NAME = "localhost";

    /**
     * This method runs before each test case to setup basic headers and construct
     * baseurl.
     */
    @BeforeEach
    public void init() {
        baseUrl = String.format("http://%s:%s%s", HOST_NAME, randomServerPort, servletContext.getContextPath());
        headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken(baseUrl));
    }

    /**
     * This method is to test RepaymentPlan with valid payload of repayments loan
     * request
     */
    @Test
    void testRepaymentPlan() {
        final var request = new HttpEntity<>(RepaymentPlanRequestFactory.createRepaymentPlanRequest(), headers);
        final var banksResponse = testRestTemplate.postForEntity(baseUrl + "/generate-plan", request,
                RepaymentPlanResponse.class);
        assertEquals(24, banksResponse.getBody().getBorrowerPayments().size(), "Expected the payments to be 24");
        final var borrowerPayments = banksResponse.getBody().getBorrowerPayments();
        var borrowerPayment = borrowerPayments.get(0);

        assertEquals("219.36", borrowerPayment.getBorrowerPaymentAmount(), "Expected to match");
        assertEquals("5000.0", borrowerPayment.getInitialOutstandingPrincipal(), "Expected to match");
        assertEquals("20.83", borrowerPayment.getInterest(), "Expected to match");
        assertEquals("198.53", borrowerPayment.getPrincipal(), "Expected to match");
        assertEquals("4801.47", borrowerPayment.getRemainingOutstandingPrincipal(), "Expected to match");

        assertEquals(HttpStatus.CREATED, banksResponse.getStatusCode(), "Expected to match");

        borrowerPayment = borrowerPayments.get(1);

        assertEquals("219.36", borrowerPayment.getBorrowerPaymentAmount(), "Expected to match");
        assertEquals("4801.47", borrowerPayment.getInitialOutstandingPrincipal(), "Expected to match");
        assertEquals("20.01", borrowerPayment.getInterest(), "Expected to match");
        assertEquals("199.35", borrowerPayment.getPrincipal(), "Expected to match");
        assertEquals("4602.12", borrowerPayment.getRemainingOutstandingPrincipal(), "Expected to match");

        borrowerPayment = borrowerPayments.get(5);

        assertEquals("219.36", borrowerPayment.getBorrowerPaymentAmount(), "Expected to match");
        assertEquals("3999.06", borrowerPayment.getInitialOutstandingPrincipal(), "Expected to match");
        assertEquals("16.66", borrowerPayment.getInterest(), "Expected to match");
        assertEquals("202.70", borrowerPayment.getPrincipal(), "Expected to match");
        assertEquals("3796.36", borrowerPayment.getRemainingOutstandingPrincipal(), "Expected to match");

        borrowerPayment = borrowerPayments.get(23);

        assertEquals("219.28", borrowerPayment.getBorrowerPaymentAmount(), "Expected to match");
        assertEquals("218.37", borrowerPayment.getInitialOutstandingPrincipal(), "Expected to match");
        assertEquals("0.91", borrowerPayment.getInterest(), "Expected to match");
        assertEquals("218.37", borrowerPayment.getPrincipal(), "Expected to match");
        assertEquals("0", borrowerPayment.getRemainingOutstandingPrincipal(), "Expected to match");

        assertEquals(HttpStatus.CREATED, banksResponse.getStatusCode(), "Expected to match");
    }

    @Test
    void testRepaymentPlanWithBlankLoanAmount() {
        final var request = new HttpEntity<>(
                RepaymentPlanRequestFactory.createRepaymentPlanRequestWithBlankLoanAmount(), headers);
        final var banksResponse = testRestTemplate.postForEntity(baseUrl + "/generate-plan", request,
                ErrorResponse.class);
        assertEquals("Loan Amount should not be blank to for Generating the Repayment Schedule",
                banksResponse.getBody().getErrorDetails().getMessage(), "Expected the error messages to match");
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, banksResponse.getStatusCode(), "Expected to match");
    }

    @Test
    void testRepaymentPlanWithNullLoanAmount() {
        final var request = new HttpEntity<>(RepaymentPlanRequestFactory.createRepaymentPlanRequestWithNullLoanAmount(),
                headers);
        final var banksResponse = testRestTemplate.postForEntity(baseUrl + "/generate-plan", request,
                ErrorResponse.class);
        assertEquals("Loan Amount should not be blank to for Generating the Repayment Schedule",
                banksResponse.getBody().getErrorDetails().getMessage(), "Expected the error messages to match");
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, banksResponse.getStatusCode(), "Expected to match");
    }

    @Test
    void testRepaymentPlanWithZeroLoanAmount() {
        final var request = new HttpEntity<>(RepaymentPlanRequestFactory.createRepaymentPlanRequestWithZeroLoanAmount(),
                headers);
        final var banksResponse = testRestTemplate.postForEntity(baseUrl + "/generate-plan", request,
                ErrorResponse.class);
        assertEquals("Loan Amount should not be less than zero or zero for Generating the Repayment Schedule",
                banksResponse.getBody().getErrorDetails().getMessage(), "Expected the error messages to match");
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, banksResponse.getStatusCode(), "Expected to match");
    }

    @Test
    void testRepaymentPlanWithInvalidDoubleLoanAmount() {
        final var request = new HttpEntity<>(
                RepaymentPlanRequestFactory.createRepaymentPlanRequestWithInvalidDoubleLoanAmount(), headers);
        final var banksResponse = testRestTemplate.postForEntity(baseUrl + "/generate-plan", request,
                ErrorResponse.class);
        assertEquals("Loan Amount Zero not a valid double value to for Generating the Repayment Schedule",
                banksResponse.getBody().getErrorDetails().getMessage(), "Expected the error messages to match");
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, banksResponse.getStatusCode(), "Expected to match");
    }

    @Test
    void testRepaymentPlanWithNullNominalRate() {
        final var request = new HttpEntity<>(
                RepaymentPlanRequestFactory.createRepaymentPlanRequestWithNullNominalRate(), headers);
        final var banksResponse = testRestTemplate.postForEntity(baseUrl + "/generate-plan", request,
                ErrorResponse.class);
        assertEquals("Nominal rate should not be blank to for Generating the Repayment Schedule",
                banksResponse.getBody().getErrorDetails().getMessage(), "Expected the error messages to match");
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, banksResponse.getStatusCode(), "Expected to match");
    }

    @Test
    void testRepaymentPlanWithBlankNominalRate() {
        final var request = new HttpEntity<>(
                RepaymentPlanRequestFactory.createRepaymentPlanRequestWithBlankNominalRate(), headers);
        final var banksResponse = testRestTemplate.postForEntity(baseUrl + "/generate-plan", request,
                ErrorResponse.class);
        assertEquals("Nominal rate should not be blank to for Generating the Repayment Schedule",
                banksResponse.getBody().getErrorDetails().getMessage(), "Expected the error messages to match");
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, banksResponse.getStatusCode(), "Expected to match");
    }

    @Test
    void testRepaymentPlanWithZeroNominalRate() {
        final var request = new HttpEntity<>(
                RepaymentPlanRequestFactory.createRepaymentPlanRequestWithZeroNominalRate(), headers);
        final var banksResponse = testRestTemplate.postForEntity(baseUrl + "/generate-plan", request,
                ErrorResponse.class);
        assertEquals("Nominal rate should not be less than zero or zero for Generating the Repayment Schedule",
                banksResponse.getBody().getErrorDetails().getMessage(), "Expected the error messages to match");
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, banksResponse.getStatusCode(), "Expected to match");
    }

    @Test
    void testRepaymentPlanWithInvalidDoubleNominalRate() {
        final var request = new HttpEntity<>(
                RepaymentPlanRequestFactory.createRepaymentPlanRequestWithInvalidDoubleNominalRate(), headers);
        final var banksResponse = testRestTemplate.postForEntity(baseUrl + "/generate-plan", request,
                ErrorResponse.class);
        assertEquals("Nominal rate Zero not a valid double value to for Generating the Repayment Schedule",
                banksResponse.getBody().getErrorDetails().getMessage(), "Expected the error messages to match");
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, banksResponse.getStatusCode(), "Expected to match");
    }

    @Test
    void testRepaymentPlanWithInValidTenureWithLoanSchedule() {
        final var request = new HttpEntity<>(
                RepaymentPlanRequestFactory.createRepaymentPlanRequestWithInValidTenureWithLoanSchedule(), headers);
        final var banksResponse = testRestTemplate.postForEntity(baseUrl + "/generate-plan", request,
                ErrorResponse.class);
        assertEquals(
                "In the duration 306 months the Repayment Schedule cannot be completed, Please provide the correct duration",
                banksResponse.getBody().getErrorDetails().getMessage(), "Expected the error messages to match");
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, banksResponse.getStatusCode(), "Expected to match");
    }

    @Test
    void testRepaymentPlanWithZeroMonthsDuration() {
        final var request = new HttpEntity<>(
                RepaymentPlanRequestFactory.createRepaymentPlanRequestWithZeroMonthsDuration(), headers);
        final var banksResponse = testRestTemplate.postForEntity(baseUrl + "/generate-plan", request,
                ErrorResponse.class);
        assertEquals("Duration of the Repayment Schedule should be minimum 1",
                banksResponse.getBody().getErrorDetails().getMessage(), "Expected the error messages to match");
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, banksResponse.getStatusCode(), "Expected to match");
    }

    @Test
    void testRepaymentPlanWithOneMonthsDuration() {
        final var request = new HttpEntity<>(
                RepaymentPlanRequestFactory.createRepaymentPlanRequestWithOneMonthDuration(), headers);
        final var banksResponse = testRestTemplate.postForEntity(baseUrl + "/generate-plan", request,
                RepaymentPlanResponse.class);
        assertEquals(1, banksResponse.getBody().getBorrowerPayments().size(), "Expected the payments to be 1");
        assertEquals(HttpStatus.CREATED, banksResponse.getStatusCode(), "Expected to match");
        final var borrowerPayments = banksResponse.getBody().getBorrowerPayments();
        final var borrowerPayment = borrowerPayments.get(0);

        assertEquals("94562.50", borrowerPayment.getBorrowerPaymentAmount(), "Expected to match");
        assertEquals("89000.0", borrowerPayment.getInitialOutstandingPrincipal(), "Expected to match");
        assertEquals("5562.50", borrowerPayment.getInterest(), "Expected to match");
        assertEquals("89000.0", borrowerPayment.getPrincipal(), "Expected to match");
        assertEquals("0", borrowerPayment.getRemainingOutstandingPrincipal(), "Expected to match");
    }

    private String getAccessToken(final String baseUrl) {
        try {
            final var payload = new LinkedMultiValueMap<>();
            payload.add("grant_type", "client_credentials");
            payload.add("scope", "read");
            final var headers = new HttpHeaders();
            //@formatter:off
			headers.setBasicAuth(new String(Base64
					.getEncoder()
					.encode(("%s:%s".formatted(clientId, clientSecret))
							.getBytes(StandardCharsets.UTF_8))));
			//@formatter:on
            final var request = new HttpEntity<>(payload, headers);
            //@formatter:off
			return new ObjectMapper()
					.readValue(testRestTemplate
							.postForEntity(baseUrl + "/oauth/token", request, String.class)
							.getBody(),JsonNode.class)
					.get("access_token")
					.asText();
			//@formatter:on
        } catch (final IOException e) {
            log.error("Exception Occurred while get the response");
        }
        return null;
    }
}