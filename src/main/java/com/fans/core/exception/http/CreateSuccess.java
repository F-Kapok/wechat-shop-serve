package com.fans.core.exception.http;

import org.springframework.http.HttpStatus;

/**
 * className: CreateSuccess
 *
 * @author k
 * @version 1.0
 * @description 请求成功返回
 * @date 2020-06-03 23:09
 **/
public class CreateSuccess extends HttpException {

    public CreateSuccess(Integer code) {
        this.code = code;
        this.httpStatus = HttpStatus.CREATED.value();
    }
}
