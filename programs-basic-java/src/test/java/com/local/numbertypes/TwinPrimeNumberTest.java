package com.local.numbertypes;

import static com.local.numbertypes.NumberType.TWIN_PRIME_NUMBER;

import java.util.List;

import org.junit.jupiter.api.Test;

class TwinPrimeNumberTest extends AbstractNumberTest {

	@Test
	void testTwinPrimeNumbers() {
		testNumbersTypeByFile(TWIN_PRIME_NUMBER, "twinprimenumbers.txt");
	}

	@Test
	void testTwinPrimeNumbersByList() {
		// Pass the order as 5
		testNumbersTypeByList(TWIN_PRIME_NUMBER, List.of(1,3,5,7), 2);
	}
}
