package com.local.numbertypes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NelsonNumber implements INumberTypeOperation {
	// The number 111 is sometimes called a Nelson. Multiples of 111 are called a
	// double Nelson (222), triple Nelson (333)
	// 111,222,333,444,555,666,777

	@Override
	public boolean execute(long input, long order) {
		var status = (input % 111 == 0);
		log(status, input);
		return status;
	}

	private void log(boolean status, long input) {
		if (status) {
			log.debug("An Nelson Number {}", input);
		} else {
			log.debug("Not an Nelson Number {}", input);
		}
	}

}