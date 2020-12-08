package com.loan.repayment.client

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.loan.repayment.entity.RepaymentPlanRequest
import com.loan.repayment.entity.RepaymentPlanResponse
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.retry.annotation.Retryable
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestTemplate
import java.io.IOException
import java.net.http.HttpTimeoutException
import java.nio.charset.StandardCharsets

/**
 *
 * @author Rama chandra
 */

private val logger = KotlinLogging.logger {}
@Component
class RepaymentPlanClientApi(private val restTemplate : RestTemplate) {

    /**
     * Client ID.
     */
    @Value("{security.oauth2.client.client-id}")
    private val clientId : String = ""

    /**
     * Client Secret.
     */
    @Value("{security.oauth2.client.client-secret}")
    private val clientSecret : String = ""

    /**
     * The method is to connect to PlanGenerator API and Get Response.
     *
     * @param repaymentPlanRequest Payload for Repayment Plan
     */
    @Retryable(value = [HttpTimeoutException::class] , maxAttemptsExpression = "\${retry.max.attempts}")
    fun connectAndGetRepaymentPlan(repaymentPlanRequest : RepaymentPlanRequest) {
        val headers = HttpHeaders()
        headers.add(HttpHeaders.CONTENT_TYPE , MediaType.APPLICATION_JSON_VALUE)
        headers.add(HttpHeaders.AUTHORIZATION , "Bearer $accessToken")
        val request = HttpEntity(repaymentPlanRequest , headers)
        val response = restTemplate.postForEntity("$PLAN_GENERATOR_API/generate-plan" , request ,
                RepaymentPlanResponse::class.java)
        logger.info("Response received {}" , response)
    }
    //@formatter:off
    //@formatter:on
    //@formatter:on
    //@formatter:on
    /**
     * This method gets the access token.
     *
     * @return accessToken
     */
    private val accessToken : String?
        get() {
            try {
                val payload = LinkedMultiValueMap<String, String>()
                payload.add("grant_type" , "client_credentials")
                payload.add("scope" , "read")
                val headers = HttpHeaders()
                //@formatter:off
                headers.setBasicAuth(String(java.util.Base64
                        .getEncoder()
                        .encode("$clientId:$clientSecret"
                                .toByteArray(StandardCharsets.UTF_8))))
                //@formatter:on
                val request = HttpEntity(payload , headers)
                val accessToken = ObjectMapper().readValue(
                        restTemplate.postForEntity("$PLAN_GENERATOR_API/oauth/token" , request , String::class.java).body ,
                        JsonNode::class.java).get("access_token")
                //@formatter:on
                if (accessToken != null) {
                    return accessToken.asText()
                }
                //@formatter:on
            } catch (e : IOException) {
                logger.error("Exception Occurred while get the response")
            }
            return null
        }

    companion object {
        /**
         * This is the connection URL for the Plan Generator API.
         */
        private const val PLAN_GENERATOR_API = "http://localhost:8080"
    }
}