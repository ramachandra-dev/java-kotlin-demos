package com.loan.repayment.factory

import com.loan.repayment.exception.ErrorDetails
import java.time.ZonedDateTime

/***
 *
 * @author Rama chandra
 */
object ErrorDetailsFactory {
	/**
	 * This method creates the error details for Nominal Rate Zero
	 *
	 * @return ErrorDetails
	 */
	fun createErrorDetails() : ErrorDetails {
		//@formatter:off
		return ErrorDetails(ZonedDateTime.now(),"http://localhost:8080/generate-plan",
				"Nominal rate should not be less than zero or zero for Generating the Repayment Schedule")
		//@formatter:on
	}
}