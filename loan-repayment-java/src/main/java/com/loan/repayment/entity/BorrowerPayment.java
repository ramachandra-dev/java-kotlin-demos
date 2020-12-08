package com.loan.repayment.entity;

import java.time.ZonedDateTime;

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
public class BorrowerPayment {

    /**
     * The annuity payment amount.
     */
    @ApiModelProperty(notes = "The annuity payment amount", example = "0.48")
    private String borrowerPaymentAmount;

    /**
     * The loan payment date.
     */
    @ApiModelProperty(notes = "The loan payment date")
    private ZonedDateTime date;

    /**
     * Initial Outstanding Principal pending for the current month.
     */
    @ApiModelProperty(notes = "Initial Outstanding Principal pending " + "for the current month", example = "100.0")
    private String initialOutstandingPrincipal;

    /**
     * Interest for the month.
     */
    @ApiModelProperty(notes = "Interest for the month", example = "0.42")
    private String interest;

    /**
     * Principal for the month for the month.
     */
    @ApiModelProperty(notes = "Principal for the month", example = "0.06")
    private String principal;

    /**
     * Outstanding Principal after the interset paid for the month.
     */
    @ApiModelProperty(notes = "Outstanding Principal after the interset " + "paid for the month", example = "99.94")
    private String remainingOutstandingPrincipal;

}
