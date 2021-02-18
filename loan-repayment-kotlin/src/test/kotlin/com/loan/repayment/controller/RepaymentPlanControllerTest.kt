package com.loan.repayment.controller

import com.loan.repayment.exception.ApplicationException
import com.loan.repayment.factory.ApplicationExceptionFactory
import com.loan.repayment.factory.RepaymentPlanRequestFactory
import com.loan.repayment.factory.RepaymentPlanResponseFactory
import com.loan.repayment.service.RepaymentPlanService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

/**
 *
 * @author Rama chandra
 */
class RepaymentPlanControllerTest {

    private val repaymentPlanService = mockk<RepaymentPlanService>()

    private var repaymentPlanController = RepaymentPlanController(repaymentPlanService)

    @Test
    fun testRepaymentPlan() {
        val request = RepaymentPlanRequestFactory.createRepaymentPlanRequest()
        every { repaymentPlanService.repaymentPlan(request) } returns
                RepaymentPlanResponseFactory.createRepaymentPlanResponse()
        val banksResponse = repaymentPlanController.repaymentPlan(request)
        assertEquals(6 , banksResponse.body!!.borrowerPayments.size , "Expected the payments to be 6")
        val borrowerPayments = banksResponse.body!!.borrowerPayments
        var borrowerPayment = borrowerPayments[0]
        assertEquals("2052.83" , borrowerPayment.borrowerPaymentAmount , "Expected to match")
        assertEquals("12000.0" , borrowerPayment.initialOutstandingPrincipal , "Expected to match")
        assertEquals("90.00" , borrowerPayment.interest , "Expected to match")
        assertEquals("1962.83" , borrowerPayment.principal , "Expected to match")
        assertEquals("10037.17" , borrowerPayment.remainingOutstandingPrincipal , "Expected to match")
        assertEquals(HttpStatus.CREATED , banksResponse.statusCode , "Expected to match")
        borrowerPayment = borrowerPayments[1]
        assertEquals("2052.83" , borrowerPayment.borrowerPaymentAmount , "Expected to match")
        assertEquals("10037.17" , borrowerPayment.initialOutstandingPrincipal , "Expected to match")
        assertEquals("75.28" , borrowerPayment.interest , "Expected to match")
        assertEquals("1977.55" , borrowerPayment.principal , "Expected to match")
        assertEquals("8059.62" , borrowerPayment.remainingOutstandingPrincipal , "Expected to match")
        borrowerPayment = borrowerPayments[4]
        assertEquals("2052.83" , borrowerPayment.borrowerPaymentAmount , "Expected to match")
        assertEquals("4059.91" , borrowerPayment.initialOutstandingPrincipal , "Expected to match")
        assertEquals("30.45" , borrowerPayment.interest , "Expected to match")
        assertEquals("2022.38" , borrowerPayment.principal , "Expected to match")
        assertEquals("2037.53" , borrowerPayment.remainingOutstandingPrincipal , "Expected to match")
        borrowerPayment = borrowerPayments[5]
        assertEquals("2052.81" , borrowerPayment.borrowerPaymentAmount , "Expected to match")
        assertEquals("2037.53" , borrowerPayment.initialOutstandingPrincipal , "Expected to match")
        assertEquals("15.28" , borrowerPayment.interest , "Expected to match")
        assertEquals("2037.53" , borrowerPayment.principal , "Expected to match")
        assertEquals("0" , borrowerPayment.remainingOutstandingPrincipal , "Expected to match")
        assertEquals(HttpStatus.CREATED , banksResponse.statusCode , "Expected to match")
    }

    @Test
    fun testRepaymentPlanWithBlankLoanAmount() {
        val exception : ApplicationException = assertThrows(ApplicationException::class.java) {
            val request = RepaymentPlanRequestFactory.createRepaymentPlanRequestWithBlankLoanAmount()
            every { repaymentPlanService.repaymentPlan(request) } throws
                    ApplicationExceptionFactory.createApplicationExceptionWithBlankLoanAmount()
            repaymentPlanController.repaymentPlan(request)
        }
        assertEquals(
            "Loan Amount should not be blank to for Generating the Repayment Schedule" , exception.message ,
            "Expected the error messages to match"
        )
    }

    @Test
    fun testRepaymentPlanWithZeroLoanAmount() {
        val exception : ApplicationException = assertThrows(ApplicationException::class.java) {
            val request = RepaymentPlanRequestFactory.createRepaymentPlanRequestWithZeroLoanAmount()
            every { repaymentPlanService.repaymentPlan(request) } throws
                    ApplicationExceptionFactory.createApplicationExceptionWithZeroLoanAmount()
            repaymentPlanController.repaymentPlan(request)
        }
        assertEquals(
            "Loan Amount should not be less than zero or zero for Generating the Repayment Schedule" ,
            exception.message , "Expected the error messages to match"
        )
    }

    @Test
    fun testRepaymentPlanWithInvalidDoubleLoanAmount() {
        val exception : ApplicationException = assertThrows(ApplicationException::class.java) {
            val request = RepaymentPlanRequestFactory.createRepaymentPlanRequestWithInvalidDoubleLoanAmount()
            every { repaymentPlanService.repaymentPlan(request) } throws
                    ApplicationExceptionFactory.createApplicationExceptionWithInvalidDoubleLoanAmount()
            repaymentPlanController.repaymentPlan(request)
        }
        assertEquals(
            "Loan Amount Zero not a valid double value to for Generating the Repayment Schedule" ,
            exception.message , "Expected the error messages to match"
        )
    }

