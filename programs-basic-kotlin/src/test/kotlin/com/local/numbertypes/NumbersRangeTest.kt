package com.local.numbertypes
import mu.KotlinLogging
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.function.Function
import java.util.stream.Collectors
import java.util.stream.LongStream

private val logger = KotlinLogging.logger {}
class NumbersRangeTest {
	@Test
	fun testNumberTypesInRanges() {
		// Input Range tested is : 10 - 5000
		NumberType.values().forEach { numberType ->
				val response = LongStream.rangeClosed(1 , 1000)
						.filter { value : Long -> numberType.getOperation().execute(value , 5) }
						.boxed()
						.collect(Collectors.toList());
				logger.info("{}: {} \t {}" , numberType.numberType , response.size ,
						response.stream().map<String>(Function<Long , String> { obj : Long -> obj.toString() })
								.collect(Collectors.joining(",")))
				Assertions.assertTrue(response.size > 0 , "Expected atleast one number to be present")

		}
	}
}