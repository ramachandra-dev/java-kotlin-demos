package com.occupancy.manager.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
*
* @author Ramachandra
*
*/

@Getter
@Setter
@ToString
public class OccupancyDataStore {
	private int premiumUsage;
	private int economyUsage;
	private int premiumRoomsVacant;
	private int premiumRoomsOccupied;
	private int economyRoomsOccupied;
}
