package com.loan.repayment.factory;

import com.loan.repayment.exception.ApplicationException;

/**
 * 
 * @author Ramachandra
 *
 */
public class ApplicationExceptionFactory {

    /**
     * This method creates application exception for Blank or Empty Loan Amount
     * 
     * @return ApplicationException
     */
    public static ApplicationException createApplicationExceptionWithBlankLoanAmount() {
        return new ApplicationException("Loan Amount should not be blank to for Generating the Repayment Schedule");
    }

    /**
     * This method creates application exception for Loan Amount null
     * 
     * @return ApplicationException
     */
    public static ApplicationException createApplicationExceptionWithNullLoanAmount() {
        return new ApplicationException("Loan Amount should not be blank to for Generating the Repayment Schedule");
    }

    /**
     * This method creates application exception for Zero loan amount
     * 
     * @return ApplicationException
     */
    public static ApplicationException createApplicationExceptionWithZeroLoanAmount() {
        return new ApplicationException(
                "Loan Amount should not be less than zero or zero for Generating the Repayment Schedule");
    }

    /**
     * This method creates application exception for Invalid value for Double amount
     * 
     * @return ApplicationException
     */
    public static ApplicationException createApplicationExceptionWithInvalidDoubleLoanAmount() {
        return new ApplicationException(
                "Loan Amount Zero not a valid double value to for Generating the Repayment Schedule");
    }

    /**
     * This method creates application exception for Nominal Rate null
     * 
     * @return ApplicationException
     */
    public static ApplicationException createApplicationExceptionWithNullNominalRate() {
        return new ApplicationException("Nominal rate should not be blank to for Generating the Repayment Schedule");
    }

    /**
     * This method creates application exception for Blank Nominal Rate
     * 
     * @return ApplicationException
     */
    public static ApplicationException createApplicationExceptionWithBlankNominalRate() {
        return new ApplicationException("Nominal rate should not be blank to for Generating the Repayment Schedule");
    }

    /**
     * This method creates application exception for Zero Nominal Rate
     * 
     * @return ApplicationException
     */
    public static ApplicationException createApplicationExceptionWithZeroNominalRate() {
        return new ApplicationException(
                "Nominal rate should not be less than zero or zero for Generating the Repayment Schedule");
    }

    /**
     * This method creates application exception for Invalid Double value for
     * Nominal Rate
     * 
     * @return ApplicationException
     */
    public static ApplicationException createApplicationExceptionWithInvalidDoubleNominalRate() {
        return new ApplicationException(
                "Nominal rate Zero not a valid double value to for Generating the Repayment Schedule");
    }

    /**
     * This method creates application exception for Invalid Tenure for loan
     * Scheduled
     * 
     * @return ApplicationException
     */
    public static ApplicationException createApplicationExceptionWithInValidTenureWithLoanSchedule() {
        return new ApplicationException(
                "In the duration 306 months the Repayment Schedule cannot be completed, Please provide the correct duration");
    }

    /**
     * This method creates application exception for Zero Months Duration
     * 
     * @return ApplicationException
     */
    public static ApplicationException createApplicationExceptionWithZeroMonthsDuration() {
        return new ApplicationException("Duration of the Repayment Schedule should be minimum 1");
    }
}
