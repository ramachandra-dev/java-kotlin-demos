package com.occupancy.manager.controller

import com.occupancy.manager.dto.OccupancyUsageRequest
import com.occupancy.manager.dto.OccupancyUsageResponse
import com.occupancy.manager.service.IOccupancyManagerService
import lombok.extern.slf4j.Slf4j
import mu.KotlinLogging
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 *
 * @author Ramachandra
 */
internal val logger = KotlinLogging.logger {}

@RestController
@Slf4j
@CrossOrigin(origins = ["*"])
@RestControllerAdvice
class OccupancyManagerController(val occupancyManagerService: IOccupancyManagerService) {

    /**
     *
     * @param occupancyUsageRequest
     * @return occupancyUsageResponse
     */
    @PostMapping(
        path = ["/occupancy-usage"],
        consumes = [APPLICATION_JSON_VALUE],
        produces = [APPLICATION_JSON_VALUE]
    )
    fun calculateOccupancy(
        @RequestBody occupancyUsageRequest: OccupancyUsageRequest): ResponseEntity<OccupancyUsageResponse> {
        logger.info("In calculateOccupancy")
        return ResponseEntity<OccupancyUsageResponse>(
            occupancyManagerService.calculateOccupancy(occupancyUsageRequest),
            CREATED
        )
    }
}