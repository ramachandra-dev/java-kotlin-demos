package com.loan.repayment.factory;

import com.loan.repayment.entity.RepaymentPlanResponse;
import com.loan.repayment.factory.util.JsonUtil;

/**
 * 
 * @author Ramachandra
 *
 */
public class RepaymentPlanResponseFactory {

    /**
     * This method creates the RepaymentPlanResponse for the 6 months repayment
     * scheduled plan
     * 
     * @return RepaymentPlanResponse
     */
    public static RepaymentPlanResponse createRepaymentPlanResponse() {
        return (RepaymentPlanResponse) new JsonUtil().readFileAndGetData("repaymentscheduleresponse6months.json",
                RepaymentPlanResponse.class);
    }

    /**
     * This method creates the RepaymentPlanResponse for the one month repayment
     * scheduled plan
     * 
     * @return RepaymentPlanResponse
     */
    public static RepaymentPlanResponse createRepaymentPlanResponseSingleMonth() {
        return (RepaymentPlanResponse) new JsonUtil().readFileAndGetData("repaymentscheduleresponse1month.json",
                RepaymentPlanResponse.class);
    }

}
