package com.occupancy.manager.service

import com.occupancy.manager.dto.OccupancyDataStore
import com.occupancy.manager.dto.OccupancyUsageRequest
import com.occupancy.manager.dto.OccupancyUsageResponse
import mu.KotlinLogging
import org.springframework.stereotype.Service
import java.util.Collections.sort
import java.util.function.Predicate
import java.util.stream.Collectors

/**
 *
 * @author Ramachandra
 */
internal val logger = KotlinLogging.logger {}
@Service
class OccupancyManagerService : IOccupancyManagerService {
    override fun calculateOccupancy(occupancyUsageRequest: OccupancyUsageRequest): OccupancyUsageResponse {
        val premiumRooms: Int = occupancyUsageRequest.premiumRooms
        val economyRooms: Int = occupancyUsageRequest.economyRooms
        logger.info("Premium Rooms {}", premiumRooms)
        logger.info("Economy Rooms {}", economyRooms)
        val partitionCustomers = getPartitionCustomers(occupancyUsageRequest)
        val economyCustomers = partitionCustomers[0]
        val premiumCustomers = partitionCustomers[1]
        logger.info("Economy Customers {}", economyCustomers)
        logger.info("Premium Customers {}", premiumCustomers)
        val occupancyDataStore = OccupancyDataStore()
        calculatePremiumCustomersUsage(premiumCustomers, premiumRooms, occupancyDataStore)
        calculateEconomyCustomersWithPremiumUpgrade(economyCustomers, economyRooms, occupancyDataStore)
        logger.info(
            "Usage Premium: {} (EUR {})", occupancyDataStore.premiumRoomsOccupied,
            occupancyDataStore.premiumUsage
        )
        logger.info(
            "Usage Economy: {} (EUR {})", occupancyDataStore.economyRoomsOccupied,
            occupancyDataStore.economyUsage
        )
        return OccupancyUsageResponse(
            String.format(
                OCCUPANCY_USAGE, occupancyDataStore.premiumRoomsOccupied,
                occupancyDataStore.premiumUsage
            ), String.format(
                OCCUPANCY_USAGE, occupancyDataStore.economyRoomsOccupied,
                occupancyDataStore.economyUsage
            )
        )
    }

    /**
     * This method will sort and provides the partition customers by margin
     *
     * @param occupancyUsageRequest
     * @return
     */
    private fun getPartitionCustomers(occupancyUsageRequest: OccupancyUsageRequest): List<List<Int>> {
        val customerPayments: List<Int> = occupancyUsageRequest.payments
        sort(customerPayments)
        val minimumPremiumCondition =
            Predicate { value: Int -> value >= occupancyUsageRequest.minimumPremium }
        return partitionCustomersByMinimumPremium(customerPayments, minimumPremiumCondition)
    }

    /**
     * This method is to calculate the sum of all list
     *
     * @param customers
     * @return sum
     */
    private fun calculateSum(customers: List<Int>): Int {
        return customers.stream().mapToInt { obj: Int -> obj }.sum()
    }

    /**
     * This method is the calculate the usage of the premium customers
     *
     * @param premiumCustomers
     * @param premiumRooms
     * @param occupancyDataStore
     */
    private fun calculatePremiumCustomersUsage(
        premiumCustomers: List<Int>, premiumRooms: Int,
        occupancyDataStore: OccupancyDataStore
    ) {
        if (premiumRooms > 0 && premiumCustomers.isNotEmpty()) {
            val totalPremiumCustomers = premiumCustomers.size
            if (premiumRooms >= totalPremiumCustomers) {
                occupancyDataStore.premiumUsage =
                    calculateSum(occupancyDataStore.premiumUsage, calculateSum(premiumCustomers))

                occupancyDataStore.premiumRoomsVacant =
                    calculateSum(occupancyDataStore.premiumRoomsVacant, premiumRooms - totalPremiumCustomers)

                occupancyDataStore.premiumRoomsOccupied=
                    calculateSum(occupancyDataStore.premiumRoomsOccupied, totalPremiumCustomers)

            } else {
                val premiumCustomersOccupied = getSubListCustomers(
                    premiumCustomers,
                    totalPremiumCustomers - premiumRooms, totalPremiumCustomers
                )
                occupancyDataStore.premiumUsage =
                    calculateSum(occupancyDataStore.premiumUsage, calculateSum(premiumCustomersOccupied))

                occupancyDataStore.premiumRoomsOccupied =
                    calculateSum(occupancyDataStore.premiumRoomsOccupied, premiumCustomersOccupied.size)

            }
        }
    }

