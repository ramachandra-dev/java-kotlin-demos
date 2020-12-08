package com.local.numbertypes;

import static com.local.numbertypes.NumberType.BUZZ_NUMBER;

import java.util.List;

import org.junit.jupiter.api.Test;

class BuzzNumberTest extends AbstractNumberTest {

	@Test
	void testBuzzNumbers() {
		testNumbersTypeByFile(BUZZ_NUMBER, "buzznumbers.txt");
	}

	@Test
	void testBuzzNumbersByList() {
		// Pass the order as 5
		testNumbersTypeByList(BUZZ_NUMBER, List.of(7, 14, 17, 21, 27, 28, 35, 37, 42, 47, 49, 56, 57, 63, 67, 70, 77,
				84, 87, 91, 97, 98, 105, 107), 24);
	}
}
