package com.local.numbertypes;

import static com.local.numbertypes.NumberType.PRIME_NUMBER;

import java.util.List;

import org.junit.jupiter.api.Test;

class PrimeNumberTest extends AbstractNumberTest {

	@Test
	void testPrimeNumbers() {
		testNumbersTypeByFile(PRIME_NUMBER, "primenumbers.txt");
	}

	@Test
	void testPrimeNumbersByList() {
		// Pass the order as 5
		testNumbersTypeByList(PRIME_NUMBER, List.of(1, 3, 5, 7, 89, 999, 12313), 4);
	}
}