    /**
     *
     * @param economyCustomers
     * @param economyRooms
     * @param occupancyDataStore
     */
    private fun calculateEconomyCustomersWithPremiumUpgrade(
        economyCustomers: List<Int>, economyRooms: Int,
        occupancyDataStore: OccupancyDataStore
    ) {
        // Validating the upgrade of the economy customers
        if (economyRooms > 0 && economyCustomers.isNotEmpty()) {
            val totalEconomyCustomers = economyCustomers.size
            // Check whether the economy customers are prefilled even if the customers are
            // upgraded to premium
            if (totalEconomyCustomers >= economyRooms - 1 && occupancyDataStore.premiumRoomsVacant > 0) {
                val economyCustomersOccupied = if (economyRooms >= totalEconomyCustomers) getSubListCustomers(
                    economyCustomers,
                    0,
                    totalEconomyCustomers
                ) else getSubListCustomers(economyCustomers, 0, totalEconomyCustomers - economyRooms)
                val economyPremiumOccupied = economyCustomers.stream().collect(Collectors.toList())
                economyPremiumOccupied.removeAll(economyCustomersOccupied)
                if (economyPremiumOccupied.isNotEmpty()) {
                    occupancyDataStore.premiumUsage =
                        calculateSum(occupancyDataStore.premiumUsage, calculateSum(economyPremiumOccupied))

                    occupancyDataStore.premiumRoomsOccupied =
                        calculateSum(occupancyDataStore.premiumRoomsOccupied, economyPremiumOccupied.size)

                }
                calculateEconomyCustomersUsage(economyCustomersOccupied, economyRooms, occupancyDataStore)
            } else {
                calculateEconomyCustomersUsage(economyCustomers, economyRooms, occupancyDataStore)
            }
        }
    }

    /**
     * This method is the calculate the usage of the economy customers
     *
     * @param economyCustomers
     * @param economyRooms
     * @param occupancyDataStore
     */
    private fun calculateEconomyCustomersUsage(
        economyCustomers: List<Int>, economyRooms: Int,
        occupancyDataStore: OccupancyDataStore
    ) {
        val totalEconomyCustomers = economyCustomers.size
        if (economyRooms >= totalEconomyCustomers) {
            occupancyDataStore
                .economyUsage=calculateSum(occupancyDataStore.economyUsage, calculateSum(economyCustomers))
            occupancyDataStore.economyRoomsOccupied =
                calculateSum(occupancyDataStore.economyRoomsOccupied, totalEconomyCustomers)

        } else {
            val economyCustomersOccupied = getSubListCustomers(
                economyCustomers, totalEconomyCustomers - economyRooms,
                totalEconomyCustomers
            )
            occupancyDataStore.economyUsage =
                calculateSum(occupancyDataStore.economyUsage, calculateSum(economyCustomersOccupied))
            
            occupancyDataStore.economyRoomsOccupied =
                calculateSum(occupancyDataStore.economyRoomsOccupied, economyCustomersOccupied.size)

        }
    }

    /**
     * This method is to calculate the sum of two values
     *
     * @param initialData
     * @param updatedData
     * @return sum
     */
    private fun calculateSum(initialData: Int, updatedData: Int): Int {
        return initialData + updatedData
    }

    /**
     * This method is to build the sublist of the customers
     *
     * @param customers
     * @param startIndex
     * @param lastIndex
     * @return sublist
     */
    private fun getSubListCustomers(customers: List<Int>, startIndex: Int, lastIndex: Int): List<Int> {
        return customers.stream().skip(startIndex.toLong()).limit(lastIndex.toLong()).collect(Collectors.toList())
    }

    /**
     * This method will create two list of customers based on the predicate
     *
     * @param customers
     * @param condition
     * @return list of customers
     */
    private fun <T> partitionCustomersByMinimumPremium(customers: List<T>, condition: Predicate<T>): List<List<T>> {
        return customers.stream().collect(Collectors.partitioningBy { s: T ->
            condition.test(
                s
            )
        }).values.stream()
            .collect(Collectors.toList())
    }

    companion object {
        private const val OCCUPANCY_USAGE = "%s (EUR %s)"
    }
}