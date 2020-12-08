package com.local.numbertypes;

import static com.local.numbertypes.NumberType.DISARIUM_NUMBER;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
class DisariumNumberTest extends AbstractNumberTest {

	@Test
	void testDisariumNumbers() {
		testNumbersTypeByFile(DISARIUM_NUMBER, "disariumnumbers.txt");
	}

	@Test
	void testDisariumNumbersByList() {
		// Pass the order as 5
		testNumbersTypeByList(DISARIUM_NUMBER, List.of(1,2,3,4,5,6,7,8,9,89,135,175,518,598,1306,1676,2427), 17);
	}
}
