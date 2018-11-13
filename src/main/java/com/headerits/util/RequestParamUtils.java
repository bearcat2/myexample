package com.headerits.util;

import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
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
        String contentType = httpServletRequest.getContentType();
        if (MediaType.APPLICATION_JSON_VALUE.equalsIgnoreCase(contentType)) {
            // 获取json类型参数
            return getJsonRequestParams(httpServletRequest);
        }
        if (MediaType.MULTIPART_FORM_DATA_VALUE.equals(contentType)) {
            // 二进制流不记录请求参数,直接返回空
            return null;
        }
        // 走到这没有返回,默认返回 key - value类型数据参数
        return getKeyValueRequestParams(httpServletRequest);
    }

    public static String getKeyValueRequestParams(HttpServletRequest httpServletRequest) {
        StringBuilder stringBuilder = new StringBuilder(64);
        stringBuilder.append("{");
        Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String[] values = entry.getValue();
            String value = values.length > 1 ? Arrays.toString(values) : values[0];
            stringBuilder.append(entry.getKey())
                    .append(" : ")
                    .append("\"")
                    .append(value)
                    .append("\"")
                    .append(" ,");
        }
        if (stringBuilder.indexOf(",") != -1) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public static String getJsonRequestParams(HttpServletRequest httpServletRequest) {
        MyRequestWrapper myRequestWrapper = (MyRequestWrapper) httpServletRequest;
        return myRequestWrapper.getBody();
    }
}
