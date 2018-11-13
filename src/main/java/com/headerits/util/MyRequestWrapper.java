package com.headerits.util;

import com.headerits.constants.SystemConstants;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * <p> Description: 解决从request流中获取数据后导致流关闭无法再次获取流数据 </p>
 * <p> Title: MyRequestWrapper </p>
 * <p> Create Time: 2018/11/13 11:31 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
@Slf4j
public class MyRequestWrapper extends HttpServletRequestWrapper {
    private String body;

    public MyRequestWrapper(HttpServletRequest request) {
        super(request);
        initBody(request);
    }

    /**
     * 从请求对象流中获取 body字符串放入自己缓存中,避免流读取一次就关闭导致数据无法获取问题
     *
     * @param request 请求对象
     */
    private void initBody(HttpServletRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, SystemConstants.UTF8));
                char[] charBuffer = new char[64];
                int bytesRead;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("{}");
            }
        } catch (Exception ex) {
            log.error("【MyRequestWrapper】初始化body串发生错误", ex);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception ex) {
                    log.error("【MyRequestWrapper】初始化body串发生错误", ex);
                }
            }
        }
        body = stringBuilder.toString();
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes(SystemConstants.UTF8));
        ServletInputStream servletInputStream = new ServletInputStream() {
            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };
        return servletInputStream;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream(), SystemConstants.UTF8));
    }

    public String getBody() {
        return this.body;
    }
}