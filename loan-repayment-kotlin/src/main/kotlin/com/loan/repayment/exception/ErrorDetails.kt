package com.loan.repayment.exception

import io.swagger.annotations.ApiModelProperty
import java.time.ZonedDateTime

/**
 *
 * @author Rama chandra
 */
data class ErrorDetails(
        @ApiModelProperty(notes = "Current time the error occurred") val date : ZonedDateTime ,
        /**
         * Stack trace of the error.
         */
        @ApiModelProperty(notes = "Stack trace of the error" , example = "Nominal rate should not be less "
                + "than zero or zero for Generating the Repayment Schedule") val message : String ,
        /**
         * The API Caused the error.
         */
        @ApiModelProperty(notes = "The API Caused the error" , example = "http://localhost:8080/generate-plan") private val details : String ,
)