package com.fans.core.exception.http;

import lombok.*;

import java.io.Serializable;

/**
 * className: ParameterException
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-05-31 23:30
 **/
public class ParameterException extends HttpException {

    public ParameterException(Integer code) {
        this.code = code;
        this.httpStatus = 400;
    }
}
