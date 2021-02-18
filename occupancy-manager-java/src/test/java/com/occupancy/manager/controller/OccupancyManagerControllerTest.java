package com.occupancy.manager.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.occupancy.manager.factory.OccupancyUsageRequestFactory;
import com.occupancy.manager.factory.OccupancyUsageResponseFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.occupancy.manager.dto.OccupancyUsageRequest;
import com.occupancy.manager.service.OccupancyManagerService;

/**
 * 
 * @author Ramachandra
 *
 */
class OccupancyManagerControllerTest {

	@InjectMocks
	OccupancyManagerController occupancyManagerController;

	@Mock
	private OccupancyManagerService occupancyManagerService;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testcalculateOccupancyFor3Premium3EconomyRooms() {
		when(occupancyManagerService.calculateOccupancy(any(OccupancyUsageRequest.class)))
				.thenReturn(OccupancyUsageResponseFactory.createOccupancyUsageResponseFor3Premium3EconomyRooms());
		final var occupancyUsageResponse = occupancyManagerController
				.calculateOccupancy(OccupancyUsageRequestFactory.createThreePremiumAndThreeEconomyRooms());
		assertEquals("3 (EUR 738)", occupancyUsageResponse.getBody().getPremiumUsage(),
				"Expected the premium usage values to match");
		assertEquals("3 (EUR 167)", occupancyUsageResponse.getBody().getEconomyUsage(),
				"Expected the economy usage values to match");
	}
	
	@Test
	void testcalculateOccupancyFor7Premium1EconomyRoom() {
		when(occupancyManagerService.calculateOccupancy(any(OccupancyUsageRequest.class)))
				.thenReturn(OccupancyUsageResponseFactory.createOccupancyUsageResponseFor7Premium1EconomyRoom());
		final var occupancyUsageResponse = occupancyManagerController
				.calculateOccupancy(OccupancyUsageRequestFactory.createSevenPremiumAndOneEconomyRoom());
		assertEquals("7 (EUR 1153)", occupancyUsageResponse.getBody().getPremiumUsage(),
				"Expected the premium usage values to match");
		assertEquals("1 (EUR 45)", occupancyUsageResponse.getBody().getEconomyUsage(),
				"Expected the economy usage values to match");
	}
	
	@Test
	void testcalculateOccupancyFor2Premium7EconomyRooms() {
		when(occupancyManagerService.calculateOccupancy(any(OccupancyUsageRequest.class)))
				.thenReturn(OccupancyUsageResponseFactory.createOccupancyUsageResponseFor2Premium7EconomyRooms());
		final var occupancyUsageResponse = occupancyManagerController
				.calculateOccupancy(OccupancyUsageRequestFactory.createTwoPremiumAndSevenEconomyRooms());
		assertEquals("2 (EUR 583)", occupancyUsageResponse.getBody().getPremiumUsage(),
				"Expected the premium usage values to match");
		assertEquals("4 (EUR 189)", occupancyUsageResponse.getBody().getEconomyUsage(),
				"Expected the economy usage values to match");
	}
	
	@Test
	void testcalculateOccupancyFor7Premium5EconomyRooms() {
		when(occupancyManagerService.calculateOccupancy(any(OccupancyUsageRequest.class)))
				.thenReturn(OccupancyUsageResponseFactory.createOccupancyUsageResponseFor7Premium5EconomyRooms());
		final var occupancyUsageResponse = occupancyManagerController
				.calculateOccupancy(OccupancyUsageRequestFactory.createSevenPremiumAndFiveEconomyRooms());
		assertEquals("6 (EUR 1054)", occupancyUsageResponse.getBody().getPremiumUsage(),
				"Expected the premium usage values to match");
		assertEquals("4 (EUR 189)", occupancyUsageResponse.getBody().getEconomyUsage(),
				"Expected the economy usage values to match");
	}
}