package com.loan.repayment.exception

/**
 *
 * @author Rama chandra
 */
data class ApplicationException
/**
 * This is Custom Exception.
 *
 * @param message Exception message
 */
(override val message : String) : RuntimeException(message) {
    companion object {
        private const val serialVersionUID = 1L
    }
}