package com.local.numbertypes;

import static com.local.numbertypes.NumberType.DUCK_NUMBER;

import java.util.List;

import org.junit.jupiter.api.Test;

class DuckNumberTest extends AbstractNumberTest {

	@Test
	void testDuckNumbers() {
		testNumbersTypeByFile(DUCK_NUMBER, "ducknumbers.txt");
	}

	@Test
	void testDuckNumbersByList() {
		// Pass the order as 5
		testNumbersTypeByList(DUCK_NUMBER, List.of(10, 20, 30, 40, 50, 60, 70, 80, 01111111), 8);
	}
}
