package com.occupancy.manager.controller

import com.occupancy.manager.dto.OccupancyUsageResponse
import com.occupancy.manager.factory.OccupancyUsageRequestFactory
import com.occupancy.manager.factory.OccupancyUsageResponseFactory
import com.occupancy.manager.service.OccupancyManagerService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.http.ResponseEntity

/**
 *
 * @author Ramachandra
 */
internal class OccupancyManagerControllerTest() {

    private val occupancyManagerService = mockk<OccupancyManagerService>()

    private var occupancyManagerController = OccupancyManagerController(occupancyManagerService)

    @Test
    fun testCalculateOccupancyFor3Premium3EconomyRooms() {
        val request = OccupancyUsageRequestFactory.createThreePremiumAndThreeEconomyRooms()
        every { occupancyManagerService.calculateOccupancy(request) } returns
                OccupancyUsageResponseFactory.createOccupancyUsageResponseFor3Premium3EconomyRooms()
        val occupancyUsageResponse: ResponseEntity<OccupancyUsageResponse> = occupancyManagerController
            .calculateOccupancy(request)
        assertEquals(
            "3 (EUR 738)", occupancyUsageResponse.body!!.premiumUsage,
            "Expected the premium usage values to match"
        )
        assertEquals(
            "3 (EUR 167)", occupancyUsageResponse.body!!.economyUsage,
            "Expected the economy usage values to match"
        )
    }

    @Test
    fun testCalculateOccupancyFor7Premium1EconomyRoom() {
        val request = OccupancyUsageRequestFactory.createSevenPremiumAndOneEconomyRoom()
        every { occupancyManagerService.calculateOccupancy(request) } returns
                OccupancyUsageResponseFactory.createOccupancyUsageResponseFor7Premium1EconomyRoom()
        val occupancyUsageResponse: ResponseEntity<OccupancyUsageResponse> = occupancyManagerController
            .calculateOccupancy(request)
        assertEquals(
            "7 (EUR 1153)", occupancyUsageResponse.body!!.premiumUsage,
            "Expected the premium usage values to match"
        )
        assertEquals(
            "1 (EUR 45)", occupancyUsageResponse.body!!.economyUsage,
            "Expected the economy usage values to match"
        )
    }

    @Test
    fun testCalculateOccupancyFor2Premium7EconomyRooms() {
        val request = OccupancyUsageRequestFactory.createTwoPremiumAndSevenEconomyRooms()
        every { occupancyManagerService.calculateOccupancy(request) } returns
                OccupancyUsageResponseFactory.createOccupancyUsageResponseFor2Premium7EconomyRooms()
        val occupancyUsageResponse: ResponseEntity<OccupancyUsageResponse> = occupancyManagerController
            .calculateOccupancy(request)
        assertEquals(
            "2 (EUR 583)", occupancyUsageResponse.body!!.premiumUsage,
            "Expected the premium usage values to match"
        )
        assertEquals(
            "4 (EUR 189)", occupancyUsageResponse.body!!.economyUsage,
            "Expected the economy usage values to match"
        )
    }

    @Test
    fun testCalculateOccupancyFor7Premium5EconomyRooms() {
        val request = OccupancyUsageRequestFactory.createSevenPremiumAndFiveEconomyRooms()
        every { occupancyManagerService.calculateOccupancy(request) } returns
                OccupancyUsageResponseFactory.createOccupancyUsageResponseFor7Premium5EconomyRooms()
        val occupancyUsageResponse: ResponseEntity<OccupancyUsageResponse> = occupancyManagerController
            .calculateOccupancy(request)
        assertEquals(
            "6 (EUR 1054)", occupancyUsageResponse.body!!.premiumUsage,
            "Expected the premium usage values to match"
        )
        assertEquals(
            "4 (EUR 189)", occupancyUsageResponse.body!!.economyUsage,
            "Expected the economy usage values to match"
        )
    }
}