package com.occupancy.manager.factory

import com.occupancy.manager.dto.OccupancyUsageResponse
import com.occupancy.manager.util.JsonUtil

/**
 *
 * @author Ramachandra
 */
object OccupancyUsageResponseFactory {
	/**
	 *
	 * @return OccupancyUsageResponse
	 */
	fun createOccupancyUsageResponseFor2Premium7EconomyRooms(): OccupancyUsageResponse {
		return JsonUtil().readFileAndGetData(
			"2Premium7EconomyRoomsResponse.json",
			OccupancyUsageResponse::class.java
		) as OccupancyUsageResponse
	}

	/**
	 * @return OccupancyUsageResponse
	 */
	fun createOccupancyUsageResponseFor3Premium3EconomyRooms(): OccupancyUsageResponse {
		return JsonUtil().readFileAndGetData(
			"3Premium3EconomyRoomsResponse.json",
			OccupancyUsageResponse::class.java
		) as OccupancyUsageResponse
	}

	/**
	 * @return OccupancyUsageResponse
	 */
	fun createOccupancyUsageResponseFor7Premium1EconomyRoom(): OccupancyUsageResponse {
		return JsonUtil().readFileAndGetData(
			"7Premium1EconomyRoomResponse.json",
			OccupancyUsageResponse::class.java
		) as OccupancyUsageResponse
	}

	/**
	 * @return OccupancyUsageResponse
	 */
	fun createOccupancyUsageResponseFor7Premium5EconomyRooms(): OccupancyUsageResponse {
		return JsonUtil().readFileAndGetData(
			"7Premium5EconomyRoomsResponse.json",
			OccupancyUsageResponse::class.java
		) as OccupancyUsageResponse
	}
}