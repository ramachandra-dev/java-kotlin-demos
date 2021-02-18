package com.occupancy.manager.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
*
* @author Ramachandra
*
*/

@Setter
@Getter
@ToString
public class OccupancyUsageRequest {

	@JsonProperty("Free Premium Rooms")
	private Integer premiumRooms;
	@JsonProperty("Free Economy Rooms")
	private Integer economyRooms;
	@JsonProperty("Payments")
	private List<Integer> payments;
	@JsonProperty("Minimum Premium")
	private Integer minimumPremium;
}
