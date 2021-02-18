package com.occupancy.manager.service;

import static java.util.Collections.sort;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.occupancy.manager.dto.OccupancyUsageRequest;
import com.occupancy.manager.dto.OccupancyUsageResponse;
import com.occupancy.manager.dto.OccupancyDataStore;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Ramachandra
 *
 */
@Slf4j
@Service
public class OccupancyManagerService implements IOccupancyManagerService {

	private static final String OCCUPANCY_USAGE = "%s (EUR %s)";

	public OccupancyUsageResponse calculateOccupancy(OccupancyUsageRequest occupancyUsageRequest) {

		var premiumRooms = occupancyUsageRequest.getPremiumRooms();
		var economyRooms = occupancyUsageRequest.getEconomyRooms();

		log.info("Premium Rooms {}", premiumRooms);
		log.info("Economy Rooms {}", economyRooms);

		var partitionCustomers = getPartitionCustomers(occupancyUsageRequest);
		var economyCustomers = partitionCustomers.get(0);
		var premiumCustomers = partitionCustomers.get(1);

		log.info("Economy Customers {}", economyCustomers);
		log.info("Premium Customers {}", premiumCustomers);

		var occupancyDataStore = new OccupancyDataStore();

		calculatePremiumCustomersUsage(premiumCustomers, premiumRooms, occupancyDataStore);

		calculateEconomyCustomersWithPremiumUpgrade(economyCustomers, economyRooms, occupancyDataStore);

		log.info("Usage Premium: {} (EUR {})", occupancyDataStore.getPremiumRoomsOccupied(),
				occupancyDataStore.getPremiumUsage());
		log.info("Usage Economy: {} (EUR {})", occupancyDataStore.getEconomyRoomsOccupied(),
				occupancyDataStore.getEconomyUsage());
		return new OccupancyUsageResponse(
				String.format(OCCUPANCY_USAGE, occupancyDataStore.getPremiumRoomsOccupied(),
						occupancyDataStore.getPremiumUsage()),
				String.format(OCCUPANCY_USAGE, occupancyDataStore.getEconomyRoomsOccupied(),
						occupancyDataStore.getEconomyUsage()));
	}

	/**
	 * This method will sort and provides the partition customers by margin
	 * 
	 * @param occupancyUsageRequest
	 * @return
	 */
	private List<List<Integer>> getPartitionCustomers(OccupancyUsageRequest occupancyUsageRequest) {
		var customerPayments = occupancyUsageRequest.getPayments();
		sort(customerPayments);
		Predicate<Integer> minimumPremiumCondition = value -> value >= occupancyUsageRequest.getMinimumPremium();
		return partitionCustomersByMinimumPremium(customerPayments, minimumPremiumCondition);
	}

	/**
	 * This method is to calculate the sum of all list
	 * 
	 * @param customers
	 * @return sum
	 */
	private int calculateSum(List<Integer> customers) {
		return customers.stream().mapToInt(Integer::intValue).sum();
	}

	/**
	 * This method is the calculate the usage of the premium customers
	 * 
	 * @param premiumCustomers
	 * @param premiumRooms
	 * @param occupancyDataStore
	 */
	private void calculatePremiumCustomersUsage(List<Integer> premiumCustomers, int premiumRooms,
			OccupancyDataStore occupancyDataStore) {

		if (premiumRooms > 0 && !premiumCustomers.isEmpty()) {
			var totalPremiumCustomers = premiumCustomers.size();
			if (premiumRooms >= totalPremiumCustomers) {
				occupancyDataStore.setPremiumUsage(
						calculateSum(occupancyDataStore.getPremiumUsage(), calculateSum(premiumCustomers)));
				occupancyDataStore.setPremiumRoomsVacant(
						calculateSum(occupancyDataStore.getPremiumRoomsVacant(), premiumRooms - totalPremiumCustomers));
				occupancyDataStore.setPremiumRoomsOccupied(
						calculateSum(occupancyDataStore.getPremiumRoomsOccupied(), totalPremiumCustomers));
			} else {
				var premiumCustomersOccupied = getSubListCustomers(premiumCustomers,
						totalPremiumCustomers - premiumRooms, totalPremiumCustomers);
				occupancyDataStore.setPremiumUsage(
						calculateSum(occupancyDataStore.getPremiumUsage(), calculateSum(premiumCustomersOccupied)));
				occupancyDataStore.setPremiumRoomsOccupied(
						calculateSum(occupancyDataStore.getPremiumRoomsOccupied(), premiumCustomersOccupied.size()));
			}
		}
	}

