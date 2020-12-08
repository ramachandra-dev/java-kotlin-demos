package com.local.numbertypes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DuckNumber implements INumberTypeOperation {
	// A number that has at least one 0 ( but not at the beginning of the number )
	// 10,20,30,40,50,60,70,80

	@Override
	public boolean execute(long input, long order) {
		for (; input != 0; input /= 10) {
			if (input % 10 == 0) {
				log.debug("An Duck Number {}", input);
				return true;
			}
		}
		log.debug("Invalid Duck Number {}", input);
		return false;
	}

	protected boolean duckNumberViaString(long input) {
		var numberValue = String.valueOf(input);
		if (numberValue.charAt(0) == '0') {
			return false;
		}
		return numberValue.chars()
				.mapToObj(i -> (char) i)
				.skip(1)
				.anyMatch(charValue -> charValue == '0');

	}
}