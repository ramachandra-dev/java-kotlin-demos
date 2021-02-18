package com.occupancy.manager.controller

import com.occupancy.manager.dto.OccupancyUsageRequest
import com.occupancy.manager.dto.OccupancyUsageResponse
import com.occupancy.manager.factory.OccupancyUsageRequestFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import javax.servlet.ServletContext

/**
 *
 * @author Ramachandra
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class OccupancyManagerControllerIT {

	@LocalServerPort
	var randomServerPort = 0

	@Autowired
	private lateinit var servletContext : ServletContext

	@Autowired
	private lateinit var testRestTemplate : TestRestTemplate
	private var baseUrl = ""
	private lateinit var headers : HttpHeaders

	/**
	 * This method runs before each test case to setup basic headers and construct
	 * baseurl.
	 */
	@BeforeEach
	fun init() {
		String.format("http://%s:%s%s", HOST_NAME, randomServerPort, servletContext.contextPath)
			.also { baseUrl = it }
		headers = HttpHeaders()
		headers.add(
			HttpHeaders.CONTENT_TYPE,
			org.springframework.http.MediaType.APPLICATION_JSON_VALUE
		)
	}

	/**
	 * This method is to test Occupancy Plan with valid payload of occupancy request
	 */
	@Test
	fun testOccupancyUsage() {
		val request: HttpEntity<OccupancyUsageRequest> = HttpEntity<OccupancyUsageRequest>(
			OccupancyUsageRequestFactory.createThreePremiumAndThreeEconomyRooms(),
			headers
		)
		val occupancyUsageResponse: ResponseEntity<OccupancyUsageResponse> =
			testRestTemplate.postForEntity(
				"$baseUrl/occupancy-usage", request,
				OccupancyUsageResponse::class.java
			)
		assertEquals(
			"3 (EUR 738)", occupancyUsageResponse.body!!.premiumUsage,
			"Expected the premium usage values to match"
		)
		assertEquals(
			"3 (EUR 167)", occupancyUsageResponse.body!!.economyUsage,
			"Expected the economy usage values to match"
		)
	}

	companion object {
		private const val HOST_NAME = "localhost"
	}
}