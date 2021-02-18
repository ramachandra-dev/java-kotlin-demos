package com.occupancy.manager.service

import com.occupancy.manager.dto.OccupancyUsageResponse
import com.occupancy.manager.factory.OccupancyUsageRequestFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

/**
 *
 * @author Ramachandra
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class OccupancyManagerServiceIT(@Autowired val occupancyManagerService: OccupancyManagerService) {
    @Test
    fun testCalculateOccupancyFor3Premium3EconomyRooms() {
        val occupancyUsageResponse: OccupancyUsageResponse = occupancyManagerService
            .calculateOccupancy(OccupancyUsageRequestFactory.createThreePremiumAndThreeEconomyRooms())
        assertEquals(
            "3 (EUR 738)", occupancyUsageResponse.premiumUsage,
            "Expected the premium usage values to match"
        )
        assertEquals(
            "3 (EUR 167)", occupancyUsageResponse.economyUsage,
            "Expected the economy usage values to match"
        )
    }

    @Test
    fun testCalculateOccupancyFor7Premium1EconomyRoom() {
        val occupancyUsageResponse: OccupancyUsageResponse = occupancyManagerService
            .calculateOccupancy(OccupancyUsageRequestFactory.createSevenPremiumAndOneEconomyRoom())
        assertEquals(
            "7 (EUR 1153)", occupancyUsageResponse.premiumUsage,
            "Expected the premium usage values to match"
        )
        assertEquals(
            "1 (EUR 45)", occupancyUsageResponse.economyUsage,
            "Expected the economy usage values to match"
        )
    }

    @Test
    fun testCalculateOccupancyFor2Premium7EconomyRooms() {
        val occupancyUsageResponse: OccupancyUsageResponse = occupancyManagerService
            .calculateOccupancy(OccupancyUsageRequestFactory.createTwoPremiumAndSevenEconomyRooms())
        assertEquals(
            "2 (EUR 583)", occupancyUsageResponse.premiumUsage,
            "Expected the premium usage values to match"
        )
        assertEquals(
            "4 (EUR 189)", occupancyUsageResponse.economyUsage,
            "Expected the economy usage values to match"
        )
    }

    @Test
    fun testCalculateOccupancyFor7Premium5EconomyRooms() {
        val occupancyUsageResponse: OccupancyUsageResponse = occupancyManagerService
            .calculateOccupancy(OccupancyUsageRequestFactory.createSevenPremiumAndFiveEconomyRooms())
        assertEquals(
            "6 (EUR 1054)", occupancyUsageResponse.premiumUsage,
            "Expected the premium usage values to match"
        )
        assertEquals(
            "4 (EUR 189)", occupancyUsageResponse.economyUsage,
            "Expected the economy usage values to match"
        )
    }
}