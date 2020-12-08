package com.local.numbertypes;

import static com.local.numbertypes.NumberType.TECH_NUMBER;

import java.util.List;

import org.junit.jupiter.api.Test;

class TechNumberTest extends AbstractNumberTest {

	@Test
	void testTechNumbers() {
		testNumbersTypeByFile(TECH_NUMBER, "technumbers.txt");
	}

	@Test
	void testTechNumbersByList() {
		// Pass the order as 5
		testNumbersTypeByList(TECH_NUMBER, List.of(81,2025,3025), 3);
	}
}
