package com.local.numbertypes;

@FunctionalInterface
public interface INumberTypeOperation {
	boolean execute(long number, long order);
}