package com.local.numbertypes;

import static com.local.numbertypes.NumberType.PERFECT_NUMBER;

import java.util.List;

import org.junit.jupiter.api.Test;

class PerfectNumberTest extends AbstractNumberTest {

	@Test
	void testPerfectNumbers() {
		testNumbersTypeByFile(PERFECT_NUMBER, "perfectnumbers.txt");
	}

	@Test
	void testPerfectNumbersByList() {
		// Pass the order as 5
		testNumbersTypeByList(PERFECT_NUMBER, List.of(1,6,28,496), 4);
	}
}
