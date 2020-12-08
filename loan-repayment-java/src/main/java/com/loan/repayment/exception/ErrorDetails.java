package com.loan.repayment.exception;

import java.time.ZonedDateTime;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *
 * @author Ramachandra
 *
 */
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ErrorDetails {
    /**
     * Current time the error occured.
     */
    @ApiModelProperty(notes = "Current time the error occured")
    private ZonedDateTime date;

    /**
     * Stack trace of the error.
     */
    @ApiModelProperty(notes = "Stack trace of the error", example = "Nominal rate should not be less "
            + "than zero or zero for Generating the Repayment Schedule")
    private String message;

    /**
     * The API Caused the error.
     */
    @ApiModelProperty(notes = "The API Caused the error", example = "http://localhost:8080/generate-plan")
    private String details;
}
