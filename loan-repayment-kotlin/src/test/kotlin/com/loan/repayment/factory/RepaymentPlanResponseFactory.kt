package com.loan.repayment.factory

import com.loan.repayment.entity.RepaymentPlanResponse
import com.loan.repayment.factory.util.JsonUtil

/**
 *
 * @author Rama chandra
 */
object RepaymentPlanResponseFactory {
    /**
     * This method creates the RepaymentPlanResponse for the 6 months repayment
     * scheduled plan
     *
     * @return RepaymentPlanResponse
     */
    fun createRepaymentPlanResponse() : RepaymentPlanResponse {
        return JsonUtil().readFileAndGetData("repaymentscheduleresponse6months.json" ,
                RepaymentPlanResponse::class.java) as RepaymentPlanResponse
    }

    /**
     * This method creates the RepaymentPlanResponse for the one month repayment
     * scheduled plan
     *
     * @return RepaymentPlanResponse
     */
    fun createRepaymentPlanResponseSingleMonth() : RepaymentPlanResponse {
        return JsonUtil().readFileAndGetData("repaymentscheduleresponse1month.json" ,
                RepaymentPlanResponse::class.java) as RepaymentPlanResponse
    }
}