package com.occupancy.manager.factory;

import com.occupancy.manager.dto.OccupancyUsageRequest;
import com.occupancy.manager.util.JsonUtil;

/**
 * 
 * @author Ramachandra
 *
 */
public class OccupancyUsageRequestFactory {

	private static OccupancyUsageRequest buildOccupancyUsageRequest(int premiumRooms, int economyRooms) {
		OccupancyUsageRequest request = new OccupancyUsageRequest();
		request.setEconomyRooms(economyRooms);
		request.setPremiumRooms(premiumRooms);
		request.setPayments(new JsonUtil().readFileAndGetData("payments.json"));
		request.setMinimumPremium(100);
		return request;
	}

	/**
	 * This method is to create OccupancyUsageRequest with 3 Premium Rooms and 3
	 * Economy Rooms vacancy
	 * 
	 * @return OccupancyUsageRequest
	 */
	public static OccupancyUsageRequest createThreePremiumAndThreeEconomyRooms() {
		return buildOccupancyUsageRequest(3, 3);
	}

	/**
	 * This method is to create OccupancyUsageRequest with 7 Premium Rooms and 5
	 * Economy Rooms vacancy
	 * 
	 * @return OccupancyUsageRequest
	 */
	public static OccupancyUsageRequest createSevenPremiumAndFiveEconomyRooms() {
		return buildOccupancyUsageRequest(7, 5);
	}

	/**
	 * This method is to create OccupancyUsageRequest with 2 Premium Rooms and 7
	 * Economy Rooms vacancy
	 * 
	 * @return OccupancyUsageRequest
	 */
	public static OccupancyUsageRequest createTwoPremiumAndSevenEconomyRooms() {
		return buildOccupancyUsageRequest(2, 7);
	}

	/**
	 * This method is to create OccupancyUsageRequest with 7 Premium Rooms and 1
	 * Economy Room vacancy
	 * 
	 * @return OccupancyUsageRequest
	 */
	public static OccupancyUsageRequest createSevenPremiumAndOneEconomyRoom() {
		return buildOccupancyUsageRequest(7, 1);
	}

}
