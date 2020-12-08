package com.local.numbertypes;

import static com.local.numbertypes.NumberType.MAGIC_NUMBER;

import java.util.List;

import org.junit.jupiter.api.Test;

class MagicNumberTest extends AbstractNumberTest {

	@Test
	void testMagicNumbers() {
		testNumbersTypeByFile(MAGIC_NUMBER, "magicnumbers.txt");
	}

	@Test
	void testMagicNumbersByList() {
		// Pass the order as 5
		testNumbersTypeByList(MAGIC_NUMBER, List.of(1,10,19,28,37,46,55,64,73,82,91), 11);
	}
}
