package com.loan.repayment.client;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.loan.repayment.factory.RepaymentPlanRequestFactory;
import com.loan.repayment.factory.RepaymentPlanResponseFactory;

/**
 * 
 * @author Ramachandra
 *
 */
class RepaymentPlanClientApiTest {

    /**
     * Auth Token URL.
     */
    private static final String AUTH_TOKEN_URL = "http://localhost:8080/oauth/token";

    /**
     * Generate Plan URL.
     */
    private static final String GENERATE_PLAN_URL = "http://localhost:8080/generate-plan";

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private RepaymentPlanClientApi repaymentPlanClientApi;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * This method is to test ConnectAndGetRepaymentPlan with Successful Response.
     */
    @Test
    void testConnectAndGetRepaymentPlan() {
        when(restTemplate.postForEntity(eq(AUTH_TOKEN_URL), any(), any()))
                .thenReturn(new ResponseEntity<>(
                        "{\"access_token\":\"53713e93-1914-46f9-9f23-8162a0bf21b2\","
                                + "\"token_type\":\"bearer\",\"expires_in\":43199," + "\"scope\":\"any\"}",
                        HttpStatus.OK));
        when(restTemplate.postForEntity(eq(GENERATE_PLAN_URL), any(), any())).thenReturn(
                new ResponseEntity<>(RepaymentPlanResponseFactory.createRepaymentPlanResponse(), HttpStatus.OK));
        repaymentPlanClientApi.connectAndGetRepaymentPlan(RepaymentPlanRequestFactory.createRepaymentPlanRequest());
        verifyConnectAndGetRepaymentPlan();
    }

    /**
     * This method is to test ConnectAndGetRepaymentPlan with UnAuthorized
     * Response.
     */
    @Test
    void testConnectAndGetRepaymentPlanWithException() {
        when(restTemplate.postForEntity(eq(AUTH_TOKEN_URL), any(), any()))
                .thenReturn(new ResponseEntity<>(
                        "{\"error\":\"unauthorized\",\"error_description\":"
                                + "\"Full authentication is required to access this resource\"}",
                        HttpStatus.UNAUTHORIZED));
        when(restTemplate.postForEntity(eq(GENERATE_PLAN_URL), any(), any())).thenReturn(
                new ResponseEntity<>(RepaymentPlanResponseFactory.createRepaymentPlanResponse(), HttpStatus.OK));
        repaymentPlanClientApi.connectAndGetRepaymentPlan(RepaymentPlanRequestFactory.createRepaymentPlanRequest());
        verifyConnectAndGetRepaymentPlan();

    }

    /**
     * This method will verify the execution of API.
     */
    void verifyConnectAndGetRepaymentPlan() {
        verify(restTemplate).postForEntity(eq(AUTH_TOKEN_URL), any(), any());
        verify(restTemplate).postForEntity(eq(GENERATE_PLAN_URL), any(), any());
    }
}