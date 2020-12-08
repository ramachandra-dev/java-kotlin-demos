package com.loan.repayment.factory

import com.loan.repayment.exception.ErrorResponse

/**
 *
 * @author Rama chandra
 */
object ErrorResponseFactory {
	/**
	 * This method creates the error details for Nominal Rate Zero
	 *
	 * @return ErrorResponse
	 */
	fun createErrorResponse() : ErrorResponse {
		//@formatter:off
		return ErrorResponse(ErrorDetailsFactory.createErrorDetails())
		//@formatter:on
	}
}