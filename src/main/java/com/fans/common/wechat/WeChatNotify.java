package com.fans.common.wechat;

import com.fans.core.exception.http.ServerErrorException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * className: WeChatNotity
 *
 * @author k
 * @version 1.0
 * @description 微信通知处理
 * @date 2020-07-10 23:30
 **/
public class WeChatNotify {

    public static String fail() {
        return "false";
    }

    public static String success() {
        return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
    }

    public static String readNotify(InputStream inputStream) {

        StringBuilder result = new StringBuilder();
        String line;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServerErrorException(9999);
        }
        return result.toString();
    }
}
