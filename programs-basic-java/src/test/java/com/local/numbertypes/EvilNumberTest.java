package com.local.numbertypes;

import static com.local.numbertypes.NumberType.EVIL_NUMBER;

import java.util.List;

import org.junit.jupiter.api.Test;

class EvilNumberTest extends AbstractNumberTest {

	@Test
	void testEvilNumbers() {
		testNumbersTypeByFile(EVIL_NUMBER, "evilnumbers.txt");
	}

	@Test
	void testEvilNumbersByList() {
		// Pass the order as 5
		testNumbersTypeByList(EVIL_NUMBER, List.of(3,5,6,9,10,12,15,17,18), 9);
	}
}
