package com.loan.repayment.service

import com.loan.repayment.exception.ApplicationException
import com.loan.repayment.factory.RepaymentPlanRequestFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment

/**
 *
 * @author Rama chandra
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
internal class RepaymentPlanServiceIT {
    @Autowired
    private lateinit var repaymentPlanService : RepaymentPlanService
    @Test
    fun testRepaymentPlan() {
        val banksResponse = repaymentPlanService
                .repaymentPlan(RepaymentPlanRequestFactory.createRepaymentPlanRequest())
        assertEquals(24 , banksResponse.borrowerPayments.size , "Expected the payments to be 24")
        val borrowerPayments = banksResponse.borrowerPayments
        var borrowerPayment = borrowerPayments[0]
        assertEquals("219.36" , borrowerPayment.borrowerPaymentAmount , "Expected to match")
        assertEquals("5000.0" , borrowerPayment.initialOutstandingPrincipal, "Expected to match")
        assertEquals("20.83" , borrowerPayment.interest, "Expected to match")
        assertEquals("198.53" , borrowerPayment.principal, "Expected to match")
        assertEquals("4801.47" , borrowerPayment.remainingOutstandingPrincipal, "Expected to match")
        borrowerPayment = borrowerPayments[1]
        assertEquals("219.36" , borrowerPayment.borrowerPaymentAmount, "Expected to match")
        assertEquals("4801.47" , borrowerPayment.initialOutstandingPrincipal, "Expected to match")
        assertEquals("20.01" , borrowerPayment.interest, "Expected to match")
        assertEquals("199.35" , borrowerPayment.principal, "Expected to match")
        assertEquals("4602.12" , borrowerPayment.remainingOutstandingPrincipal, "Expected to match")
        borrowerPayment = borrowerPayments[5]
        assertEquals("219.36" , borrowerPayment.borrowerPaymentAmount, "Expected to match")
        assertEquals("3999.06" , borrowerPayment.initialOutstandingPrincipal, "Expected to match")
        assertEquals("16.66" , borrowerPayment.interest, "Expected to match")
        assertEquals("202.70" , borrowerPayment.principal, "Expected to match")
        assertEquals("3796.36" , borrowerPayment.remainingOutstandingPrincipal, "Expected to match")
        borrowerPayment = borrowerPayments[23]
        assertEquals("219.28" , borrowerPayment.borrowerPaymentAmount, "Expected to match")
        assertEquals("218.37" , borrowerPayment.initialOutstandingPrincipal, "Expected to match")
        assertEquals("0.91" , borrowerPayment.interest, "Expected to match")
        assertEquals("218.37" , borrowerPayment.principal, "Expected to match")
        assertEquals("0" , borrowerPayment.remainingOutstandingPrincipal, "Expected to match")
    }

    @Test
    fun testRepaymentPlanWithBlankLoanAmount() {
        val exception : ApplicationException = assertThrows(ApplicationException::class.java) {
            repaymentPlanService
                    .repaymentPlan(RepaymentPlanRequestFactory.createRepaymentPlanRequestWithBlankLoanAmount())
        }
        assertEquals("Loan Amount should not be blank to for Generating the Repayment Schedule" , exception.message ,
                "Expected the error messages to match")
    }

    @Test
    fun testRepaymentPlanWithZeroLoanAmount() {
        val exception : ApplicationException = assertThrows(ApplicationException::class.java) {
            repaymentPlanService
                    .repaymentPlan(RepaymentPlanRequestFactory.createRepaymentPlanRequestWithZeroLoanAmount())
        }
        assertEquals("Loan Amount should not be less than zero or zero for Generating the Repayment Schedule" ,
                exception.message , "Expected the error messages to match")
    }

    @Test
    fun testRepaymentPlanWithInvalidDoubleLoanAmount() {
        val exception : ApplicationException = assertThrows(ApplicationException::class.java) {
            repaymentPlanService
                    .repaymentPlan(RepaymentPlanRequestFactory.createRepaymentPlanRequestWithInvalidDoubleLoanAmount())
        }
        assertEquals("Loan Amount Zero not a valid double value to for Generating the Repayment Schedule" ,
                exception.message , "Expected the error messages to match")
    }

    @Test
    fun testRepaymentPlanWithNullNominalRate() {
        val exception = assertThrows(ApplicationException::class.java) {
            repaymentPlanService
                    .repaymentPlan(RepaymentPlanRequestFactory.createRepaymentPlanRequestWithNullNominalRate())
        }
        assertEquals("Nominal rate should not be blank to for Generating the Repayment Schedule" ,
                exception.message , "Expected the error messages to match")
    }

    @Test
    fun testRepaymentPlanWithBlankNominalRate() {
        val exception = assertThrows(ApplicationException::class.java) {
            repaymentPlanService
                    .repaymentPlan(RepaymentPlanRequestFactory.createRepaymentPlanRequestWithBlankNominalRate())
        }
        assertEquals("Nominal rate should not be blank to for Generating the Repayment Schedule" ,
                exception.message , "Expected the error messages to match")
    }

    @Test
    fun testRepaymentPlanWithZeroNominalRate() {
        val exception : ApplicationException = assertThrows(ApplicationException::class.java) {
            repaymentPlanService
                    .repaymentPlan(RepaymentPlanRequestFactory.createRepaymentPlanRequestWithZeroNominalRate())
        }
        assertEquals("Nominal rate should not be less than zero or zero for Generating the Repayment Schedule" ,
                exception.message , "Expected the error messages to match")
    }

    @Test
    fun testRepaymentPlanWithInvalidDoubleNominalRate() {
        val exception : ApplicationException = assertThrows(ApplicationException::class.java) {
            repaymentPlanService.repaymentPlan(
                    RepaymentPlanRequestFactory.createRepaymentPlanRequestWithInvalidDoubleNominalRate())
        }
        assertEquals("Nominal rate Zero not a valid double value to for Generating the Repayment Schedule" ,
                exception.message , "Expected the error messages to match")
    }

    @Test
    fun testRepaymentPlanWithInValidTenureWithLoanSchedule() {
        val exception : ApplicationException = assertThrows(ApplicationException::class.java) {
            repaymentPlanService.repaymentPlan(
                    RepaymentPlanRequestFactory.createRepaymentPlanRequestWithInValidTenureWithLoanSchedule())
        }
        assertEquals(
                "In the duration 306 months the Repayment Schedule cannot be completed, Please provide the correct duration" ,
                exception.message , "Expected the error messages to match")
    }

    @Test
    fun testRepaymentPlanWithZeroMonthsDuration() {
        val exception : ApplicationException = assertThrows(ApplicationException::class.java) {
            repaymentPlanService
                    .repaymentPlan(RepaymentPlanRequestFactory.createRepaymentPlanRequestWithZeroMonthsDuration())
        }
        assertEquals("Duration of the Repayment Schedule should be minimum 1" , exception.message ,
                "Expected the error messages to match")
    }

    @Test
    fun testRepaymentPlanWithOneMonthsDuration() {
        val banksResponse = repaymentPlanService
                .repaymentPlan(RepaymentPlanRequestFactory.createRepaymentPlanRequestWithOneMonthDuration())
        assertEquals(1 , banksResponse.borrowerPayments.size, "Expected the payments to be 1")
    }
}