package com.local.numbertypes;

import static com.local.numbertypes.NumberType.ARM_STRONG_NUMBER;

import java.util.List;

import org.junit.jupiter.api.Test;

class ArmstrongNumberTest extends AbstractNumberTest {

	@Test
	void testArmstrongNumbers() {
		testNumbersTypeByFile(ARM_STRONG_NUMBER, "armstrongnumbers.txt");
	}

	@Test
	void testArmstrongNumbersByList() {
		// Pass the order as 5
		testNumbersTypeByList(ARM_STRONG_NUMBER, List.of(1, 4150, 4151), 3);
	}
}
