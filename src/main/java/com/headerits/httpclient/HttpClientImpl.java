package com.headerits.httpclient;

import com.headerits.constants.SystemConstants;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: 使用HttpClient模拟客户端像服务端发送http请求 </p>
 * <p>Title: HttpClientImpl </p>
 * <p>Create Time: 2018/5/13 10:42 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
@Component
public class HttpClientImpl implements HttpClient {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientImpl.class);

    @Autowired
    private CloseableHttpClient httpClient;

    @Autowired
    private RequestConfig requestConfig;

    @Override
    public String doGet(String url, Map<String, String> param) {
        String result = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();
            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);
            // 设置请求参数
            httpGet.setConfig(requestConfig);
            // 执行请求
            response = httpClient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity(), SystemConstants.UTF8);
            }
        } catch (Exception e) {
            logger.info("【httpclient get 请求发生异常】", e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                logger.info("【httpclient get 请求发生异常】", e);
            }
        }
        return result;
    }

    @Override
    public String doGet(String url) {
        return doGet(url, null);
    }

    @Override
    public String doPost(String url, Map<String, String> param) {
        String result = "";
        CloseableHttpResponse response = null;
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 设置请求参数
            httpPost.setConfig(requestConfig);

            //设置请求头
            httpPost.setHeader("clientSerial","smartzone-client-001");

            // 创建参数列表
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key)));
                }
                // 模拟表单 这里参数编码不指定默认是iso8859-1
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList,SystemConstants.UTF8);
                httpPost.setEntity(entity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity(), SystemConstants.UTF8);
            }
        } catch (Exception e) {
            logger.info("【httpclient post 请求发生异常】", e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                logger.info("【httpclient post 请求发生异常】", e);
            }
        }
        return result;
    }

    @Override
    public String doPost(String url) {
        return doPost(url, null);
    }

    @Override
    public String doPostJson(String url, String json) {
        CloseableHttpResponse response = null;
        String result = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 设置请求参数
            httpPost.setConfig(requestConfig);
            // 设置请求的报文头部的编码
            httpPost.setHeader(new BasicHeader("Content-Type", "application/json; charset=UTF-8"));
            if (StringUtils.isNotBlank(json)) {
                // 创建请求内容
                StringEntity entity = new StringEntity(json,SystemConstants.UTF8);
                httpPost.setEntity(entity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity(), SystemConstants.UTF8);
            }
        } catch (Exception e) {
            logger.info("【httpclient post 请求发生异常】", e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                logger.info("【httpclient post 请求发生异常】", e);
            }
        }
        return result;
    }
}
