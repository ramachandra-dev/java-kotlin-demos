package com.local.numbertypes
import org.junit.jupiter.api.Test
class SunnyNumberTest : AbstractNumberTest() {
	@Test
	fun testSunnyNumbers() {
		testNumbersTypeByFile(com.local.numbertypes.NumberType.SUNNY_NUMBER, "sunnynumbers.txt")
	}

	@Test
	fun testSunnyNumbersByList() {
		// Pass the order as 5
		testNumbersTypeByList(com.local.numbertypes.NumberType.SUNNY_NUMBER, java.util.List.of<Int>(3, 8, 15, 24, 35, 48, 63, 80, 99), 9)
	}
}