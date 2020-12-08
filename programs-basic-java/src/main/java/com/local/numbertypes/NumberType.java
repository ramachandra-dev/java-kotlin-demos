package com.local.numbertypes;

public enum NumberType {

	ARM_STRONG_NUMBER("ARM_STRONG_NUMBER", new ArmstrongNumber()), //
	AUTO_MORPHIC_NUMBER("AUTO_MORPHIC_NUMBER", new AutomorphicNumber()), //
	BUZZ_NUMBER("BUZZ_NUMBER", new BuzzNumber()), //
	DISARIUM_NUMBER("DISARIUM_NUMBER", new DisariumNumber()), //
	DUCK_NUMBER("DUCK_NUMBER", new DuckNumber()), //
	EVIL_NUMBER("EVIL_NUMBER", new EvilNumber()), //
	NIVEN_NUMBER("NIVEN_NUMBER", new NivenNumber()), //
	MULTI_NIVEN_NUMBER("MULTI_NIVEN_NUMBER", new MultiNivenNumber()), //
	MAGIC_NUMBER("MAGIC_NUMBER", new MagicNumber()), //
	NELSON_NUMBER("NELSON_NUMBER", new NelsonNumber()), //
	NEON_NUMBER("NEON_NUMBER", new NeonNumber()), //
	PALINDROME_NUMBER("PALINDROME_NUMBER", new PalindromeNumber()), //
	PERFECT_NUMBER("PERFECT_NUMBER", new PerfectNumber()), //
	PRIME_NUMBER("PRIME_NUMBER", new PrimeNumber()), //
	PRONIC_NUMBER("PRONIC_NUMBER", new PronicNumber()), //
	SPY_NUMBER("SPY_NUMBER", new SpyNumber()), //
	STRONG_NUMBER("STRONG_NUMBER", new StrongNumber()), //
	SUNNY_NUMBER("SUNNY_NUMBER", new SunnyNumber()), //
	TECH_NUMBER("TECH_NUMBER", new TechNumber()), //
	TWIN_PRIME_NUMBER("TWIN_PRIME_NUMBER", new TwinPrimeNumber());

	private final String type;
	private final INumberTypeOperation operation;

	NumberType(String type, INumberTypeOperation operation) {
		this.type = type;
		this.operation = operation;
	}

	public INumberTypeOperation getOperation() {
		return operation;
	}

	public String getNumberType() {
		return type;
	}

}
