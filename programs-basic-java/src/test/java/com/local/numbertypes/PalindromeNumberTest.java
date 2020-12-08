package com.local.numbertypes;

import static com.local.numbertypes.NumberType.PALINDROME_NUMBER;

import java.util.List;

import org.junit.jupiter.api.Test;

class PalindromeNumberTest extends AbstractNumberTest {

	@Test
	void testPalindromeNumbers() {
		testNumbersTypeByFile(PALINDROME_NUMBER, "palindromenumbers.txt");
	}

	@Test
	void testPalindromeNumbersByList() {
		// Pass the order as 5
		testNumbersTypeByList(PALINDROME_NUMBER, List.of(55, 66, 777), 3);
	}
}
