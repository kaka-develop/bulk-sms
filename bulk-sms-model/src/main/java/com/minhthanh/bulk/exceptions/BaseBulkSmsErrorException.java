package com.minhthanh.bulk.exceptions;

/**
 * Created by luyenchu on 7/24/16.
 */
public class BaseBulkSmsErrorException extends RuntimeException {
    public BaseBulkSmsErrorException() {
    }

    public BaseBulkSmsErrorException(Throwable cause) {
        super(cause);
    }

    public BaseBulkSmsErrorException(String message) {
        super(message);
    }

    public BaseBulkSmsErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseBulkSmsErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
