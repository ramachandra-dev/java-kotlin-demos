package com.local.numbertypes;

import static com.local.numbertypes.NumberType.ARM_STRONG_NUMBER;
import static com.local.numbertypes.NumberType.AUTO_MORPHIC_NUMBER;

import java.util.List;

import org.junit.jupiter.api.Test;

class AutomorphicNumberTest extends AbstractNumberTest {

	@Test
	void testAutomorphicNumbers() {
		testNumbersTypeByFile(ARM_STRONG_NUMBER, "automorphicnumbers.txt");
	}

	@Test
	void testAutomorphicNumbersByList() {
		// Pass the order as 5
		testNumbersTypeByList(AUTO_MORPHIC_NUMBER, List.of(1, 5, 6, 25, 76, 376, 625), 7);
	}
}
