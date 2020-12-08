package com.loan.repayment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.loan.repayment.exception.ApplicationException;
import com.loan.repayment.factory.RepaymentPlanRequestFactory;

/**
 * 
 * @author Ramachandra
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class RepaymentPlanServiceIT {

    @Autowired
    private RepaymentPlanService repaymentPlanService;

    @Test
    void testRepaymentPlan() {
        final var banksResponse = repaymentPlanService
                .repaymentPlan(RepaymentPlanRequestFactory.createRepaymentPlanRequest());
        assertEquals(24, banksResponse.getBorrowerPayments().size(), "Expected the payments to be 24");

        var borrowerPayments = banksResponse.getBorrowerPayments();
        var borrowerPayment = borrowerPayments.get(0);

        assertEquals("219.36", borrowerPayment.getBorrowerPaymentAmount(), "Expected to match");
        assertEquals("5000.0", borrowerPayment.getInitialOutstandingPrincipal(), "Expected to match");
        assertEquals("20.83", borrowerPayment.getInterest(), "Expected to match");
        assertEquals("198.53", borrowerPayment.getPrincipal(), "Expected to match");
        assertEquals("4801.47", borrowerPayment.getRemainingOutstandingPrincipal(), "Expected to match");

        borrowerPayment = borrowerPayments.get(1);

        assertEquals("219.36", borrowerPayment.getBorrowerPaymentAmount(), "Expected to match");
        assertEquals("4801.47", borrowerPayment.getInitialOutstandingPrincipal(), "Expected to match");
        assertEquals("20.01", borrowerPayment.getInterest(), "Expected to match");
        assertEquals("199.35", borrowerPayment.getPrincipal(), "Expected to match");
        assertEquals("4602.12", borrowerPayment.getRemainingOutstandingPrincipal(), "Expected to match");

        borrowerPayment = borrowerPayments.get(5);

        assertEquals("219.36", borrowerPayment.getBorrowerPaymentAmount(), "Expected to match");
        assertEquals("3999.06", borrowerPayment.getInitialOutstandingPrincipal(), "Expected to match");
        assertEquals("16.66", borrowerPayment.getInterest(), "Expected to match");
        assertEquals("202.70", borrowerPayment.getPrincipal(), "Expected to match");
        assertEquals("3796.36", borrowerPayment.getRemainingOutstandingPrincipal(), "Expected to match");

        borrowerPayment = borrowerPayments.get(23);

        assertEquals("219.28", borrowerPayment.getBorrowerPaymentAmount(), "Expected to match");
        assertEquals("218.37", borrowerPayment.getInitialOutstandingPrincipal(), "Expected to match");
        assertEquals("0.91", borrowerPayment.getInterest(), "Expected to match");
        assertEquals("218.37", borrowerPayment.getPrincipal(), "Expected to match");
        assertEquals("0", borrowerPayment.getRemainingOutstandingPrincipal(), "Expected to match");

    }

    @Test
    void testRepaymentPlanWithBlankLoanAmount() {
        ApplicationException exception = assertThrows(ApplicationException.class, () -> {
            repaymentPlanService
                    .repaymentPlan(RepaymentPlanRequestFactory.createRepaymentPlanRequestWithBlankLoanAmount());
        });
        assertEquals("Loan Amount should not be blank to for Generating the Repayment Schedule", exception.getMessage(),
                "Expected the error messages to match");
    }

    @Test
    void testRepaymentPlanWithNullLoanAmount() {
        ApplicationException exception = assertThrows(ApplicationException.class, () -> {
            repaymentPlanService
                    .repaymentPlan(RepaymentPlanRequestFactory.createRepaymentPlanRequestWithNullLoanAmount());
        });
        assertEquals("Loan Amount should not be blank to for Generating the Repayment Schedule", exception.getMessage(),
                "Expected the error messages to match");
    }

    @Test
    void testRepaymentPlanWithZeroLoanAmount() {
        ApplicationException exception = assertThrows(ApplicationException.class, () -> {
            repaymentPlanService
                    .repaymentPlan(RepaymentPlanRequestFactory.createRepaymentPlanRequestWithZeroLoanAmount());
        });
        assertEquals("Loan Amount should not be less than zero or zero for Generating the Repayment Schedule",
                exception.getMessage(), "Expected the error messages to match");
    }

    @Test
    void testRepaymentPlanWithInvalidDoubleLoanAmount() {
        ApplicationException exception = assertThrows(ApplicationException.class, () -> {
            repaymentPlanService
                    .repaymentPlan(RepaymentPlanRequestFactory.createRepaymentPlanRequestWithInvalidDoubleLoanAmount());
        });
        assertEquals("Loan Amount Zero not a valid double value to for Generating the Repayment Schedule",
                exception.getMessage(), "Expected the error messages to match");
    }

    @Test
    void testRepaymentPlanWithNullNominalRate() {
        ApplicationException exception = assertThrows(ApplicationException.class, () -> {
            repaymentPlanService
                    .repaymentPlan(RepaymentPlanRequestFactory.createRepaymentPlanRequestWithNullNominalRate());
        });
        assertEquals("Nominal rate should not be blank to for Generating the Repayment Schedule",
                exception.getMessage(), "Expected the error messages to match");
    }

    @Test
    void testRepaymentPlanWithBlankNominalRate() {
        ApplicationException exception = assertThrows(ApplicationException.class, () -> {
            repaymentPlanService
                    .repaymentPlan(RepaymentPlanRequestFactory.createRepaymentPlanRequestWithBlankNominalRate());
        });
        assertEquals("Nominal rate should not be blank to for Generating the Repayment Schedule",
                exception.getMessage(), "Expected the error messages to match");
    }

    @Test
    void testRepaymentPlanWithZeroNominalRate() {
        ApplicationException exception = assertThrows(ApplicationException.class, () -> {
            repaymentPlanService
                    .repaymentPlan(RepaymentPlanRequestFactory.createRepaymentPlanRequestWithZeroNominalRate());
        });
        assertEquals("Nominal rate should not be less than zero or zero for Generating the Repayment Schedule",
                exception.getMessage(), "Expected the error messages to match");
    }

    @Test
    void testRepaymentPlanWithInvalidDoubleNominalRate() {
        ApplicationException exception = assertThrows(ApplicationException.class, () -> {
            repaymentPlanService.repaymentPlan(
                    RepaymentPlanRequestFactory.createRepaymentPlanRequestWithInvalidDoubleNominalRate());
        });
        assertEquals("Nominal rate Zero not a valid double value to for Generating the Repayment Schedule",
                exception.getMessage(), "Expected the error messages to match");
    }

    @Test
    void testRepaymentPlanWithInValidTenureWithLoanSchedule() {
        ApplicationException exception = assertThrows(ApplicationException.class, () -> {
            repaymentPlanService.repaymentPlan(
                    RepaymentPlanRequestFactory.createRepaymentPlanRequestWithInValidTenureWithLoanSchedule());
        });
        assertEquals(
                "In the duration 306 months the Repayment Schedule cannot be completed, Please provide the correct duration",
                exception.getMessage(), "Expected the error messages to match");
    }

    @Test
    void testRepaymentPlanWithZeroMonthsDuration() {
        ApplicationException exception = assertThrows(ApplicationException.class, () -> {
            repaymentPlanService
                    .repaymentPlan(RepaymentPlanRequestFactory.createRepaymentPlanRequestWithZeroMonthsDuration());
        });
        assertEquals("Duration of the Repayment Schedule should be minimum 1", exception.getMessage(),
                "Expected the error messages to match");
    }

    @Test
    void testRepaymentPlanWithOneMonthsDuration() {
        final var banksResponse = repaymentPlanService
                .repaymentPlan(RepaymentPlanRequestFactory.createRepaymentPlanRequestWithOneMonthDuration());
        assertEquals(1, banksResponse.getBorrowerPayments().size(), "Expected the payments to be 1");
    }
}