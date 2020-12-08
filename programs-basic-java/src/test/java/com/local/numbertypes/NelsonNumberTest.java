package com.local.numbertypes;

import static com.local.numbertypes.NumberType.NELSON_NUMBER;

import java.util.List;

import org.junit.jupiter.api.Test;

class NelsonNumberTest extends AbstractNumberTest {

	@Test
	void testNelsonNumbers() {
		testNumbersTypeByFile(NELSON_NUMBER, "nelsonnumbers.txt");
	}

	@Test
	void testNelsonNumbersByList() {
		// Pass the order as 5
		testNumbersTypeByList(NELSON_NUMBER, List.of(111,222,333,444,555,666,777), 7);
	}
}
