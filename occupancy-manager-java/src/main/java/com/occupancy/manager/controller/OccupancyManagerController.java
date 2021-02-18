package com.occupancy.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.occupancy.manager.dto.OccupancyUsageRequest;
import com.occupancy.manager.dto.OccupancyUsageResponse;
import com.occupancy.manager.service.IOccupancyManagerService;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Ramachandra
 *
 */
@RestController
@Slf4j
@CrossOrigin(origins = "*")
@RestControllerAdvice
public class OccupancyManagerController {

	/**
	 * OccupancyManagerService.
	 */
	@Autowired
	private IOccupancyManagerService occupancyManagerService;

	/**
	 *
	 * @param occupancyUsageRequest
	 * @return occupancyUsageResponse
	 */
	@PostMapping(path = "/occupancy-usage", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OccupancyUsageResponse> calculateOccupancy(
			@RequestBody final OccupancyUsageRequest occupancyUsageRequest) {
		log.info("In calculateOccupancy");
		return new ResponseEntity<>(occupancyManagerService.calculateOccupancy(occupancyUsageRequest),
				HttpStatus.CREATED);
	}
}
