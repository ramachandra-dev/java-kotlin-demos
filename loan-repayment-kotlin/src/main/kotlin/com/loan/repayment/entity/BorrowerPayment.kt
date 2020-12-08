package com.loan.repayment.entity

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModelProperty
import java.time.ZonedDateTime

/**
 *
 * @author Rama chandra
 */
data class BorrowerPayment(
        @ApiModelProperty(notes = "The annuity payment amount" , example = "0.48")
        @JsonProperty("borrowerPaymentAmount")
        val borrowerPaymentAmount : String ,
        @ApiModelProperty(notes = "The loan payment date")
        @JsonProperty("date")
        val date : ZonedDateTime ,
        @ApiModelProperty(notes = "Initial Outstanding Principal pending " + "for the current month" , example = "100.0")
        @JsonProperty("initialOutstandingPrincipal")
        val initialOutstandingPrincipal : String ,
        @ApiModelProperty(notes = "Interest for the month" , example = "0.42")
        @JsonProperty("interest")
        val interest : String ,
        @ApiModelProperty(notes = "Principal for the month" , example = "0.06")
        @JsonProperty("principal")
        val principal : String ,
        @ApiModelProperty(notes = "Outstanding Principal after the interest " + "paid for the month" , example = "99.94")
        @JsonProperty("remainingOutstandingPrincipal")
        val remainingOutstandingPrincipal : String
)