package com.local.numbertypes

import mu.KotlinLogging
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import java.util.function.*
import java.util.stream.Collectors.toList
import java.util.stream.Stream

private val logger = KotlinLogging.logger {}
abstract class AbstractNumberTest {
	private val stringValidator = LongValue()
	fun testNumbersTypeByFile(numberType : com.local.numbertypes.NumberType , fileName : String) {
		try {
			Files.lines(Paths.get("src/test/resources/$fileName")).use { inputNumbers ->
				//@formatter:off
				val totalValidNumbers: Long = inputNumbers
						.filter { stringValidator.test(it)  }
						.mapToLong { it.toLong()  }
						.boxed()
						.map {
							numberType.getOperation()
									.execute(it , 5) }
						.collect(toList())
						.stream()
						.filter(Predicate<Boolean> { it })
						.count()
				assertTrue(totalValidNumbers > 0 , "Expected the size of the elements is ")
			}
		} catch (e : IOException) {
			logger.info("Error occured reading the file")
		}
	}

	fun testNumbersTypeByConsoleInput(numberType : com.local.numbertypes.NumberType) {
		try {
			Scanner(File("src/test/resources/armstrongnumber.txt")).use { scan ->
				//@formatter:off
				logger.info("Enter the order of the number: ")
				val order: Long = scan.nextLine().toLong()
				logger.info("#########   PRESS ENTER ONCE TO STOP ENTERING THE NUMBERS ##################")
				logger.info("Enter NumberType:")
				val inputNumbers: List<Long> = Stream.iterate<String>(scan.nextLine() ,
						{ line : String -> line.isNotEmpty() } ,
						{ scan.nextLine() })
						.filter { stringValidator.test(it) }
						.mapToLong { it.toLong()  }
						.boxed()
						.collect(toList())
				inputNumbers.stream().forEach(Consumer<Long> { value : Long -> numberType.getOperation().execute(value , order) })
			}
		} catch (e : FileNotFoundException) {
			logger.info("Error occurred reading the file")
		}
	}

	fun testNumbersTypeByList(numberType : com.local.numbertypes.NumberType , inputNumbers : List<Int?> , expectedSize : Int) {
		// Pass the order as 5
		val response: MutableList<Long> = inputNumbers
				.stream()
				.map { value: Int? -> value?.toLong() }
				.filter { value : Long? -> numberType
						.getOperation()
						.execute(value!! , 5) }
				.collect(toList()) as MutableList<Long>
		assertEquals(expectedSize , response.size , "Expected the size of the elements is $expectedSize")
	}
}