package com.loan.repayment.controller

import com.loan.repayment.entity.RepaymentPlanRequest
import com.loan.repayment.entity.RepaymentPlanResponse
import com.loan.repayment.exception.ErrorResponse
import com.loan.repayment.service.IRepaymentPlanService
import io.swagger.annotations.*
import mu.KotlinLogging
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

/**
 *
 * @author Rama chandra
 */
private val logger = KotlinLogging.logger {}
@RestController
@Api(value = "API for the Generate a plan for repayment of loan" , tags = ["Repayment Plan"])
@CrossOrigin(origins = ["*"])
class RepaymentPlanController(private val repaymentPlanService : IRepaymentPlanService) {
    /**
     * This method calculates the repayment plan.
     *
     * @param repaymentPlanRequest Payload for the repayment of loan
     * @return repaymentPlanResponse
     */
    @PostMapping(path = ["/generate-plan"] , consumes = [MediaType.APPLICATION_JSON_VALUE] , produces = [MediaType.APPLICATION_JSON_VALUE])
    @PreAuthorize(value = "ROLE_USER")
    @ApiOperation(value = "Generate the Repayment Plan" , notes = "Generate the Repayment Plan" ,
            response = RepaymentPlanResponse::class , authorizations = [Authorization(HttpHeaders.AUTHORIZATION)])
    @ApiResponses(value = [ApiResponse(code = 201 , message = "The Repayment Schedule has been Successfully Generated" ,
            response = RepaymentPlanResponse::class) ,
        ApiResponse(code = 401 , message = "The request requires user authentication to access") ,
        ApiResponse(code = 403 , message = "Accessing the resource is forbidden") ,
        ApiResponse(code = 500 , message = "Repayment Schedule failed with Internal Error" , response = ErrorResponse::class) , ApiResponse(code = 404 , message = "The server has not found the Request-URI")])
    fun repaymentPlan(@RequestBody repaymentPlanRequest : RepaymentPlanRequest) : ResponseEntity<RepaymentPlanResponse> {
        logger.info("In repaymentPlan")
        return ResponseEntity<RepaymentPlanResponse>(repaymentPlanService.repaymentPlan(repaymentPlanRequest) , HttpStatus.CREATED)
    }
}