package com.loan.repayment.exception;

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
public class ErrorResponse {

    /**
     * Details of the error messsage.
     */
    @ApiModelProperty(notes = "Details of the error messsage")
    private ErrorDetails errorDetails;
}
