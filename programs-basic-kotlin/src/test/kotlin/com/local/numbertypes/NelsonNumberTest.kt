package com.local.numbertypes
import org.junit.jupiter.api.Test
class NelsonNumberTest : AbstractNumberTest() {
	@Test
	fun testNelsonNumbers() {
		testNumbersTypeByFile(com.local.numbertypes.NumberType.NELSON_NUMBER, "nelsonnumbers.txt")
	}

	@Test
	fun testNelsonNumbersByList() {
		// Pass the order as 5
		testNumbersTypeByList(com.local.numbertypes.NumberType.NELSON_NUMBER, java.util.List.of<Int>(111, 222, 333, 444, 555, 666, 777), 7)
	}
}