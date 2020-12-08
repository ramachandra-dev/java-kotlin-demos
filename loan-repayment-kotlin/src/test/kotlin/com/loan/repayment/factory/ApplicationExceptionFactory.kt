package com.loan.repayment.factory

import com.loan.repayment.exception.ApplicationException

/**
 *
 * @author Rama chandra
 */
object ApplicationExceptionFactory {
    /**
     * This method creates application exception for Blank or Empty Loan Amount
     *
     * @return ApplicationException
     */
    fun createApplicationExceptionWithBlankLoanAmount() : ApplicationException {
        return ApplicationException("Loan Amount should not be blank to for Generating the Repayment Schedule")
    }

    /**
     * This method creates application exception for Loan Amount null
     *
     * @return ApplicationException
     */
    fun createApplicationExceptionWithNullLoanAmount() : ApplicationException {
        return ApplicationException("Loan Amount should not be blank to for Generating the Repayment Schedule")
    }

    /**
     * This method creates application exception for Zero loan amount
     *
     * @return ApplicationException
     */
    fun createApplicationExceptionWithZeroLoanAmount() : ApplicationException {
        return ApplicationException(
                "Loan Amount should not be less than zero or zero for Generating the Repayment Schedule")
    }

    /**
     * This method creates application exception for Invalid value for Double amount
     *
     * @return ApplicationException
     */
    fun createApplicationExceptionWithInvalidDoubleLoanAmount() : ApplicationException {
        return ApplicationException(
                "Loan Amount Zero not a valid double value to for Generating the Repayment Schedule")
    }

    /**
     * This method creates application exception for Nominal Rate null
     *
     * @return ApplicationException
     */
    fun createApplicationExceptionWithNullNominalRate() : ApplicationException {
        return ApplicationException("Nominal rate should not be blank to for Generating the Repayment Schedule")
    }

    /**
     * This method creates application exception for Blank Nominal Rate
     *
     * @return ApplicationException
     */
    fun createApplicationExceptionWithBlankNominalRate() : ApplicationException {
        return ApplicationException("Nominal rate should not be blank to for Generating the Repayment Schedule")
    }

    /**
     * This method creates application exception for Zero Nominal Rate
     *
     * @return ApplicationException
     */
    fun createApplicationExceptionWithZeroNominalRate() : ApplicationException {
        return ApplicationException(
                "Nominal rate should not be less than zero or zero for Generating the Repayment Schedule")
    }

    /**
     * This method creates application exception for Invalid Double value for
     * Nominal Rate
     *
     * @return ApplicationException
     */
    fun createApplicationExceptionWithInvalidDoubleNominalRate() : ApplicationException {
        return ApplicationException(
                "Nominal rate Zero not a valid double value to for Generating the Repayment Schedule")
    }

    /**
     * This method creates application exception for Invalid Tenure for loan
     * Scheduled
     *
     * @return ApplicationException
     */
    fun createApplicationExceptionWithInValidTenureWithLoanSchedule() : ApplicationException {
        return ApplicationException(
                "In the duration 306 months the Repayment Schedule cannot be completed, Please provide the correct duration")
    }

    /**
     * This method creates application exception for Zero Months Duration
     *
     * @return ApplicationException
     */
    fun createApplicationExceptionWithZeroMonthsDuration() : ApplicationException {
        return ApplicationException("Duration of the Repayment Schedule should be minimum 1")
    }
}