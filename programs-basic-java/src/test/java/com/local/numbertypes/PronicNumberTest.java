package com.local.numbertypes;

import static com.local.numbertypes.NumberType.PRONIC_NUMBER;

import java.util.List;

import org.junit.jupiter.api.Test;

class PronicNumberTest extends AbstractNumberTest {

	@Test
	void testPronicNumbers() {
		testNumbersTypeByFile(PRONIC_NUMBER, "pronicnumbers.txt");
	}

	@Test
	void testPronicNumbersByList() {
		// Pass the order as 5
		testNumbersTypeByList(PRONIC_NUMBER, List.of(2,6,12,20,30,42), 6);
	}
}
