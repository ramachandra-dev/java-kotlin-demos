package com.loan.repayment.service;

import com.loan.repayment.entity.BorrowerPayment;
import com.loan.repayment.entity.RepaymentPlanRequest;
import com.loan.repayment.entity.RepaymentPlanResponse;
import com.loan.repayment.exception.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;
import static java.math.BigDecimal.*;
import static java.math.RoundingMode.HALF_UP;

/**
 * @author Ramachandra
 */
@Service
@Slf4j
public class RepaymentPlanService implements IRepaymentPlanService {

    /**
     * This is total no of days in the month.
     */
    private static final Integer DAYS_IN_MONTH = 30;

    /**
     * This is total no of days in the year.
     */
    private static final Integer DAYS_IN_YEAR = 360;

    /**
     * This method calculates the repayment plan.
     *
     * @param repaymentPlanRequest Payload for the repayment of loan
     * @return repaymentPlanResponse
     */
    @Override
    public RepaymentPlanResponse repaymentPlan(final RepaymentPlanRequest repaymentPlanRequest) {

        validateRequest(repaymentPlanRequest);

        final var rate = Double.parseDouble(repaymentPlanRequest.getNominalRate()) / 100;
        // Getting the monthly rate as yearly rate is the input
        final var monthlyRate = rate / 12;
        // Duration of the repayments loan period
        final var term = repaymentPlanRequest.getDuration();

        // Initial values
        var initialOutstandingPrincipal = Double.parseDouble(repaymentPlanRequest.getLoanAmount());
        var remainingOutstandingPrincipal = BigDecimal.valueOf(initialOutstandingPrincipal);

        // Calculating the Annuity/ Borrower Payment Amount
        var borrowerPaymentAmount = calculateBorrowerPaymentAmount(BigDecimal.valueOf(initialOutstandingPrincipal),
                monthlyRate, term);
        validateBorrowerPaymentAmount(borrowerPaymentAmount);
        // Start date of the loan repayment schedule
        var currentDate = repaymentPlanRequest.getStartDate();
        final List<BorrowerPayment> borrowerPayments = new ArrayList<>();

        for ( var i = 1; i <= term; i++ ) {
            // Old Remaining Outstanding Principal becomes current Outstanding
            // Principal
            var daysInMonth = currentDate.getMonth().maxLength();
            var daysInYear = YearMonth.from(currentDate).lengthOfYear();
            log.info("Days in the month {}, Days in the Year {}", daysInMonth, daysInYear);
            initialOutstandingPrincipal = remainingOutstandingPrincipal.doubleValue();
            // Calculate Interest
            final var interest = calculateInterest(rate, initialOutstandingPrincipal);
            // Calculate Principal
            var principal = calculatePrincipal(borrowerPaymentAmount, interest);
            // Calculate OutstandingPrincipal
            remainingOutstandingPrincipal = calculateRemainingOutstandingPrincipal(principal,
                    initialOutstandingPrincipal);
            // If the OutstandingPrincipal is less the Annuity/BorrowerPayment
            // Amount we
            // need to add remainingOutstandingPrincipal to the current annuity
            // and reset
            // the remainingOutstandingPrincipal to zero and update the
            // principal amount
            // with initialOutstandingPrincipal
            if (valueOf(initialOutstandingPrincipal).compareTo(borrowerPaymentAmount) < 0) {
                borrowerPaymentAmount = borrowerPaymentAmount.add(remainingOutstandingPrincipal).setScale(2, HALF_UP);
                remainingOutstandingPrincipal = ZERO;
                principal = valueOf(initialOutstandingPrincipal);
            }
            // If there any outstanding principal
            if (i == term && remainingOutstandingPrincipal.compareTo(ZERO) > 0) {
                throw new ApplicationException("In the duration " + term
                        + " months the Repayment Schedule cannot be completed, Please provide the correct duration");
            }
            borrowerPayments.add(buildBorrowerPayment(borrowerPaymentAmount, initialOutstandingPrincipal,
                    remainingOutstandingPrincipal, interest, principal, currentDate));
            // Increasing the current date to next month
            currentDate = currentDate.plusMonths(1);
        }
        return new RepaymentPlanResponse(borrowerPayments);
    }

    /**
     * This method checks whether the borrower payment amount or annuity after
     * calculation is less the zero or not.
     *
     * @param borrowerPaymentAmount
     */
    private void validateBorrowerPaymentAmount(final BigDecimal borrowerPaymentAmount) {
        if (borrowerPaymentAmount.compareTo(ZERO) <= 0) {
            throw new ApplicationException("Borrower Payment Amount or Calculated Annuity is Zero, "
                    + "Please provide correct data to generate the Repayment Schedule");
        }
    }

