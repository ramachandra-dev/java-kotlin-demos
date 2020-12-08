package com.local.numbertypes;

import static com.local.numbertypes.NumberType.MULTI_NIVEN_NUMBER;

import java.util.List;

import org.junit.jupiter.api.Test;

class MultiNivenNumberTest extends AbstractNumberTest {

	@Test
	void testMultiNivenNumbers() {
		testNumbersTypeByFile(MULTI_NIVEN_NUMBER, "multinivennumbers.txt");
	}

	@Test
	void testMultiNivenNumbersByList() {
		// Pass the order as 5
		testNumbersTypeByList(MULTI_NIVEN_NUMBER, List.of(1, 4150, 4151), 1);
	}
}
