package com.occupancy.manager.factory

import com.occupancy.manager.dto.OccupancyUsageRequest
import com.occupancy.manager.util.JsonUtil

/**
 *
 * @author Ramachandra
 */
object OccupancyUsageRequestFactory {
	private fun buildOccupancyUsageRequest(premiumRooms: Int, economyRooms: Int): OccupancyUsageRequest {
		return OccupancyUsageRequest(
			premiumRooms,
			economyRooms,
			JsonUtil().readFileAndGetData("payments.json"),
			100,
		)
	}

	/**
	 * This method is to create OccupancyUsageRequest with 3 Premium Rooms and 3
	 * Economy Rooms vacancy
	 *
	 * @return OccupancyUsageRequest
	 */
	fun createThreePremiumAndThreeEconomyRooms(): OccupancyUsageRequest {
		return buildOccupancyUsageRequest(3, 3)
	}

	/**
	 * This method is to create OccupancyUsageRequest with 7 Premium Rooms and 5
	 * Economy Rooms vacancy
	 *
	 * @return OccupancyUsageRequest
	 */
	fun createSevenPremiumAndFiveEconomyRooms(): OccupancyUsageRequest {
		return buildOccupancyUsageRequest(7, 5)
	}

	/**
	 * This method is to create OccupancyUsageRequest with 2 Premium Rooms and 7
	 * Economy Rooms vacancy
	 *
	 * @return OccupancyUsageRequest
	 */
	fun createTwoPremiumAndSevenEconomyRooms(): OccupancyUsageRequest {
		return buildOccupancyUsageRequest(2, 7)
	}

	/**
	 * This method is to create OccupancyUsageRequest with 7 Premium Rooms and 1
	 * Economy Room vacancy
	 *
	 * @return OccupancyUsageRequest
	 */
	fun createSevenPremiumAndOneEconomyRoom(): OccupancyUsageRequest {
		return buildOccupancyUsageRequest(7, 1)
	}
}