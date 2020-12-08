package com.loan.repayment.entity;

import java.util.List;

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
public class RepaymentPlanResponse {

    /**
     * 
     * Borrower Payments.
     */
    @ApiModelProperty(notes = "Borrower Payments")
    private List<BorrowerPayment> borrowerPayments;

}
