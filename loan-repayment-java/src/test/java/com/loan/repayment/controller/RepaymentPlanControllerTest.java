package com.loan.repayment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import com.loan.repayment.entity.RepaymentPlanRequest;
import com.loan.repayment.exception.ApplicationException;
import com.loan.repayment.factory.ApplicationExceptionFactory;
import com.loan.repayment.factory.RepaymentPlanRequestFactory;
import com.loan.repayment.factory.RepaymentPlanResponseFactory;
import com.loan.repayment.service.RepaymentPlanService;

/**
 * 
 * @author Ramachandra
 *
 */
class RepaymentPlanControllerTest {

    @InjectMocks
    RepaymentPlanController repaymentPlanController;

    @Mock
    private RepaymentPlanService repaymentPlanService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRepaymentPlan() {
        when(repaymentPlanService.repaymentPlan(any(RepaymentPlanRequest.class)))
                .thenReturn(RepaymentPlanResponseFactory.createRepaymentPlanResponse());
        final var banksResponse = repaymentPlanController
                .repaymentPlan(RepaymentPlanRequestFactory.createRepaymentPlanRequest());
        assertEquals(6, banksResponse.getBody().getBorrowerPayments().size(), "Expected the payments to be 6");

        var borrowerPayments = banksResponse.getBody().getBorrowerPayments();
        var borrowerPayment = borrowerPayments.get(0);

        assertEquals("2052.83", borrowerPayment.getBorrowerPaymentAmount(), "Expected to match");
        assertEquals("12000.0", borrowerPayment.getInitialOutstandingPrincipal(), "Expected to match");
        assertEquals("90.00", borrowerPayment.getInterest(), "Expected to match");
        assertEquals("1962.83", borrowerPayment.getPrincipal(), "Expected to match");
        assertEquals("10037.17", borrowerPayment.getRemainingOutstandingPrincipal(), "Expected to match");

        assertEquals(HttpStatus.CREATED, banksResponse.getStatusCode(), "Expected to match");

        borrowerPayment = borrowerPayments.get(1);

        assertEquals("2052.83", borrowerPayment.getBorrowerPaymentAmount(), "Expected to match");
        assertEquals("10037.17", borrowerPayment.getInitialOutstandingPrincipal(), "Expected to match");
        assertEquals("75.28", borrowerPayment.getInterest(), "Expected to match");
        assertEquals("1977.55", borrowerPayment.getPrincipal(), "Expected to match");
        assertEquals("8059.62", borrowerPayment.getRemainingOutstandingPrincipal(), "Expected to match");

        borrowerPayment = borrowerPayments.get(4);

        assertEquals("2052.83", borrowerPayment.getBorrowerPaymentAmount(), "Expected to match");
        assertEquals("4059.91", borrowerPayment.getInitialOutstandingPrincipal(), "Expected to match");
        assertEquals("30.45", borrowerPayment.getInterest(), "Expected to match");
        assertEquals("2022.38", borrowerPayment.getPrincipal(), "Expected to match");
        assertEquals("2037.53", borrowerPayment.getRemainingOutstandingPrincipal(), "Expected to match");

        borrowerPayment = borrowerPayments.get(5);

        assertEquals("2052.81", borrowerPayment.getBorrowerPaymentAmount(), "Expected to match");
        assertEquals("2037.53", borrowerPayment.getInitialOutstandingPrincipal(), "Expected to match");
        assertEquals("15.28", borrowerPayment.getInterest(), "Expected to match");
        assertEquals("2037.53", borrowerPayment.getPrincipal(), "Expected to match");
        assertEquals("0", borrowerPayment.getRemainingOutstandingPrincipal(), "Expected to match");

        assertEquals(HttpStatus.CREATED, banksResponse.getStatusCode(), "Expected to match");
    }

