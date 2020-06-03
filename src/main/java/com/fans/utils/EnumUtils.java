package com.fans.utils;

import com.fans.common.CodeEnum;

import java.util.stream.Stream;

/**
 * className: EnumUtils
 *
 * @author k
 * @version 1.0
 * @description 枚举通用工具
 * @date 2020-06-03 23:34
 **/
public class EnumUtils {
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        return Stream.of(enumClass.getEnumConstants()).filter(ec -> ec.getCode().equals(code)).findAny().orElse(null);
    }

}
