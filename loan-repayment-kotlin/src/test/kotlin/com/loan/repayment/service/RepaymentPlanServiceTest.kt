package com.loan.repayment.service

import com.loan.repayment.exception.ApplicationException
import com.loan.repayment.factory.RepaymentPlanRequestFactory
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.MockitoAnnotations

/**
 *
 * @author Rama chandra
 */
internal class RepaymentPlanServiceTest {
    @InjectMocks
    private lateinit var repaymentPlanService : RepaymentPlanService

    @BeforeEach
    fun init() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testRepaymentPlan() {
        val banksResponse = repaymentPlanService
                .repaymentPlan(RepaymentPlanRequestFactory.createRepaymentPlanRequest())
        Assertions.assertEquals(24 , banksResponse.borrowerPayments.size , "Expected the payments to be 24")
    }

    @Test
    fun testRepaymentPlanWithBlankLoanAmount() {
        val exception = Assertions.assertThrows(ApplicationException::class.java) {
            repaymentPlanService
                    .repaymentPlan(RepaymentPlanRequestFactory.createRepaymentPlanRequestWithBlankLoanAmount())
        }
        Assertions.assertEquals("Loan Amount should not be blank to for Generating the Repayment Schedule" , exception.message ,
                "Expected the error messages to match")
    }

    @Test
    fun testRepaymentPlanWithZeroLoanAmount() {
        val exception = Assertions.assertThrows(ApplicationException::class.java) {
            repaymentPlanService
                    .repaymentPlan(RepaymentPlanRequestFactory.createRepaymentPlanRequestWithZeroLoanAmount())
        }
        Assertions.assertEquals("Loan Amount should not be less than zero or zero for Generating the Repayment Schedule" ,
                exception.message , "Expected the error messages to match")
    }

    @Test
    fun testRepaymentPlanWithInvalidDoubleLoanAmount() {
        val exception = Assertions.assertThrows(ApplicationException::class.java) {
            repaymentPlanService
                    .repaymentPlan(RepaymentPlanRequestFactory.createRepaymentPlanRequestWithInvalidDoubleLoanAmount())
        }
        Assertions.assertEquals("Loan Amount Zero not a valid double value to for Generating the Repayment Schedule" ,
                exception.message , "Expected the error messages to match")
    }

    @Test
    fun testRepaymentPlanWithNullNominalRate() {
        val exception = Assertions.assertThrows(ApplicationException::class.java) {
            repaymentPlanService
                    .repaymentPlan(RepaymentPlanRequestFactory.createRepaymentPlanRequestWithNullNominalRate())
        }
        Assertions.assertEquals("Nominal rate should not be blank to for Generating the Repayment Schedule" ,
                exception.message , "Expected the error messages to match")
    }

    @Test
    fun testRepaymentPlanWithBlankNominalRate() {
        val exception = Assertions.assertThrows(ApplicationException::class.java) {
            repaymentPlanService
                    .repaymentPlan(RepaymentPlanRequestFactory.createRepaymentPlanRequestWithBlankNominalRate())
        }
        Assertions.assertEquals("Nominal rate should not be blank to for Generating the Repayment Schedule" ,
                exception.message , "Expected the error messages to match")
    }

    @Test
    fun testRepaymentPlanWithZeroNominalRate() {
        val exception = Assertions.assertThrows(ApplicationException::class.java) {
            repaymentPlanService
                    .repaymentPlan(RepaymentPlanRequestFactory.createRepaymentPlanRequestWithZeroNominalRate())
        }
        Assertions.assertEquals("Nominal rate should not be less than zero or zero for Generating the Repayment Schedule" ,
                exception.message , "Expected the error messages to match")
    }

    @Test
    fun testRepaymentPlanWithInvalidDoubleNominalRate() {
        val exception = Assertions.assertThrows(ApplicationException::class.java) {
            repaymentPlanService.repaymentPlan(
                    RepaymentPlanRequestFactory.createRepaymentPlanRequestWithInvalidDoubleNominalRate())
        }
        Assertions.assertEquals("Nominal rate Zero not a valid double value to for Generating the Repayment Schedule" ,
                exception.message , "Expected the error messages to match")
    }

    @Test
    fun testRepaymentPlanWithInValidTenureWithLoanSchedule() {
        val exception = Assertions.assertThrows(ApplicationException::class.java) {
            repaymentPlanService.repaymentPlan(
                    RepaymentPlanRequestFactory.createRepaymentPlanRequestWithInValidTenureWithLoanSchedule())
        }
        Assertions.assertEquals(
                "In the duration 306 months the Repayment Schedule cannot be completed, Please provide the correct duration" ,
                exception.message , "Expected the error messages to match")
    }

    @Test
    fun testRepaymentPlanWithZeroMonthsDuration() {
        val exception : ApplicationException = Assertions.assertThrows(ApplicationException::class.java) {
            repaymentPlanService.repaymentPlan(RepaymentPlanRequestFactory.createRepaymentPlanRequestWithZeroMonthsDuration())
        }
        Assertions.assertEquals("Duration of the Repayment Schedule should be minimum 1" , exception.message ,
                "Expected the error messages to match")
    }

    @Test
    fun testRepaymentPlanWithOneMonthsDuration() {
        val banksResponse = repaymentPlanService
                .repaymentPlan(RepaymentPlanRequestFactory.createRepaymentPlanRequestWithOneMonthDuration())
        Assertions.assertEquals(1 , banksResponse.borrowerPayments.size ,
                "Expected the payments to be 1")
    }
}