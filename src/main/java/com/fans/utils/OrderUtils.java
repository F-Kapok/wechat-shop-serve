package com.fans.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Calendar;

/**
 * className: OrderUtils
 *
 * @author k
 * @version 1.0
 * @description 订单工具类
 * @date 2020-06-07 16:38
 **/
@Component(value = "orderUtils")
public class OrderUtils {

    private static String[] yearCodes;

    @Value("${kapok.year-codes}")
    public void setYearCodes(String yearCodes) {
        OrderUtils.yearCodes = yearCodes.split(",");
    }

    public static String makeOrderNo() {
        StringBuilder joiner = new StringBuilder();
        Calendar calendar = Calendar.getInstance();
        String mills = String.valueOf(calendar.getTimeInMillis());
        String micro = LocalDateTime.now().toString();
        String random = String.valueOf(Math.random() * 1000).substring(0, 2);
        joiner.append(yearCodes[calendar.get(Calendar.YEAR) - 2019])
                .append(Integer.toHexString(calendar.get(Calendar.MONTH) + 1).toUpperCase())
                .append(calendar.get(Calendar.DAY_OF_MONTH))
                .append(mills.substring(mills.length() - 5))
                .append(micro.substring(micro.length() - 3))
                .append(random);
        return joiner.toString();
    }
}
