package com.local.numbertypes
import org.junit.jupiter.api.Test
class MagicNumberTest : AbstractNumberTest() {
	@Test
	fun testMagicNumbers() {
		testNumbersTypeByFile(com.local.numbertypes.NumberType.MAGIC_NUMBER, "magicnumbers.txt")
	}

	@Test
	fun testMagicNumbersByList() {
		// Pass the order as 5
		testNumbersTypeByList(com.local.numbertypes.NumberType.MAGIC_NUMBER, java.util.List.of<Int>(1, 10, 19, 28, 37, 46, 55, 64, 73, 82, 91), 11)
	}
}