    @Test
    void testRepaymentPlanWithBlankLoanAmount() {
        ApplicationException exception = assertThrows(ApplicationException.class, () -> {
            when(repaymentPlanService.repaymentPlan(any(RepaymentPlanRequest.class)))
                    .thenThrow(ApplicationExceptionFactory.createApplicationExceptionWithBlankLoanAmount());
            repaymentPlanController
                    .repaymentPlan(RepaymentPlanRequestFactory.createRepaymentPlanRequestWithBlankLoanAmount());
        });
        assertEquals("Loan Amount should not be blank to for Generating the Repayment Schedule", exception.getMessage(),
                "Expected the error messages to match");
    }

    @Test
    void testRepaymentPlanWithNullLoanAmount() {
        ApplicationException exception = assertThrows(ApplicationException.class, () -> {
            when(repaymentPlanService.repaymentPlan(any(RepaymentPlanRequest.class)))
                    .thenThrow(ApplicationExceptionFactory.createApplicationExceptionWithNullLoanAmount());
            repaymentPlanController
                    .repaymentPlan(RepaymentPlanRequestFactory.createRepaymentPlanRequestWithNullLoanAmount());
        });
        assertEquals("Loan Amount should not be blank to for Generating the Repayment Schedule", exception.getMessage(),
                "Expected the error messages to match");
    }

    @Test
    void testRepaymentPlanWithZeroLoanAmount() {
        ApplicationException exception = assertThrows(ApplicationException.class, () -> {
            when(repaymentPlanService.repaymentPlan(any(RepaymentPlanRequest.class)))
                    .thenThrow(ApplicationExceptionFactory.createApplicationExceptionWithZeroLoanAmount());
            repaymentPlanController
                    .repaymentPlan(RepaymentPlanRequestFactory.createRepaymentPlanRequestWithZeroLoanAmount());
        });
        assertEquals("Loan Amount should not be less than zero or zero for Generating the Repayment Schedule",
                exception.getMessage(), "Expected the error messages to match");
    }

    @Test
    void testRepaymentPlanWithInvalidDoubleLoanAmount() {
        ApplicationException exception = assertThrows(ApplicationException.class, () -> {
            when(repaymentPlanService.repaymentPlan(any(RepaymentPlanRequest.class)))
                    .thenThrow(ApplicationExceptionFactory.createApplicationExceptionWithInvalidDoubleLoanAmount());
            repaymentPlanController
                    .repaymentPlan(RepaymentPlanRequestFactory.createRepaymentPlanRequestWithInvalidDoubleLoanAmount());
        });
        assertEquals("Loan Amount Zero not a valid double value to for Generating the Repayment Schedule",
                exception.getMessage(), "Expected the error messages to match");
    }

    @Test
    void testRepaymentPlanWithNullNominalRate() {
        ApplicationException exception = assertThrows(ApplicationException.class, () -> {
            when(repaymentPlanService.repaymentPlan(any(RepaymentPlanRequest.class)))
                    .thenThrow(ApplicationExceptionFactory.createApplicationExceptionWithNullNominalRate());
            repaymentPlanController
                    .repaymentPlan(RepaymentPlanRequestFactory.createRepaymentPlanRequestWithNullNominalRate());
        });
        assertEquals("Nominal rate should not be blank to for Generating the Repayment Schedule",
                exception.getMessage(), "Expected the error messages to match");
    }

    @Test
    void testRepaymentPlanWithBlankNominalRate() {
        ApplicationException exception = assertThrows(ApplicationException.class, () -> {
            when(repaymentPlanService.repaymentPlan(any(RepaymentPlanRequest.class)))
                    .thenThrow(ApplicationExceptionFactory.createApplicationExceptionWithBlankNominalRate());
            repaymentPlanController
                    .repaymentPlan(RepaymentPlanRequestFactory.createRepaymentPlanRequestWithBlankNominalRate());
        });
        assertEquals("Nominal rate should not be blank to for Generating the Repayment Schedule",
                exception.getMessage(), "Expected the error messages to match");
    }

