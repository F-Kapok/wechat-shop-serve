package com.fans.utils;

import javax.persistence.AttributeConverter;
import java.util.List;

/**
 * className: ObjectToJson
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-05-31 15:12
 **/
public class ObjectToJson<T> implements AttributeConverter<List<T>, String> {


    @Override
    public String convertToDatabaseColumn(List<T> attribute) {
        return null;
    }

    @Override
    public List<T> convertToEntityAttribute(String dbData) {
        return null;
    }
}
