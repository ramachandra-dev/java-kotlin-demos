package com.loan.repayment.controller

import com.loan.repayment.exception.ApplicationException
import com.loan.repayment.exception.ErrorDetails
import com.loan.repayment.exception.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.ZoneOffset
import java.time.ZonedDateTime
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 *
 * @author Rama chandra
 */
@RestControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {
    /**
     * This method builds the error response.
     *
     * @param exception Runtime Exception
     * @param response HttpServlet Response
     * @param request HttpServlet Request
     * @return errorResponse
     */
    @ExceptionHandler(ApplicationException::class)
    fun handleException(
            exception : ApplicationException ,
            response : HttpServletResponse , request : HttpServletRequest ,
    ) : ResponseEntity<Any> {
        response.status = HttpStatus.INTERNAL_SERVER_ERROR.value()
        return error(exception , HttpStatus.INTERNAL_SERVER_ERROR , request.requestURL)
    }

    /**
     * This method builds the error response.
     *
     * @param <E>
     * @param exception
     * @param httpStatus
     * @param details
     * @return errorResponse
    </E> */
    private fun <E : Exception> error(
            exception : E , httpStatus : HttpStatus ,
            details : StringBuffer ,
    ) : ResponseEntity<Any> {
        val message : String = exception.message.toString()
        return ResponseEntity(ErrorResponse(ErrorDetails(ZonedDateTime.now(ZoneOffset.UTC) ,
                message , details.toString())) ,
                httpStatus)
    }
}