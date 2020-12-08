package com.local.numbertypes

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DisariumNumberTest : AbstractNumberTest() {
	@Test
	fun testDisariumNumbers() {
		testNumbersTypeByFile(com.local.numbertypes.NumberType.DISARIUM_NUMBER, "disariumnumbers.txt")
	}

	@Test
	fun testDisariumNumbersByList() {
		// Pass the order as 5
		testNumbersTypeByList(com.local.numbertypes.NumberType.DISARIUM_NUMBER, java.util.List.of<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 89, 135, 175, 518, 598, 1306, 1676, 2427), 17)
	}
}