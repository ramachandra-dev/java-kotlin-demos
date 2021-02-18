package com.occupancy.manager.factory;

import com.occupancy.manager.dto.OccupancyUsageResponse;
import com.occupancy.manager.util.JsonUtil;

/**
 * 
 * @author Ramachandra
 *
 */
public class OccupancyUsageResponseFactory {

	/**
	 * 
	 * @return OccupancyUsageResponse
	 */
	public static OccupancyUsageResponse createOccupancyUsageResponseFor2Premium7EconomyRooms() {
		return (OccupancyUsageResponse) new JsonUtil().readFileAndGetData("2Premium7EconomyRoomsResponse.json",
				OccupancyUsageResponse.class);
	}

	/**
	 * @return OccupancyUsageResponse
	 */
	public static OccupancyUsageResponse createOccupancyUsageResponseFor3Premium3EconomyRooms() {
		return (OccupancyUsageResponse) new JsonUtil().readFileAndGetData("3Premium3EconomyRoomsResponse.json",
				OccupancyUsageResponse.class);
	}

	/**
	 * @return OccupancyUsageResponse
	 */
	public static OccupancyUsageResponse createOccupancyUsageResponseFor7Premium1EconomyRoom() {
		return (OccupancyUsageResponse) new JsonUtil().readFileAndGetData("7Premium1EconomyRoomResponse.json",
				OccupancyUsageResponse.class);
	}

	/**
	 * @return OccupancyUsageResponse
	 */
	public static OccupancyUsageResponse createOccupancyUsageResponseFor7Premium5EconomyRooms() {
		return (OccupancyUsageResponse) new JsonUtil().readFileAndGetData("7Premium5EconomyRoomsResponse.json",
				OccupancyUsageResponse.class);
	}

}
