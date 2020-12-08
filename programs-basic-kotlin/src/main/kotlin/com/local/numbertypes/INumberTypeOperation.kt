package com.local.numbertypes

fun interface INumberTypeOperation {
	fun execute(input: Long, order: Long): Boolean
}