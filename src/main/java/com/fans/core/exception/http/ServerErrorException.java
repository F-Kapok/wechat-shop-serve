package com.fans.core.exception.http;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * className: ServerErrorException
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-07-05 12:09
 **/
public class ServerErrorException extends HttpException {

    public ServerErrorException(int code) {
        this.code = code;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();
    }
}
