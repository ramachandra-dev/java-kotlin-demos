package com.loan.repayment.factory

import com.loan.repayment.entity.RepaymentPlanRequest
import java.time.ZonedDateTime

/**
 *
 * @author Rama chandra
 */
object RepaymentPlanRequestFactory {
    /**
     * This method creates the RepaymentPlanRequest for all valid values
     *
     * @return RepaymentPlanRequest
     */
    fun createRepaymentPlanRequest() : RepaymentPlanRequest {
        return buildAnnuityRequest(24 , "5000" , "5")
    }

    /**
     *
     * @param duration    Duration of the loan
     * @param loanAmount  Loan Amount
     * @param nominalRate Nominal Rate
     * @return RepaymentPlanRequest
     */
    private fun buildAnnuityRequest(duration : Int , loanAmount : String , nominalRate : String) : RepaymentPlanRequest {
        //@formatter:off
        return RepaymentPlanRequest(
                loanAmount , nominalRate , duration , ZonedDateTime.now())
        //@formatter:on
    }

    /**
     * This method is to create RepaymentPlanRequest with Blank Loan Amount
     *
     * @return RepaymentPlanRequest
     */
    fun createRepaymentPlanRequestWithBlankLoanAmount() : RepaymentPlanRequest {
        return buildAnnuityRequest(24 , "" , "5")
    }

    /**
     * This method is to create RepaymentPlanRequest with Zero Loan Amount
     *
     * @return RepaymentPlanRequest
     */
    fun createRepaymentPlanRequestWithZeroLoanAmount() : RepaymentPlanRequest {
        return buildAnnuityRequest(24 , "0" , "5")
    }

    /**
     * This method is to create RepaymentPlanRequest with Invalid Double amount
     *
     * @return RepaymentPlanRequest
     */
    fun createRepaymentPlanRequestWithInvalidDoubleLoanAmount() : RepaymentPlanRequest {
        return buildAnnuityRequest(24 , "Zero" , "0")
    }

    /**
     * This method is to create RepaymentPlanRequest with Zero Nominal Rate
     *
     * @return RepaymentPlanRequest
     */
    fun createRepaymentPlanRequestWithZeroNominalRate() : RepaymentPlanRequest {
        return buildAnnuityRequest(24 , "5000" , "0")
    }

    /**
     * This method is to create RepaymentPlanRequest with Blank Nominal Rate
     *
     * @return RepaymentPlanRequest
     */
    fun createRepaymentPlanRequestWithBlankNominalRate() : RepaymentPlanRequest {
        return buildAnnuityRequest(24 , "5000" , "")
    }

    /**
     * This method is to create RepaymentPlanRequest with Nominal Rate null
     *
     * @return RepaymentPlanRequest
     */
    fun createRepaymentPlanRequestWithNullNominalRate() : RepaymentPlanRequest {
        return buildAnnuityRequest(24 , "5000" , "")
    }

    /**
     * This method is to create RepaymentPlanRequest With Invalid Double Nominal
     * Rate
     *
     * @return RepaymentPlanRequest
     */
    fun createRepaymentPlanRequestWithInvalidDoubleNominalRate() : RepaymentPlanRequest {
        return buildAnnuityRequest(24 , "5000" , "Zero")
    }

    /**
     * This method is to create RepaymentPlanRequest with Zero month Duration
     *
     * @return RepaymentPlanRequest
     */
    fun createRepaymentPlanRequestWithZeroMonthsDuration() : RepaymentPlanRequest {
        return buildAnnuityRequest(0 , "5000" , "10")
    }

    /**
     * This method is to create RepaymentPlanRequest with One Month Duration for the
     * repayment
     *
     * @return RepaymentPlanRequest
     */
    fun createRepaymentPlanRequestWithOneMonthDuration() : RepaymentPlanRequest {
        return buildAnnuityRequest(1 , "89000" , "75")
    }

    /**
     * This method is to create RepaymentPlanRequest with Invalid Tenure for the
     * Given Loan Amount
     *
     * @return RepaymentPlanRequest
     */
    fun createRepaymentPlanRequestWithInValidTenureWithLoanSchedule() : RepaymentPlanRequest {
        return buildAnnuityRequest(306 , "5000" , "5")
    }
}