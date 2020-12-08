package com.loan.repayment.factory;

import java.time.ZonedDateTime;

import com.loan.repayment.entity.RepaymentPlanRequest;

/**
 * 
 * @author Ramachandra
 *
 */
public class RepaymentPlanRequestFactory {

    /**
     * This method creates the RepaymentPlanRequest for all valid values
     * 
     * @return RepaymentPlanRequest
     */
    public static RepaymentPlanRequest createRepaymentPlanRequest() {
        return buildAnnuityRequest(24, "5000", "5");
    }

    /**
     * 
     * @param duration    Duration of the loan
     * @param loanAmount  Loan Amount
     * @param nominalRate Nominal Rate
     * @return RepaymentPlanRequest
     */
    private static RepaymentPlanRequest buildAnnuityRequest(int duration, String loanAmount, String nominalRate) {
        //@formatter:off
		return RepaymentPlanRequest
				.builder()
				.duration(duration)
				.loanAmount(loanAmount)
				.nominalRate(nominalRate)
				.startDate(ZonedDateTime.now()).build();
		//@formatter:on
    }

    /**
     * This method is to create RepaymentPlanRequest with Blank Loan Amount
     * 
     * @return RepaymentPlanRequest
     */
    public static RepaymentPlanRequest createRepaymentPlanRequestWithBlankLoanAmount() {
        return buildAnnuityRequest(24, "", "5");
    }

    /**
     * This method is to create RepaymentPlanRequest with Null Loan Amount
     * 
     * @return RepaymentPlanRequest
     */
    public static RepaymentPlanRequest createRepaymentPlanRequestWithNullLoanAmount() {
        return buildAnnuityRequest(24, null, "5");
    }

    /**
     * This method is to create RepaymentPlanRequest with Zero Loan Amount
     * 
     * @return RepaymentPlanRequest
     */
    public static RepaymentPlanRequest createRepaymentPlanRequestWithZeroLoanAmount() {
        return buildAnnuityRequest(24, "0", "5");
    }

    /**
     * This method is to create RepaymentPlanRequest with Invalid Double amount
     * 
     * @return RepaymentPlanRequest
     */
    public static RepaymentPlanRequest createRepaymentPlanRequestWithInvalidDoubleLoanAmount() {
        return buildAnnuityRequest(24, "Zero", "0");
    }

    /**
     * This method is to create RepaymentPlanRequest with Zero Nominal Rate
     * 
     * @return RepaymentPlanRequest
     */
    public static RepaymentPlanRequest createRepaymentPlanRequestWithZeroNominalRate() {
        return buildAnnuityRequest(24, "5000", "0");
    }

    /**
     * This method is to create RepaymentPlanRequest with Blank Nominal Rate
     * 
     * @return RepaymentPlanRequest
     */
    public static RepaymentPlanRequest createRepaymentPlanRequestWithBlankNominalRate() {
        return buildAnnuityRequest(24, "5000", "");
    }

    /**
     * This method is to create RepaymentPlanRequest with Nominal Rate null
     * 
     * @return RepaymentPlanRequest
     */
    public static RepaymentPlanRequest createRepaymentPlanRequestWithNullNominalRate() {
        return buildAnnuityRequest(24, "5000", "");
    }

    /**
     * This method is to create RepaymentPlanRequest With Invalid Double Nominal
     * Rate
     * 
     * @return RepaymentPlanRequest
     */
    public static RepaymentPlanRequest createRepaymentPlanRequestWithInvalidDoubleNominalRate() {
        return buildAnnuityRequest(24, "5000", "Zero");
    }

    /**
     * This method is to create RepaymentPlanRequest with Zero month Duration
     * 
     * @return RepaymentPlanRequest
     */
    public static RepaymentPlanRequest createRepaymentPlanRequestWithZeroMonthsDuration() {
        return buildAnnuityRequest(0, "5000", "10");
    }

    /**
     * This method is to create RepaymentPlanRequest with One Month Duration for the
     * repayment
     * 
     * @return RepaymentPlanRequest
     */
    public static RepaymentPlanRequest createRepaymentPlanRequestWithOneMonthDuration() {
        return buildAnnuityRequest(1, "89000", "75");
    }

    /**
     * This method is to create RepaymentPlanRequest with Invalid Tenure for the
     * Given Loan Amount
     * 
     * @return RepaymentPlanRequest
     */
    public static RepaymentPlanRequest createRepaymentPlanRequestWithInValidTenureWithLoanSchedule() {
        return buildAnnuityRequest(306, "5000", "5");
    }
}
