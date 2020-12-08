package com.loan.repayment.service;

import com.loan.repayment.entity.RepaymentPlanRequest;
import com.loan.repayment.entity.RepaymentPlanResponse;

/**
 *
 * @author Ramachandra
 *
 */
public interface IRepaymentPlanService {

    /**
     * This method calculates the repayment plan.
     *
     * @param repaymentPlanRequest Payload for the repayment loan
     * @return repaymentPlanResponse
     */
    RepaymentPlanResponse repaymentPlan(RepaymentPlanRequest repaymentPlanRequest);
}
