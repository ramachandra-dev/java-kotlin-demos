package com.local.numbertypes;

import static com.local.numbertypes.NumberType.SPY_NUMBER;

import java.util.List;

import org.junit.jupiter.api.Test;

class SpyNumberTest extends AbstractNumberTest {

	@Test
	void testSpyNumbers() {
		testNumbersTypeByFile(SPY_NUMBER, "spynumbers.txt");
	}

	@Test
	void testSpyNumbersByList() {
		// Pass the order as 5
		testNumbersTypeByList(SPY_NUMBER, List.of(132,1123,123123), 1);
	}
}
