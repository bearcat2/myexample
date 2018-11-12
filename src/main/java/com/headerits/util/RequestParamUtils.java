package com.headerits.util;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;

/**
 * <p> Description: 从request请求对象中获取请求参数 </p>
 * <p> Title: RequestParamUtils </p>
 * <p> Create Time: 2018/11/12 22:33 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
public class RequestParamUtils {

    /**
     * 获取请求参数
     *
     * @param httpServletRequest 请求对象
     * @return 返回json格式字符串 {key:value}
     */
    public static String getRequestParams(HttpServletRequest httpServletRequest) {
        try {
            MyRequestWrapper myRequestWrapper = new MyRequestWrapper(httpServletRequest);
            return myRequestWrapper.getBody();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
        //if (MediaType.APPLICATION_JSON_VALUE.equalsIgnoreCase(httpServletRequest.getContentType())) {
        //    return getJsonRequestParams(httpServletRequest);
        //}
        //return getKeyValueRequestParams(httpServletRequest);
    }

    public static String getKeyValueRequestParams(HttpServletRequest httpServletRequest) {
        StringBuilder stringBuilder = new StringBuilder(64);
        stringBuilder.append("{");
        Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String[] values = entry.getValue();
            String value = values.length > 1 ? Arrays.toString(values) : values[0];
            stringBuilder.append(entry.getKey()).append(":").append("\"")
                    .append(value).append("\"").append(" ,");
        }
        if (stringBuilder.indexOf(",") != -1) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public static String getJsonRequestParams(HttpServletRequest httpServletRequest) {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = httpServletRequest.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("{}");
            }
        } catch (IOException ex) {
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                }
            }
        }
        return stringBuilder.toString();
    }
}
