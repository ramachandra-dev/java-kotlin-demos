package com.local.numbertypes;

import static com.local.numbertypes.NumberType.NEON_NUMBER;

import java.util.List;

import org.junit.jupiter.api.Test;

class NeonNumberTest extends AbstractNumberTest {

	@Test
	void testNeonNumbers() {
		testNumbersTypeByFile(NEON_NUMBER, "neonnumbers.txt");
	}

	@Test
	void testNeonNumbersByList() {
		// Pass the order as 5
		testNumbersTypeByList(NEON_NUMBER, List.of(1,9), 2);
	}
}