    @Test
    fun testRepaymentPlanWithNullNominalRate() {
        val exception : ApplicationException = assertThrows(ApplicationException::class.java) {
            val request = RepaymentPlanRequestFactory.createRepaymentPlanRequestWithNullNominalRate()
            every { repaymentPlanService.repaymentPlan(request) } throws
                    ApplicationExceptionFactory.createApplicationExceptionWithNullNominalRate()
            repaymentPlanController.repaymentPlan(request)
        }
        assertEquals(
            "Nominal rate should not be blank to for Generating the Repayment Schedule" ,
            exception.message , "Expected the error messages to match"
        )
    }

    @Test
    fun testRepaymentPlanWithBlankNominalRate() {
        val exception : ApplicationException = assertThrows(ApplicationException::class.java) {
            val request = RepaymentPlanRequestFactory.createRepaymentPlanRequestWithBlankNominalRate()
            every { repaymentPlanService.repaymentPlan(request) } throws
                    ApplicationExceptionFactory.createApplicationExceptionWithBlankNominalRate()
            repaymentPlanController.repaymentPlan(request)
        }
        assertEquals(
            "Nominal rate should not be blank to for Generating the Repayment Schedule" ,
            exception.message , "Expected the error messages to match"
        )
    }

    @Test
    fun testRepaymentPlanWithZeroNominalRate() {
        val exception : ApplicationException = assertThrows(ApplicationException::class.java) {
            val request = RepaymentPlanRequestFactory.createRepaymentPlanRequestWithZeroNominalRate()
            every { repaymentPlanService.repaymentPlan(request) } throws
                    ApplicationExceptionFactory.createApplicationExceptionWithZeroNominalRate()
            repaymentPlanController.repaymentPlan(request)
        }
        assertEquals(
            "Nominal rate should not be less than zero or zero for Generating the Repayment Schedule" ,
            exception.message , "Expected the error messages to match"
        )
    }

    @Test
    fun testRepaymentPlanWithInvalidDoubleNominalRate() {
        val exception : ApplicationException = assertThrows(ApplicationException::class.java) {
            val request = RepaymentPlanRequestFactory.createRepaymentPlanRequestWithInvalidDoubleNominalRate()
            every { repaymentPlanService.repaymentPlan(request) } throws
                    ApplicationExceptionFactory.createApplicationExceptionWithInvalidDoubleNominalRate()
            repaymentPlanController.repaymentPlan(request)
        }
        assertEquals(
            "Nominal rate Zero not a valid double value to for Generating the Repayment Schedule" ,
            exception.message , "Expected the error messages to match"
        )
    }

    @Test
    fun testRepaymentPlanWithInValidTenureWithLoanSchedule() {
        val exception : ApplicationException = assertThrows(ApplicationException::class.java) {
            val request = RepaymentPlanRequestFactory.createRepaymentPlanRequestWithInValidTenureWithLoanSchedule()
            every { repaymentPlanService.repaymentPlan(request) } throws
                    ApplicationExceptionFactory.createApplicationExceptionWithInValidTenureWithLoanSchedule()
            repaymentPlanController.repaymentPlan(request)
        }
        assertEquals(
            "In the duration 306 months the Repayment Schedule cannot be completed, Please provide the correct duration" ,
            exception.message , "Expected the error messages to match"
        )
    }

    @Test
    fun testRepaymentPlanWithZeroMonthsDuration() {
        val exception : ApplicationException = assertThrows(ApplicationException::class.java) {
            val request = RepaymentPlanRequestFactory.createRepaymentPlanRequestWithZeroMonthsDuration()
            every { repaymentPlanService.repaymentPlan(request) } throws
                    ApplicationExceptionFactory.createApplicationExceptionWithZeroMonthsDuration()
            repaymentPlanController.repaymentPlan(request)
        }
        assertEquals(
            "Duration of the Repayment Schedule should be minimum 1" , exception.message ,
            "Expected the error messages to match"
        )
    }

    @Test
    fun testRepaymentPlanWithOneMonthsDuration() {
        val request = RepaymentPlanRequestFactory.createRepaymentPlanRequestWithOneMonthDuration()
        every { repaymentPlanService.repaymentPlan(request) } returns
                RepaymentPlanResponseFactory.createRepaymentPlanResponseSingleMonth()
        val banksResponse = repaymentPlanController.repaymentPlan(request)
        assertEquals(1 , banksResponse.body!!.borrowerPayments.size , "Expected the payments to be 1")
        val borrowerPayments = banksResponse.body!!.borrowerPayments
        val borrowerPayment = borrowerPayments[0]
        assertEquals("12090.00" , borrowerPayment.borrowerPaymentAmount , "Expected to match")
        assertEquals("12000.0" , borrowerPayment.initialOutstandingPrincipal , "Expected to match")
        assertEquals("90.00" , borrowerPayment.interest , "Expected to match")
        assertEquals("12000.0" , borrowerPayment.principal , "Expected to match")
        assertEquals("0" , borrowerPayment.remainingOutstandingPrincipal , "Expected to match")
    }
}