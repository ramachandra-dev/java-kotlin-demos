package com.loan.repayment.entity

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModelProperty

/**
 *
 * @author Rama chandra
 */
data class RepaymentPlanResponse(
        @ApiModelProperty(notes = "Borrower Payments")
        @JsonProperty("borrowerPayments")
        var borrowerPayments: List<BorrowerPayment>
)