package com.local.numbertypes
import org.junit.jupiter.api.Test
class PalindromeNumberTest : AbstractNumberTest() {
	@Test
	fun testPalindromeNumbers() {
		testNumbersTypeByFile(com.local.numbertypes.NumberType.PALINDROME_NUMBER, "palindromenumbers.txt")
	}

	@Test
	fun testPalindromeNumbersByList() {
		// Pass the order as 5
		testNumbersTypeByList(com.local.numbertypes.NumberType.PALINDROME_NUMBER, java.util.List.of<Int>(55, 66, 777), 3)
	}
}