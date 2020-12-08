package com.loan.repayment.entity;

import java.time.ZonedDateTime;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Ramachandra
 *
 */
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@Validated
public class RepaymentPlanRequest {

    /**
     * The loan amount.
     */
    @NotNull
    @ApiModelProperty(notes = "The loan amount", example = "5000", required = true)
    private String loanAmount;

    /**
     * The nominal rate of interest for year.
     */
    @NotNull
    @ApiModelProperty(notes = "The nominal rate of interest " + "for year", example = "5", required = true)
    private String nominalRate;

    /**
     * The loan repayment duration.
     */
    @NotNull
    @ApiModelProperty(notes = "The loan repayment" + " duration", example = "24", required = true)
    private int duration;

    /**
     * The loan payment start date.
     */
    @NotNull
    @ApiModelProperty(notes = "The loan payment start date", required = true)
    private ZonedDateTime startDate;

}