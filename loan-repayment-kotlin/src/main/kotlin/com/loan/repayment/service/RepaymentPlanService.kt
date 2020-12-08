package com.loan.repayment.service

import com.loan.repayment.entity.BorrowerPayment
import com.loan.repayment.entity.RepaymentPlanRequest
import com.loan.repayment.entity.RepaymentPlanResponse
import com.loan.repayment.exception.ApplicationException
import org.springframework.stereotype.Service
import org.springframework.util.ObjectUtils
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.ZonedDateTime
import kotlin.math.pow

/**
 *
 * @author Rama chandra
 */

@Service
class RepaymentPlanService : IRepaymentPlanService {
    /**
     * This method calculates the repayment plan.
     *
     * @param repaymentPlanRequest Payload for the repayment of loan
     * @return repaymentPlanResponse
     */
    override fun repaymentPlan(repaymentPlanRequest : RepaymentPlanRequest) : RepaymentPlanResponse {
        validateRequest(repaymentPlanRequest)
        val rate : Double = java.lang.Double.valueOf(repaymentPlanRequest.nominalRate) / 100
        // Getting the monthly rate as yearly rate is the input
        val monthlyRate = rate / 12
        // Duration of the repayments loan period
        val term = repaymentPlanRequest.duration

        // Initial values
        var initialOutstandingPrincipal : Double = java.lang.Double.valueOf(repaymentPlanRequest.loanAmount)
        var remainingOutstandingPrincipal : BigDecimal = BigDecimal.valueOf(initialOutstandingPrincipal)

        // Calculating the Annuity/ Borrower Payment Amount
        var borrowerPaymentAmount : BigDecimal = calculateBorrowerPaymentAmount(BigDecimal.valueOf(initialOutstandingPrincipal) ,
                monthlyRate , term)
        validateBorrowerPaymentAmount(borrowerPaymentAmount)
        // Start date of the loan repayment schedule
        var currentDate = repaymentPlanRequest.startDate
        val borrowerPayments : MutableList<BorrowerPayment> = java.util.ArrayList<BorrowerPayment>()
        for (i in 1..term) {
            // Old Remaining Outstanding Principal becomes current Outstanding
            // Principal
            initialOutstandingPrincipal = remainingOutstandingPrincipal.toDouble()
            // Calculate Interest
            val interest : BigDecimal = calculateInterest(rate , initialOutstandingPrincipal)
            // Calculate Principal
            var principal : BigDecimal = calculatePrincipal(borrowerPaymentAmount , interest)
            // Calculate OutstandingPrincipal
            remainingOutstandingPrincipal = calculateRemainingOutstandingPrincipal(principal ,
                    initialOutstandingPrincipal)
            // If the OutstandingPrincipal is less the Annuity/BorrowerPayment
            // Amount we
            // need to add remainingOutstandingPrincipal to the current annuity
            // and reset
            // the remainingOutstandingPrincipal to zero and update the
            // principal amount
            // with initialOutstandingPrincipal
            if (BigDecimal.valueOf(initialOutstandingPrincipal) < borrowerPaymentAmount) {
                borrowerPaymentAmount = borrowerPaymentAmount.add(remainingOutstandingPrincipal).setScale(2 , RoundingMode.HALF_UP)
                remainingOutstandingPrincipal = BigDecimal.ZERO
                principal = BigDecimal.valueOf(initialOutstandingPrincipal)
            }
            // If there any outstanding principal
            if (i==term && remainingOutstandingPrincipal > BigDecimal.ZERO) {
                throw ApplicationException("In the duration " + term
                        + " months the Repayment Schedule cannot be completed, Please provide the correct duration")
            }
            borrowerPayments.add(buildBorrowerPayment(borrowerPaymentAmount , initialOutstandingPrincipal ,
                    remainingOutstandingPrincipal , interest , principal , currentDate))
            // Increasing the current date to next month
            currentDate = currentDate.plusMonths(1)
        }
        return RepaymentPlanResponse(borrowerPayments)
    }

    /**
     * This method checks whether the borrower payment amount or annuity after
     * calculation is less the zero or not.
     *
     * @param borrowerPaymentAmount
     */
    private fun validateBorrowerPaymentAmount(borrowerPaymentAmount : BigDecimal) {
        if (borrowerPaymentAmount <= BigDecimal.ZERO) {
            throw ApplicationException("Borrower Payment Amount or Calculated Annuity is Zero, "
                    + "Please provide correct data to generate the Repayment Schedule")
        }
    }

