package com.wz.appplay.common.exception;

/**
 * Created by wz on 17-5-15.
 */

public class ApiException extends BaseException {


    public ApiException(int code, String displayMessage) {
        super(code, displayMessage);
    }

    public ApiException(String message, int code, String displayMessage) {
        super(message, code, displayMessage);
    }
}
