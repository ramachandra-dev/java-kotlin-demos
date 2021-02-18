package com.occupancy.manager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.occupancy.manager.factory.OccupancyUsageRequestFactory;

/**
 * 
 * @author Ramachandra
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class OccupancyManagerServiceIT {

	@Autowired
	OccupancyManagerService occupancyManagerService;

	@Test
	void testcalculateOccupancyFor3Premium3EconomyRooms() {
		final var occupancyUsageResponse = occupancyManagerService
				.calculateOccupancy(OccupancyUsageRequestFactory.createThreePremiumAndThreeEconomyRooms());
		assertEquals("3 (EUR 738)", occupancyUsageResponse.getPremiumUsage(),
				"Expected the premium usage values to match");
		assertEquals("3 (EUR 167)", occupancyUsageResponse.getEconomyUsage(),
				"Expected the economy usage values to match");
	}
	
	@Test
	void testcalculateOccupancyFor7Premium1EconomyRoom() {
		final var occupancyUsageResponse = occupancyManagerService
				.calculateOccupancy(OccupancyUsageRequestFactory.createSevenPremiumAndOneEconomyRoom());
		assertEquals("7 (EUR 1153)", occupancyUsageResponse.getPremiumUsage(),
				"Expected the premium usage values to match");
		assertEquals("1 (EUR 45)", occupancyUsageResponse.getEconomyUsage(),
				"Expected the economy usage values to match");
	}
	
	@Test
	void testcalculateOccupancyFor2Premium7EconomyRooms() {
		final var occupancyUsageResponse = occupancyManagerService
				.calculateOccupancy(OccupancyUsageRequestFactory.createTwoPremiumAndSevenEconomyRooms());
		assertEquals("2 (EUR 583)", occupancyUsageResponse.getPremiumUsage(),
				"Expected the premium usage values to match");
		assertEquals("4 (EUR 189)", occupancyUsageResponse.getEconomyUsage(),
				"Expected the economy usage values to match");
	}
	
	@Test
	void testcalculateOccupancyFor7Premium5EconomyRooms() {
		final var occupancyUsageResponse = occupancyManagerService
				.calculateOccupancy(OccupancyUsageRequestFactory.createSevenPremiumAndFiveEconomyRooms());
		assertEquals("6 (EUR 1054)", occupancyUsageResponse.getPremiumUsage(),
				"Expected the premium usage values to match");
		assertEquals("4 (EUR 189)", occupancyUsageResponse.getEconomyUsage(),
				"Expected the economy usage values to match");
	}

}