    /**
     * This method checks/validates all the input parameters for blank, invalid
     * double values, whether duration is provided as zero or not.
     *
     * @param repaymentPlanRequest
     */
    private fun validateRequest(repaymentPlanRequest : RepaymentPlanRequest) {
        validateData(repaymentPlanRequest.loanAmount , "Loan Amount")
        validateData(repaymentPlanRequest.nominalRate , "Nominal rate")
        if (repaymentPlanRequest.duration.toDouble().compareTo(1.0) < 0) {
            throw ApplicationException("Duration of the Repayment Schedule should be minimum 1")
        }
    }

    /**
     * This method checks/validates all the input parameters for blank, invalid
     * double values.
     *
     * @param value
     * @param message
     */
    private fun validateData(value : String , message : String) {
        if (ObjectUtils.isEmpty(value)) {
            throw ApplicationException("$message should not be blank to for Generating the Repayment Schedule")
        }
        try {
            value.toDouble()
        } catch (exception : NumberFormatException) {
            throw ApplicationException(
                    "$message $value not a valid double value to for Generating the Repayment Schedule")
        }
        if (value.toDouble().compareTo(0.0) <= 0) {
            throw ApplicationException(
                    "$message should not be less than zero or zero for Generating the Repayment Schedule")
        }
    }

    /**
     * This method calculates the interest.
     *
     * @param rate
     * @param initialOutstandingPrincipal
     * @return interest
     */
    private fun calculateInterest(rate : Double , initialOutstandingPrincipal : Double) : BigDecimal {
        return BigDecimal.valueOf(rate * DAYS_IN_MONTH * initialOutstandingPrincipal / DAYS_IN_YEAR).setScale(2 , RoundingMode.HALF_UP)
    }

    /**
     * This method calculates the OutstandingPrincipal.
     *
     * @param principal
     * @param initialOutstandingPrincipal
     * @return OutstandingPrincipal
     */
    private fun calculateRemainingOutstandingPrincipal(
            principal : BigDecimal ,
            initialOutstandingPrincipal : Double ,
    ) : BigDecimal {
        return BigDecimal.valueOf(initialOutstandingPrincipal - principal.toDouble()).setScale(2 , RoundingMode.HALF_UP)
    }

    /**
     * This method calculates the principal.
     *
     * @param borrowerPaymentAmount
     * @param interest
     * @return principal
     */
    private fun calculatePrincipal(borrowerPaymentAmount : BigDecimal , interest : BigDecimal) : BigDecimal {
        return borrowerPaymentAmount.subtract(interest).setScale(2 , RoundingMode.HALF_UP)
    }

    /**
     * This method builds the final BorrowerPayment for each month.
     *
     * @param borrowerPaymentAmount
     * @param initialOutstandingPrincipal
     * @param remainingOutstandingPrincipal
     * @param interest
     * @param principal
     * @param currentDate
     * @return borrowerPayment
     */
    private fun buildBorrowerPayment(
            borrowerPaymentAmount : BigDecimal ,
            initialOutstandingPrincipal : Double , remainingOutstandingPrincipal : BigDecimal ,
            interest : BigDecimal , principal : BigDecimal , currentDate : ZonedDateTime ,
    ) : BorrowerPayment {
        //@formatter:off
        return BorrowerPayment(borrowerPaymentAmount.toString(),
                currentDate, initialOutstandingPrincipal.toString(),
                interest.toString(), principal.toString(),
                remainingOutstandingPrincipal.toString())
        //@formatter:on
    }

    /**
     * This method is to calculate the Annuity or Borrower Payment Amount.
     *
     * @param loanAmount
     * @param monthlyRate
     * @param term
     * @return borrowerPaymentAmount
     *
     * AnnuityPayment = PV/[(1-(1+r)^-n)/r] P - Payment V - Present Value r
     * - Rate of Period n - Number of Periods
     */
    private fun calculateBorrowerPaymentAmount(
            loanAmount : BigDecimal , monthlyRate : Double ,
            term : Int ,
    ) : BigDecimal {
        //@formatter:off
        return loanAmount
                .multiply(BigDecimal.valueOf(monthlyRate))
                .divide(BigDecimal.ONE.subtract(BigDecimal.valueOf(BigDecimal.valueOf(monthlyRate)
                        .add(BigDecimal.ONE).toDouble().pow(-term.toDouble()))) , RoundingMode.HALF_UP)
                .setScale(2 , RoundingMode.HALF_UP)
        //@formatter:on
    }

    companion object {
        /**
         * This is total no of days in the month.
         */
        private const val DAYS_IN_MONTH = 30

        /**
         * This is total no of days in the year.
         */
        private const val DAYS_IN_YEAR = 360
    }
}