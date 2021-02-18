package com.loan.repayment.controller

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.loan.repayment.entity.RepaymentPlanResponse
import com.loan.repayment.exception.ErrorResponse
import com.loan.repayment.factory.RepaymentPlanRequestFactory
import mu.KotlinLogging
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.*
import org.springframework.util.LinkedMultiValueMap
import java.io.IOException
import java.lang.String.format
import javax.servlet.ServletContext


/**
 *
 * @author Rama chandra
 */
private val logger = KotlinLogging.logger {}

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class RepaymentPlanControllerIT {
    @LocalServerPort
    var randomServerPort = 0

    @Autowired
    private lateinit var servletContext : ServletContext

    @Autowired
    private lateinit var testRestTemplate : TestRestTemplate
    private lateinit var baseUrl : String
    private lateinit var headers : HttpHeaders

    @Value("{security.oauth2.client.client-id}")
    private lateinit var clientId : String

    @Value("{security.oauth2.client.client-secret}")
    private lateinit var clientSecret : String

    /**
     * This method runs before each test case to setup basic headers and construct
     * baseurl.
     */
    @BeforeEach
    fun init() {
        baseUrl = format("http://%s:%s%s" , HOST_NAME , randomServerPort , servletContext.contextPath)
        headers = HttpHeaders()
        headers.add(HttpHeaders.CONTENT_TYPE , MediaType.APPLICATION_JSON_VALUE)
        headers.add(HttpHeaders.AUTHORIZATION , "Bearer " + getAccessToken(baseUrl))
    }

    /**
     * This method is to test RepaymentPlan with valid payload of repayment plan loan
     * request
     */
    @Test
    fun testRepaymentPlan() {
        val request = HttpEntity(RepaymentPlanRequestFactory.createRepaymentPlanRequest() , headers)
        val banksResponse : ResponseEntity<RepaymentPlanResponse> = testRestTemplate.postForEntity(
            "$baseUrl/generate-plan" , request ,
            RepaymentPlanResponse::class.java
        )
        assertEquals(24 , banksResponse.body!!.borrowerPayments.size , "Expected the payments to be 24")
        val borrowerPayments = banksResponse.body!!.borrowerPayments
        var borrowerPayment = borrowerPayments[0]
        assertEquals("219.36" , borrowerPayment.borrowerPaymentAmount , "Expected to match")
        assertEquals("5000.0" , borrowerPayment.initialOutstandingPrincipal , "Expected to match")
        assertEquals("20.83" , borrowerPayment.interest , "Expected to match")
        assertEquals("198.53" , borrowerPayment.principal , "Expected to match")
        assertEquals("4801.47" , borrowerPayment.remainingOutstandingPrincipal , "Expected to match")
        borrowerPayment = borrowerPayments[1]
        assertEquals("219.36" , borrowerPayment.borrowerPaymentAmount , "Expected to match")
        assertEquals("4801.47" , borrowerPayment.initialOutstandingPrincipal , "Expected to match")
        assertEquals("20.01" , borrowerPayment.interest , "Expected to match")
        assertEquals("199.35" , borrowerPayment.principal , "Expected to match")
        assertEquals("4602.12" , borrowerPayment.remainingOutstandingPrincipal , "Expected to match")
        borrowerPayment = borrowerPayments[5]
        assertEquals("219.36" , borrowerPayment.borrowerPaymentAmount , "Expected to match")
        assertEquals("3999.06" , borrowerPayment.initialOutstandingPrincipal , "Expected to match")
        assertEquals("16.66" , borrowerPayment.interest , "Expected to match")
        assertEquals("202.70" , borrowerPayment.principal , "Expected to match")
        assertEquals("3796.36" , borrowerPayment.remainingOutstandingPrincipal , "Expected to match")
        borrowerPayment = borrowerPayments[23]
        assertEquals("219.28" , borrowerPayment.borrowerPaymentAmount , "Expected to match")
        assertEquals("218.37" , borrowerPayment.initialOutstandingPrincipal , "Expected to match")
        assertEquals("0.91" , borrowerPayment.interest , "Expected to match")
        assertEquals("218.37" , borrowerPayment.principal , "Expected to match")
        assertEquals("0" , borrowerPayment.remainingOutstandingPrincipal , "Expected to match")
        assertEquals(HttpStatus.CREATED , banksResponse.statusCode , "Expected to match")
    }

    @Test
    fun testRepaymentPlanWithBlankLoanAmount() {
        val request = HttpEntity(
            RepaymentPlanRequestFactory.createRepaymentPlanRequestWithBlankLoanAmount() , headers
        )
        val banksResponse = testRestTemplate.postForEntity(
            "$baseUrl/generate-plan" , request ,
            ErrorResponse::class.java
        )
        assertEquals(
            "Loan Amount should not be blank to for Generating the Repayment Schedule" ,
            banksResponse.body!!.errorDetails.message , "Expected the error messages to match"
        )
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR , banksResponse.statusCode , "Expected to match")
    }

    @Test
    fun testRepaymentPlanWithZeroLoanAmount() {
        val request = HttpEntity(
            RepaymentPlanRequestFactory.createRepaymentPlanRequestWithZeroLoanAmount() ,
            headers
        )
        val banksResponse = testRestTemplate.postForEntity(
            "$baseUrl/generate-plan" , request ,
            ErrorResponse::class.java
        )
        assertEquals(
            "Loan Amount should not be less than zero or zero for Generating the Repayment Schedule" ,
            banksResponse.body!!.errorDetails.message , "Expected the error messages to match"
        )
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR , banksResponse.statusCode , "Expected to match")
    }

    @Test
    fun testRepaymentPlanWithInvalidDoubleLoanAmount() {
        val request = HttpEntity(
            RepaymentPlanRequestFactory.createRepaymentPlanRequestWithInvalidDoubleLoanAmount() , headers
        )
        val banksResponse = testRestTemplate.postForEntity(
            "$baseUrl/generate-plan" , request ,
            ErrorResponse::class.java
        )
        assertEquals(
            "Loan Amount Zero not a valid double value to for Generating the Repayment Schedule" ,
            banksResponse.body!!.errorDetails.message , "Expected the error messages to match"
        )
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR , banksResponse.statusCode , "Expected to match")
    }

    @Test
    fun testRepaymentPlanWithNullNominalRate() {
        val request = HttpEntity(
            RepaymentPlanRequestFactory.createRepaymentPlanRequestWithNullNominalRate() , headers
        )
        val banksResponse = testRestTemplate.postForEntity(
            "$baseUrl/generate-plan" , request ,
            ErrorResponse::class.java
        )
        assertEquals(
            "Nominal rate should not be blank to for Generating the Repayment Schedule" ,
            banksResponse.body!!.errorDetails.message , "Expected the error messages to match"
        )
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR , banksResponse.statusCode , "Expected to match")
    }

    @Test
    fun testRepaymentPlanWithBlankNominalRate() {
        val request = HttpEntity(
            RepaymentPlanRequestFactory.createRepaymentPlanRequestWithBlankNominalRate() , headers
        )
        val banksResponse = testRestTemplate.postForEntity(
            "$baseUrl/generate-plan" , request ,
            ErrorResponse::class.java
        )
        assertEquals(
            "Nominal rate should not be blank to for Generating the Repayment Schedule" ,
            banksResponse.body!!.errorDetails.message , "Expected the error messages to match"
        )
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR , banksResponse.statusCode , "Expected to match")
    }

    @Test
    fun testRepaymentPlanWithZeroNominalRate() {
        val request = HttpEntity(
            RepaymentPlanRequestFactory.createRepaymentPlanRequestWithZeroNominalRate() , headers
        )
        val banksResponse = testRestTemplate.postForEntity(
            "$baseUrl/generate-plan" , request ,
            ErrorResponse::class.java
        )
        assertEquals(
            "Nominal rate should not be less than zero or zero for Generating the Repayment Schedule" ,
            banksResponse.body!!.errorDetails.message , "Expected the error messages to match"
        )
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR , banksResponse.statusCode , "Expected to match")
    }

    @Test
    fun testRepaymentPlanWithInvalidDoubleNominalRate() {
        val request = HttpEntity(
            RepaymentPlanRequestFactory.createRepaymentPlanRequestWithInvalidDoubleNominalRate() , headers
        )
        val banksResponse = testRestTemplate.postForEntity(
            "$baseUrl/generate-plan" , request ,
            ErrorResponse::class.java
        )
        assertEquals(
            "Nominal rate Zero not a valid double value to for Generating the Repayment Schedule" ,
            banksResponse.body!!.errorDetails.message , "Expected the error messages to match"
        )
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR , banksResponse.statusCode , "Expected to match")
    }

    @Test
    fun testRepaymentPlanWithInValidTenureWithLoanSchedule() {
        val request = HttpEntity(
            RepaymentPlanRequestFactory.createRepaymentPlanRequestWithInValidTenureWithLoanSchedule() , headers
        )
        val banksResponse = testRestTemplate.postForEntity(
            "$baseUrl/generate-plan" , request ,
            ErrorResponse::class.java
        )
        assertEquals(
            "In the duration 306 months the Repayment Schedule cannot be completed, Please provide the correct duration" ,
            banksResponse.body!!.errorDetails.message , "Expected the error messages to match"
        )
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR , banksResponse.statusCode , "Expected to match")
    }

    @Test
    fun testRepaymentPlanWithZeroMonthsDuration() {
        val request = HttpEntity(
            RepaymentPlanRequestFactory.createRepaymentPlanRequestWithZeroMonthsDuration() , headers
        )
        val banksResponse = testRestTemplate.postForEntity(
            "$baseUrl/generate-plan" , request ,
            ErrorResponse::class.java
        )
        assertEquals(
            "Duration of the Repayment Schedule should be minimum 1" ,
            banksResponse.body!!.errorDetails.message , "Expected the error messages to match"
        )
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR , banksResponse.statusCode , "Expected to match")
    }

    @Test
    fun testRepaymentPlanWithOneMonthsDuration() {
        val request = HttpEntity(
            RepaymentPlanRequestFactory.createRepaymentPlanRequestWithOneMonthDuration() , headers
        )
        val banksResponse = testRestTemplate.postForEntity(
            "$baseUrl/generate-plan" , request ,
            RepaymentPlanResponse::class.java
        )
        assertEquals(1 , banksResponse.body!!.borrowerPayments.size , "Expected the payments to be 1")
        assertEquals(HttpStatus.CREATED , banksResponse.statusCode , "Expected to match")
        val borrowerPayments = banksResponse.body!!.borrowerPayments
        val borrowerPayment = borrowerPayments[0]
        assertEquals("94562.50" , borrowerPayment.borrowerPaymentAmount , "Expected to match")
        assertEquals("89000.0" , borrowerPayment.initialOutstandingPrincipal , "Expected to match")
        assertEquals("5562.50" , borrowerPayment.interest , "Expected to match")
        assertEquals("89000.0" , borrowerPayment.principal , "Expected to match")
        assertEquals("0" , borrowerPayment.remainingOutstandingPrincipal , "Expected to match")
    }

    private fun getAccessToken(baseUrl : String) : String? {
        try {
            val payload = LinkedMultiValueMap<String , String>()
            payload.add("grant_type" , "client_credentials")
            payload.add("scope" , "read")
            val headers = HttpHeaders()
            //@formatter:off
            headers.setBasicAuth(
                String(
                    java.util.Base64
                        .getEncoder()
                        .encode(
                            "$clientId:$clientSecret"
                                .toByteArray(java.nio.charset.StandardCharsets.UTF_8)
                        )
                )
            )
            //@formatter:on
            val request = HttpEntity(payload , headers)
            //@formatter:off
            return ObjectMapper()
                .readValue(
                    testRestTemplate
                        .postForEntity("$baseUrl/oauth/token" , request , String::class.java)
                        .body , JsonNode::class.java
                )
                .get("access_token")
                .asText()
            //@formatter:on
        } catch (e : IOException) {
            logger.error("Exception Occurred while get the response")
        }
        return null
    }

    companion object {
        private const val HOST_NAME = "localhost"
    }
}