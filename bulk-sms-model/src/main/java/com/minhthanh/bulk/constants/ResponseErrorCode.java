package com.minhthanh.bulk.constants;

/**
 * Created by luyenchu on 7/12/16.
 */
public enum ResponseErrorCode {
    CONCURRENT_ERROR(-1, "Concurrent error"),
    SUCCESS(0, "Success"),
    REQUEST_PARAM_INVALID(-2, "Requested Parameters Error!");
    public int errorCode;
    public String message;

    ResponseErrorCode(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
