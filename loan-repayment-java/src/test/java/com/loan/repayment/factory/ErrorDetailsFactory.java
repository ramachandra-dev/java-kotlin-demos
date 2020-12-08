package com.loan.repayment.factory;

import java.time.ZonedDateTime;

import com.loan.repayment.exception.ErrorDetails;

/***
 * 
 * @author Ramachandra
 *
 */
public class ErrorDetailsFactory {

    /**
     * This method creates the error details for Nominal Rate Zero
     * 
     * @return ErrorDetails
     */
    public static ErrorDetails createErrorDetails() {
        //@formatter:off
		return ErrorDetails
				.builder()
				.date(ZonedDateTime.now())
				.details("http://localhost:8080/generate-plan")
				.message("Nominal rate should not be less than zero or zero for Generating the Repayment Schedule")
				.build();
		//@formatter:on
    }
}
