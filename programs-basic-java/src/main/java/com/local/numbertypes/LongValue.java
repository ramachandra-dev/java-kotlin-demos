package com.local.numbertypes;

import static java.lang.Long.valueOf;

import java.util.function.Predicate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LongValue implements Predicate<String> {

	@Override
	public boolean test(String line) {
		try {
			valueOf(line);
		} catch (NumberFormatException exception) {
			log.debug("Long number invalid: {}", line);
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
}
