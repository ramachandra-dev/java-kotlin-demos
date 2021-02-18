package com.occupancy.manager.dto

import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * @author Ramachandra
 */
data class OccupancyUsageResponse(
	@JsonProperty("Usage Premium") var premiumUsage: String,
	@JsonProperty("Usage Economy") var economyUsage: String,
)