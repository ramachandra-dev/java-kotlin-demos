package com.local.numbertypes

enum class NumberType(val numberType: String , operation: INumberTypeOperation) {
	ARM_STRONG_NUMBER("ARM_STRONG_NUMBER", ArmstrongNumber()),  //
	AUTO_MORPHIC_NUMBER("AUTO_MORPHIC_NUMBER", AutomorphicNumber()),  //
	BUZZ_NUMBER("BUZZ_NUMBER", BuzzNumber()),  //
	DISARIUM_NUMBER("DISARIUM_NUMBER", DisariumNumber()),  //
	DUCK_NUMBER("DUCK_NUMBER", DuckNumber()),  //
	EVIL_NUMBER("EVIL_NUMBER", EvilNumber()),  //
	NIVEN_NUMBER("HARSHAD_NUMBER", NivenNumber()),  //
	MULTI_NIVEN_NUMBER("MULTI_HARSHAD_NUMBER", MultiNivenNumber()),  //
	MAGIC_NUMBER("MAGIC_NUMBER", MagicNumber()),  //
	NELSON_NUMBER("NELSON_NUMBER", NelsonNumber()),  //
	NEON_NUMBER("NEON_NUMBER", NeonNumber()),  //
	PALINDROME_NUMBER("PALINDROME_NUMBER", PalindromeNumber()),  //
	PERFECT_NUMBER("PERFECT_NUMBER", PerfectNumber()),  //
	PRIME_NUMBER("PRIME_NUMBER", PrimeNumber()),  //
	PRONIC_NUMBER("PRONIC_NUMBER", PronicNumber()),  //
	SPY_NUMBER("SPY_NUMBER", SpyNumber()),  //
	STRONG_NUMBER("STRONG_NUMBER", StrongNumber()),  //
	SUNNY_NUMBER("SUNNY_NUMBER", SunnyNumber()),  //
	TECH_NUMBER("TECH_NUMBER", TechNumber()),  //
	TWIN_PRIME_NUMBER("TWIN_PRIME_NUMBER", TwinPrimeNumber());

	private val operation: INumberTypeOperation = operation
	fun getOperation(): INumberTypeOperation {
		return operation
	}

}