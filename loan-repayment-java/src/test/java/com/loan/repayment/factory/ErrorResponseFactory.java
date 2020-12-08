package com.loan.repayment.factory;

import com.loan.repayment.exception.ErrorResponse;

/**
 * 
 * @author Ramachandra
 *
 */
public class ErrorResponseFactory {

    /**
     * This method creates the error details for Nominal Rate Zero
     * 
     * @return ErrorResponse
     */
    public static ErrorResponse createErrorResponse() {
        //@formatter:off
		return ErrorResponse
				.builder()
				.errorDetails(ErrorDetailsFactory.createErrorDetails())
				.build();
		//@formatter:on
    }
}
