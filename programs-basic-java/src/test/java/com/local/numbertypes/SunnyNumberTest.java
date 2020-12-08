package com.local.numbertypes;

import static com.local.numbertypes.NumberType.SUNNY_NUMBER;

import java.util.List;

import org.junit.jupiter.api.Test;

class SunnyNumberTest extends AbstractNumberTest {

	@Test
	void testSunnyNumbers() {
		testNumbersTypeByFile(SUNNY_NUMBER, "sunnynumbers.txt");
	}

	@Test
	void testSunnyNumbersByList() {
		// Pass the order as 5
		testNumbersTypeByList(SUNNY_NUMBER, List.of(3,8,15,24,35,48,63,80,99), 9);
	}
}
