package com.local.numbertypes;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class NumbersRangeTest {

	@Test
	void testNumberTypesInRanges() {
		// Input Range tested is : 10 - 5000
		//@formatter:off			
		Arrays.stream(NumberType.values()).forEach(enumValue -> {
			List<Long> response = LongStream.rangeClosed(1, 1000)
					.filter(value -> enumValue.getOperation().execute(value, 5))
					.boxed()
					.collect(Collectors.toList());
			//@formatter:on
			log.info("{}: {} \t {}", enumValue.getNumberType(), response.size(),
					response.stream().map(String::valueOf).collect(Collectors.joining(",")));
			assertTrue(response.size() > 0, "Expected atleast one number to be present");
		});
	}
}
