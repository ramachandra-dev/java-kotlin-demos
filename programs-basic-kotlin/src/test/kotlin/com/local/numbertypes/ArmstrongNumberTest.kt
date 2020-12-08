package com.local.numbertypes
import org.junit.jupiter.api.Test
class ArmstrongNumberTest : AbstractNumberTest() {
	@Test
	fun testArmstrongNumbers() {
		testNumbersTypeByFile(com.local.numbertypes.NumberType.ARM_STRONG_NUMBER, "armstrongnumbers.txt")
	}

	@Test
	fun testArmstrongNumbersByList() {
		// Pass the order as 5
		testNumbersTypeByList(com.local.numbertypes.NumberType.ARM_STRONG_NUMBER, java.util.List.of<Int>(1, 4150, 4151), 3)
	}
}