	/**
	 * 
	 * @param economyCustomers
	 * @param economyRooms
	 * @param occupancyDataStore
	 */
	private void calculateEconomyCustomersWithPremiumUpgrade(List<Integer> economyCustomers, Integer economyRooms,
			OccupancyDataStore occupancyDataStore) {
		// Validating the upgrade of the economy customers
		if (economyRooms > 0 && !economyCustomers.isEmpty()) {
			var totalEconomyCustomers = economyCustomers.size();
			// Check whether the economy customers are prefilled even if the customers are
			// upgraded to premium
			if (totalEconomyCustomers >= (economyRooms - 1) && occupancyDataStore.getPremiumRoomsVacant() > 0) {
				var economyCustomersOccupied = economyRooms >= totalEconomyCustomers
						? getSubListCustomers(economyCustomers, 0, totalEconomyCustomers)
						: getSubListCustomers(economyCustomers, 0, totalEconomyCustomers - economyRooms);
				var economyPremiumOccupied = economyCustomers.stream().collect(toList());
				economyPremiumOccupied.removeAll(economyCustomersOccupied);
				if (!economyPremiumOccupied.isEmpty()) {
					occupancyDataStore.setPremiumUsage(
							calculateSum(occupancyDataStore.getPremiumUsage(), calculateSum(economyPremiumOccupied)));
					occupancyDataStore.setPremiumRoomsOccupied(
							calculateSum(occupancyDataStore.getPremiumRoomsOccupied(), economyPremiumOccupied.size()));
				}
				calculateEconomyCustomersUsage(economyCustomersOccupied, economyRooms, occupancyDataStore);
			} else {
				calculateEconomyCustomersUsage(economyCustomers, economyRooms, occupancyDataStore);
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
	private void calculateEconomyCustomersUsage(List<Integer> economyCustomers, int economyRooms,
			OccupancyDataStore occupancyDataStore) {
		var totalEconomyCustomers = economyCustomers.size();
		if (economyRooms >= totalEconomyCustomers) {
			occupancyDataStore
					.setEconomyUsage(calculateSum(occupancyDataStore.getEconomyUsage(), calculateSum(economyCustomers)));
			occupancyDataStore.setEconomyRoomsOccupied(
					calculateSum(occupancyDataStore.getEconomyRoomsOccupied(), totalEconomyCustomers));
		} else {
			var economyCustomersOccupied = getSubListCustomers(economyCustomers, totalEconomyCustomers - economyRooms,
					totalEconomyCustomers);
			occupancyDataStore.setEconomyUsage(
					calculateSum(occupancyDataStore.getEconomyUsage(), calculateSum(economyCustomersOccupied)));
			occupancyDataStore.setEconomyRoomsOccupied(
					calculateSum(occupancyDataStore.getEconomyRoomsOccupied(), economyCustomersOccupied.size()));
		}
	}

	/**
	 * This method is to calculate the sum of two values
	 * 
	 * @param initialData
	 * @param updatedData
	 * @return sum
	 */
	private int calculateSum(int initialData, int updatedData) {
		return initialData + updatedData;
	}

	/**
	 * This method is to build the sublist of the customers
	 * 
	 * @param customers
	 * @param startIndex
	 * @param lastIndex
	 * @return sublist
	 */
	private List<Integer> getSubListCustomers(List<Integer> customers, int startIndex, int lastIndex) {
		return customers.stream().skip(startIndex).limit(lastIndex).collect(toList());
	}

	/**
	 * This method will create two list of customers based on the predicate
	 * 
	 * @param customers
	 * @param condition
	 * @return list of customers
	 */
	private <T> List<List<T>> partitionCustomersByMinimumPremium(List<T> customers, Predicate<T> condition) {
		return customers.stream().collect(partitioningBy(s -> (condition.test(s)))).values().stream()
				.collect(Collectors.toList());
	}
}
