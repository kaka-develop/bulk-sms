package com.minhthanh.bulk.admin.model;

import org.springframework.http.HttpStatus;

/**
 * Created by phuongtm on 9/30/16.
 */
public class AjaxResponse {

    private String message;
    private int code;

    private AjaxResponse(AjaxResponseBuilder builder) {
        this.message = builder.message;
        this.code = builder.code;
    }

    public static IAddMessage builder() {
        return new AjaxResponseBuilder();
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public interface IAddMessage {
        IAddCode addMessage(String message);
    }

    public interface IAddCode {
        IBuild addCode(HttpStatus code);
    }

    public interface IBuild {
        AjaxResponse build();
    }

    private static class AjaxResponseBuilder implements IAddMessage, IAddCode, IBuild {

        private String message;
        private int code;

        public IAddCode addMessage(String message) {
            this.message = message;
            return this;
        }

        public IBuild addCode(HttpStatus code) {
            this.code = code.value();
            return this;
        }

        public AjaxResponse build() {
            return new AjaxResponse(this);
        }
    }

}
