package com.loan.repayment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.loan.repayment.exception.ApplicationException;
import com.loan.repayment.factory.RepaymentPlanRequestFactory;

/**
 * 
 * @author Ramachandra
 *
 */
class RepaymentPlanServiceTest {

    @InjectMocks
    private RepaymentPlanService repaymentPlanService;

    @BeforeEach()
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRepaymentPlan() {
        final var banksResponse = repaymentPlanService
                .repaymentPlan(RepaymentPlanRequestFactory.createRepaymentPlanRequest());
        assertEquals(24, banksResponse.getBorrowerPayments().size(), "Expected the payments to be 24");
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
        System.out.println(exception.getMessage());
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