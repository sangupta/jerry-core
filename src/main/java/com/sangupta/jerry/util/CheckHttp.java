package com.sangupta.jerry.util;

import com.sangupta.jerry.constants.HttpStatusCode;
import com.sangupta.jerry.exceptions.HttpException;

public class CheckHttp {

    public static void notNull(Object object, String message) {
        if(object == null) {
            throw new HttpException(HttpStatusCode.BAD_REQUEST, message);
        }
    }

    public static void notEmpty(String str, String message) {
        if(AssertUtils.isEmpty(str)) {
            throw new HttpException(HttpStatusCode.BAD_REQUEST, message);
        }
    }

    public static <T> T unauthorized(String message) {
        throw new HttpException(HttpStatusCode.UNAUTHORIZED, message);
    }

    public static <T> T forbidden(String message) {
        throw new HttpException(HttpStatusCode.FORBIDDEN, message);
    }

}
