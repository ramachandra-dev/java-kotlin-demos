package com.loan.repayment.exception;

/**
 *
 * @author Ramachandra
 *
 */
public class ApplicationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * This is Custom Exception.
     *
     * @param message Exception message
     */
    public ApplicationException(final String message) {
        super(message);
    }

}
