package com.loan.repayment.exception

import io.swagger.annotations.ApiModelProperty

/**
 *
 * @author Rama chandra
 */
data class ErrorResponse(
        @ApiModelProperty(notes = "Details of the error message") val errorDetails : ErrorDetails
)