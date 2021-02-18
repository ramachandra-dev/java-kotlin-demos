package com.occupancy.manager

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 *
 * @author Ramachandra
 */
@SpringBootApplication
object OccupancyManagerApplication {
	@JvmStatic
	fun main(args: Array<String>) {
		SpringApplication.run(OccupancyManagerApplication::class.java, *args)
	}
}