    /**
     * This method checks/validates all the input parameters for blank, invalid
     * double values, whether duration is provided as zero or not.
     *
     * @param repaymentPlanRequest
     */
    private void validateRequest(final RepaymentPlanRequest repaymentPlanRequest) {

        validateData(repaymentPlanRequest.getLoanAmount(), "Loan Amount");
        validateData(repaymentPlanRequest.getNominalRate(), "Nominal rate");

        if (Double.compare(repaymentPlanRequest.getDuration(), 1) < 0) {
            throw new ApplicationException("Duration of the Repayment Schedule should be minimum 1");
        }
    }

    /**
     * This method checks/validates all the input parameters for blank, invalid
     * double values.
     *
     * @param value
     * @param message
     */
    private void validateData(final String value, final String message) {
        if (ObjectUtils.isEmpty(value)) {
            throw new ApplicationException(message + " should not be blank to for Generating the Repayment Schedule");
        }

        try {
            Double.valueOf(value);
        } catch (final NumberFormatException e) {
            throw new ApplicationException(
                    message + " " + value + " not a valid double value to for Generating the Repayment Schedule");
        }

        if (Double.valueOf(value).compareTo((double) 0) <= 0) {
            throw new ApplicationException(
                    message + " should not be less than zero or zero for Generating the Repayment Schedule");
        }
    }

    /**
     * This method calculates the interest.
     *
     * @param rate
     * @param initialOutstandingPrincipal
     * @return interest
     */
    private BigDecimal calculateInterest(final Double rate, final Double initialOutstandingPrincipal) {
        return valueOf(rate * DAYS_IN_MONTH * initialOutstandingPrincipal / DAYS_IN_YEAR).setScale(2,
                HALF_UP);
    }

    protected BigDecimal calculateInterest(final Double rate, final Double initialOutstandingPrincipal,
                                         int daysOfMonth, int daysOfYear) {
        return valueOf(rate * daysOfMonth * initialOutstandingPrincipal / daysOfYear).setScale(2,
                HALF_UP);
    }

    /**
     * This method calculates the OutstandingPrincipal.
     *
     * @param principal
     * @param initialOutstandingPrincipal
     * @return OutstandingPrincipal
     */
    private BigDecimal calculateRemainingOutstandingPrincipal(final BigDecimal principal,
                                                              final Double initialOutstandingPrincipal) {
        return valueOf(initialOutstandingPrincipal - principal.doubleValue()).setScale(2, HALF_UP);
    }

    /**
     * This method calculates the principal.
     *
     * @param borrowerPaymentAmount
     * @param interest
     * @return principal
     */
    private BigDecimal calculatePrincipal(final BigDecimal borrowerPaymentAmount, final BigDecimal interest) {
        return borrowerPaymentAmount.subtract(interest).setScale(2, HALF_UP);
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
    private BorrowerPayment buildBorrowerPayment(final BigDecimal borrowerPaymentAmount,
                                                 final Double initialOutstandingPrincipal, final BigDecimal remainingOutstandingPrincipal,
                                                 final BigDecimal interest, final BigDecimal principal, final ZonedDateTime currentDate) {
        //@formatter:off
        return BorrowerPayment
                .builder()
                .borrowerPaymentAmount(borrowerPaymentAmount.toString())
                .date(currentDate)
                .initialOutstandingPrincipal(initialOutstandingPrincipal.toString())
                .interest(interest.toString())
                .principal(principal.toString())
                .remainingOutstandingPrincipal(remainingOutstandingPrincipal.toString())
                .build();
        //@formatter:on
    }

    /**
     * This method is to calculate the Annuity or Borrower Payment Amount.
     *
     * @param loanAmount
     * @param monthlyRate
     * @param term
     * @return borrowerPaymentAmount
     * <p>
     * AnnuityPayment = PV/[(1-(1+r)^-n)/r] P - Payment V - Present Value r
     * - Rate of Period n - Number of Periods
     */
    private BigDecimal calculateBorrowerPaymentAmount(final BigDecimal loanAmount, final double monthlyRate,
                                                      final int term) {
        //@formatter:off
        return loanAmount
                .multiply(valueOf(monthlyRate))
                .divide(ONE.subtract(valueOf(pow(valueOf(monthlyRate)
                        .add(ONE).doubleValue(), -term))), HALF_UP)
                .setScale(2, HALF_UP);
        //@formatter:on
    }
}
