package com.local.numbertypes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractNumberTest {

	private LongValue stringValidator = new LongValue();

	void testNumbersTypeByFile(NumberType numberType, String fileName) {
		try (Stream<String> inputNumbers = Files.lines(Paths.get("src/test/resources/" + fileName))) {
			//@formatter:off
			long totalValidNumbers = inputNumbers
					.filter(value -> stringValidator.test(value))
					.mapToLong(Long::valueOf)
					.boxed()
					.map(value -> numberType.getOperation()
							.execute(value, 5))
					.collect(Collectors.toList()).stream().filter(value -> value == true).count();
			assertTrue(totalValidNumbers > 0, "Expected the size of the elements is ");
			//@formatter:on
		} catch (IOException e) {
			log.info("Error occured reading the file");
		}
	}

	void testNumbersTypeByConsoleInput(NumberType numberType) {
		try (Scanner scan = new Scanner(new File("src/test/resources/armstrongnumber.txt"))) {
			//@formatter:off
			log.info("Enter the order of the number: ");
			var order = Long.valueOf(scan.nextLine());
			log.info("#########   PRESS ENTER ONCE TO STOP ENTERING THE NUMBERS ##################");
			log.info("Enter NumberType:");
			var inputNumbers = Stream.iterate(scan.nextLine(), line -> !line.isEmpty(), line -> scan.nextLine())
					.filter(value -> stringValidator.test(value))
					.mapToLong(Long::valueOf).boxed().collect(Collectors.toList());
			inputNumbers.stream().forEach(value -> numberType.getOperation().execute(value, order));
			//@formatter:on
		} catch (FileNotFoundException e) {
			log.info("Error occured reading the file");
		}
	}

	void testNumbersTypeByList(NumberType numberType, List<Integer> inputNumbers, int expectedSize) {
		// Pass the order as 5
		List<Long> response = inputNumbers.stream().map(Long::valueOf)
				.filter(value -> numberType.getOperation().execute(value, 5)).collect(Collectors.toList());
		assertEquals(expectedSize, response.size(), "Expected the size of the elements is " + expectedSize);
	}
}
