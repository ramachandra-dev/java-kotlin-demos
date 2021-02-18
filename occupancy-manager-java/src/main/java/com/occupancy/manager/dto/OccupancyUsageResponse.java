package com.occupancy.manager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
public class OccupancyUsageResponse {

	@JsonProperty("Usage Premium")
	private String premiumUsage;
	@JsonProperty("Usage Economy")
	private String economyUsage;
}
