package com.occupancy.manager.service

import com.occupancy.manager.dto.OccupancyUsageRequest
import com.occupancy.manager.dto.OccupancyUsageResponse

/**
 *
 * @author Ramachandra
 */
interface IOccupancyManagerService {
	fun calculateOccupancy(occupancyUsageRequest: OccupancyUsageRequest): OccupancyUsageResponse
}