package com.local.numbertypes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BuzzNumber implements INumberTypeOperation {
	// A number is called a buzz number if it is divisible by 7 or it ends with 7
	// 7,14,17,21,27,28,35,37,42,47,49,56,57,63,67,70,77,84,87,91,97,98,105,107

	@Override
	public boolean execute(long input, long order) {
		if((input % 10 == 7) || (input % 7 == 0)){
			log.debug("An Buzz Number {}", input);
			return true;
		}
		log.debug("Not an Buzz Number {}", input);
		return false;
	}
}