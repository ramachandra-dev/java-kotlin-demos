package com.loan.repayment.client

import com.loan.repayment.factory.RepaymentPlanRequestFactory
import com.loan.repayment.factory.RepaymentPlanResponseFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate

/**
 *
 * @author Rama chandra
 */
internal class RepaymentPlanClientApiTest() {
    @Mock
    private lateinit var restTemplate : RestTemplate

    @InjectMocks
    private lateinit var repaymentPlanClientApi : RepaymentPlanClientApi

    @BeforeEach
    fun init() {
        MockitoAnnotations.openMocks(this)
    }

    /**
     * This method is to test ConnectAndGetRepaymentPlan with Successful Response.
     */
    @Test
    fun testConnectAndGetRepaymentPlan() {
        `when`(
            restTemplate.postForEntity(
                eq(AUTH_TOKEN_URL) ,
                any() ,
                any<Class<Any>>()
            )
        )
            .thenReturn(
                ResponseEntity(
                    "{\"access_token\":\"53713e93-1914-46f9-9f23-8162a0bf21b2\","
                            + "\"token_type\":\"bearer\",\"expires_in\":43199," + "\"scope\":\"any\"}" ,
                    HttpStatus.OK
                )
            )
        `when`(
            restTemplate.postForEntity(
                eq(GENERATE_PLAN_URL) ,
                any() ,
                any<Class<Any>>()
            )
        ).thenReturn(
            ResponseEntity(RepaymentPlanResponseFactory.createRepaymentPlanResponse() , HttpStatus.OK)
        )
        repaymentPlanClientApi.connectAndGetRepaymentPlan(RepaymentPlanRequestFactory.createRepaymentPlanRequest())
        verifyConnectAndGetRepaymentPlan()
    }

    /**
     * This method is to test ConnectAndGetRepaymentPlan with UnAuthorized
     * Response.
     */
    @Test
    fun testConnectAndGetRepaymentPlanWithException() {
        `when`(
            restTemplate.postForEntity(
                eq(AUTH_TOKEN_URL) ,
                any() ,
                any<Class<Any>>()
            )
        )
            .thenReturn(
                ResponseEntity(
                    (
                            "{\"error\":\"unauthorized\",\"error_description\":"
                                    + "\"Full authentication is required to access this resource\"}") ,
                    HttpStatus.UNAUTHORIZED
                )
            )
        `when`(
            restTemplate.postForEntity(
                eq(GENERATE_PLAN_URL) ,
                any() ,
                any<Class<Any>>()
            )
        ).thenReturn(
            ResponseEntity(RepaymentPlanResponseFactory.createRepaymentPlanResponse() , HttpStatus.OK)
        )
        repaymentPlanClientApi.connectAndGetRepaymentPlan(RepaymentPlanRequestFactory.createRepaymentPlanRequest())
        verifyConnectAndGetRepaymentPlan()
    }

    /**
     * This method will verify the execution of API.
     */
    fun verifyConnectAndGetRepaymentPlan() {
        verify(restTemplate).postForEntity(
            eq(AUTH_TOKEN_URL) ,
            any() ,
            any<Class<Any>>()
        )
        verify(restTemplate).postForEntity(
            eq(GENERATE_PLAN_URL) ,
            any() ,
            any<Class<Any>>()
        )
    }

    companion object {
        /**
         * Auth Token URL.
         */
        private const val AUTH_TOKEN_URL = "http://localhost:8080/oauth/token"

        /**
         * Generate Plan URL.
         */
        private const val GENERATE_PLAN_URL = "http://localhost:8080/generate-plan"
    }
}