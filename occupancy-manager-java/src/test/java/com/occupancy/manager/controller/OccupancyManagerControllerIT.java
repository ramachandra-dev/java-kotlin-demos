package com.occupancy.manager.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.servlet.ServletContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.occupancy.manager.dto.OccupancyUsageResponse;
import com.occupancy.manager.factory.OccupancyUsageRequestFactory;

/**
 * 
 * @author Ramachandra
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class OccupancyManagerControllerIT {

	@LocalServerPort
	int randomServerPort;

	@Autowired
	private ServletContext servletContext;

	@Autowired
	private TestRestTemplate testRestTemplate;

	private String baseUrl = "";
	private HttpHeaders headers;

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
	}

	/**
	 * This method is to test Occupancy Plan with valid payload of occupancy request
	 */
	@Test
	void testOccupanyUsage() {
		final var request = new HttpEntity<>(OccupancyUsageRequestFactory.createThreePremiumAndThreeEconomyRooms(), headers);
		final var occupancyUsageResponse = testRestTemplate.postForEntity(baseUrl + "/occupancy-usage", request,
				OccupancyUsageResponse.class);
		assertEquals("3 (EUR 738)", occupancyUsageResponse.getBody().getPremiumUsage(),
				"Expected the premium usage values to match");
		assertEquals("3 (EUR 167)", occupancyUsageResponse.getBody().getEconomyUsage(),
				"Expected the economy usage values to match");
	}

}