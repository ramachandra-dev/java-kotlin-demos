package com.local.numbertypes
import org.junit.jupiter.api.Test
class AutomorphicNumberTest : AbstractNumberTest() {
	@Test
	fun testAutomorphicNumbers() {
		testNumbersTypeByFile(com.local.numbertypes.NumberType.ARM_STRONG_NUMBER, "automorphicnumbers.txt")
	}

	@Test
	fun testAutomorphicNumbersByList() {
		// Pass the order as 5
		testNumbersTypeByList(com.local.numbertypes.NumberType.AUTO_MORPHIC_NUMBER, java.util.List.of<Int>(1, 5, 6, 25, 76, 376, 625), 7)
	}
}