    @Test
    void testRepaymentPlanWithZeroNominalRate() {
        ApplicationException exception = assertThrows(ApplicationException.class, () -> {
            when(repaymentPlanService.repaymentPlan(any(RepaymentPlanRequest.class)))
                    .thenThrow(ApplicationExceptionFactory.createApplicationExceptionWithZeroNominalRate());
            repaymentPlanController
                    .repaymentPlan(RepaymentPlanRequestFactory.createRepaymentPlanRequestWithZeroNominalRate());
        });
        assertEquals("Nominal rate should not be less than zero or zero for Generating the Repayment Schedule",
                exception.getMessage(), "Expected the error messages to match");
    }

    @Test
    void testRepaymentPlanWithInvalidDoubleNominalRate() {
        ApplicationException exception = assertThrows(ApplicationException.class, () -> {
            when(repaymentPlanService.repaymentPlan(any(RepaymentPlanRequest.class)))
                    .thenThrow(ApplicationExceptionFactory.createApplicationExceptionWithInvalidDoubleNominalRate());
            repaymentPlanController.repaymentPlan(
                    RepaymentPlanRequestFactory.createRepaymentPlanRequestWithInvalidDoubleNominalRate());
        });
        assertEquals("Nominal rate Zero not a valid double value to for Generating the Repayment Schedule",
                exception.getMessage(), "Expected the error messages to match");
    }

    @Test
    void testRepaymentPlanWithInValidTenureWithLoanSchedule() {
        ApplicationException exception = assertThrows(ApplicationException.class, () -> {
            when(repaymentPlanService.repaymentPlan(any(RepaymentPlanRequest.class))).thenThrow(
                    ApplicationExceptionFactory.createApplicationExceptionWithInValidTenureWithLoanSchedule());
            repaymentPlanController.repaymentPlan(
                    RepaymentPlanRequestFactory.createRepaymentPlanRequestWithInValidTenureWithLoanSchedule());
        });
        assertEquals(
                "In the duration 306 months the Repayment Schedule cannot be completed, Please provide the correct duration",
                exception.getMessage(), "Expected the error messages to match");
    }

    @Test
    void testRepaymentPlanWithZeroMonthsDuration() {
        ApplicationException exception = assertThrows(ApplicationException.class, () -> {
            when(repaymentPlanService.repaymentPlan(any(RepaymentPlanRequest.class)))
                    .thenThrow(ApplicationExceptionFactory.createApplicationExceptionWithZeroMonthsDuration());
            repaymentPlanController
                    .repaymentPlan(RepaymentPlanRequestFactory.createRepaymentPlanRequestWithZeroMonthsDuration());
        });
        assertEquals("Duration of the Repayment Schedule should be minimum 1", exception.getMessage(),
                "Expected the error messages to match");
    }

    @Test
    void testRepaymentPlanWithOneMonthsDuration() {
        when(repaymentPlanService.repaymentPlan(any(RepaymentPlanRequest.class)))
                .thenReturn(RepaymentPlanResponseFactory.createRepaymentPlanResponseSingleMonth());
        final var banksResponse = repaymentPlanController
                .repaymentPlan(RepaymentPlanRequestFactory.createRepaymentPlanRequestWithOneMonthDuration());
        assertEquals(1, banksResponse.getBody().getBorrowerPayments().size(), "Expected the payments to be 1");

        var borrowerPayments = banksResponse.getBody().getBorrowerPayments();
        var borrowerPayment = borrowerPayments.get(0);

        assertEquals("12090.00", borrowerPayment.getBorrowerPaymentAmount(), "Expected to match");
        assertEquals("12000.0", borrowerPayment.getInitialOutstandingPrincipal(), "Expected to match");
        assertEquals("90.00", borrowerPayment.getInterest(), "Expected to match");
        assertEquals("12000.0", borrowerPayment.getPrincipal(), "Expected to match");
        assertEquals("0", borrowerPayment.getRemainingOutstandingPrincipal(), "Expected to match");
    }
}