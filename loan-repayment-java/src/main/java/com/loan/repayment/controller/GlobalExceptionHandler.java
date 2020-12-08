package com.loan.repayment.controller;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.loan.repayment.exception.ApplicationException;
import com.loan.repayment.exception.ErrorDetails;
import com.loan.repayment.exception.ErrorResponse;

/**
 *
 * @author Ramachandra
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * This method builds the error response.
     *
     * @param exception Runtime Exception
     * @param response HttpServlet Response
     * @param request HttpServlet Request
     * @return errorResponse
     */
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Object> handleFeignStatusException(final ApplicationException exception,
            final HttpServletResponse response, final HttpServletRequest request) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return error(exception, HttpStatus.INTERNAL_SERVER_ERROR, request.getRequestURL());
    }

    /**
     * This method builds the error response.
     *
     * @param <E>
     * @param exception
     * @param httpStatus
     * @param details
     * @return errorResponse
     */
    private <E extends Exception> ResponseEntity<Object> error(final E exception, final HttpStatus httpStatus,
            final StringBuffer details) {
        return new ResponseEntity<>(new ErrorResponse(new ErrorDetails(ZonedDateTime.now(ZoneOffset.UTC),
                Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName()), details.toString())),
                httpStatus);
    }

}
