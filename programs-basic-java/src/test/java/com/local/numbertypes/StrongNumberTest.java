package com.local.numbertypes;

import static com.local.numbertypes.NumberType.STRONG_NUMBER;

import java.util.List;

import org.junit.jupiter.api.Test;

class StrongNumberTest extends AbstractNumberTest {

	@Test
	void testStrongNumbers() {
		testNumbersTypeByFile(STRONG_NUMBER, "strongnumbers.txt");
	}

	@Test
	void testStrongNumbersByList() {
		// Pass the order as 5
		testNumbersTypeByList(STRONG_NUMBER, List.of(1, 2, 145), 3);
	}
}
