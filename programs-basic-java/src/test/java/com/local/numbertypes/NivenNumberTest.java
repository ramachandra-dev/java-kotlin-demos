package com.local.numbertypes;

import static com.local.numbertypes.NumberType.NIVEN_NUMBER;

import java.util.List;

import org.junit.jupiter.api.Test;

class NivenNumberTest extends AbstractNumberTest {

	@Test
	void testNivenNumbers() {
		testNumbersTypeByFile(NIVEN_NUMBER, "nivennumbers.txt");
	}

	@Test
	void testNivenNumbersByList() {
		// Pass the order as 5
		testNumbersTypeByList(NIVEN_NUMBER, List.of(1,2,3,4,5,6,7,8,9,10,12,18,20,21,24,27,30), 17);
	}
}
