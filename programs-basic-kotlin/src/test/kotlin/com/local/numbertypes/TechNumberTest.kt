package com.local.numbertypes
import org.junit.jupiter.api.Test
class TechNumberTest : AbstractNumberTest() {
	@Test
	fun testTechNumbers() {
		testNumbersTypeByFile(com.local.numbertypes.NumberType.TECH_NUMBER, "technumbers.txt")
	}

	@Test
	fun testTechNumbersByList() {
		// Pass the order as 5
		testNumbersTypeByList(com.local.numbertypes.NumberType.TECH_NUMBER, java.util.List.of<Int>(81, 2025, 3025), 3)
	}
}