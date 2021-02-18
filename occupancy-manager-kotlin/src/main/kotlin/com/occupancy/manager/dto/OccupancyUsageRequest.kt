package com.occupancy.manager.dto

import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * @author Ramachandra
 */
data class OccupancyUsageRequest(
	@JsonProperty("Free Premium Rooms")
	var premiumRooms: Int,
	@JsonProperty("Free Economy Rooms")
    var economyRooms: Int,
	@JsonProperty("Payments")
	var payments: List<Int>,
	@JsonProperty("Minimum Premium")
	var minimumPremium: Int,
)