package com.loan.repayment.entity

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModelProperty
import java.time.ZonedDateTime

/**
 *
 * @author Rama chandra
 */
data class RepaymentPlanRequest(
        @ApiModelProperty(notes = "The loan amount" , example = "5000" , required = true)
        @JsonProperty("loanAmount")
        var loanAmount : String ,
        @ApiModelProperty(notes = "The nominal rate of interest " + "for year" , example = "5" , required = true)
        @JsonProperty("nominalRate")
        var nominalRate : String ,
        @ApiModelProperty(notes = "The loan repayment" + " duration" , example = "24" , required = true)
        @JsonProperty("duration")
        var duration : Int ,
        @ApiModelProperty(notes = "The loan payment start date" , required = true)
        @JsonProperty("startDate")
        var startDate : ZonedDateTime
)