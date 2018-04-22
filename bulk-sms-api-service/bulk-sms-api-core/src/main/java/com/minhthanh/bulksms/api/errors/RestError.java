package com.minhthanh.bulksms.api.errors;

/**
 * Created by luyenchu on 7/28/16.
 */
public enum RestError {
    ERROR_INSERT_DB(500);
    int code;

    RestError(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }
}
