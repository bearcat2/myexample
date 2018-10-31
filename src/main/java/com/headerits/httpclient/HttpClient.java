package com.headerits.httpclient;

import java.util.Map;

/**
 * <p>Description: 将httpclient抽取出来一个接口</p>
 * <p>Title: HttpClient </p>
 * <p>Create Time: 2018/5/27 11:30 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
public interface HttpClient {

    /**
     * 带参数的 get 请求
     *
     * @param url   请求的url地址
     * @param param 需要传递的参数map集合
     * @return 返回服务端响应内容
     */
    String doGet(String url, Map<String, String> param);

    /**
     * 不带参数的 get 请求
     *
     * @param url 请求的url地址
     * @return 返回服务端响应内容
     */
    String doGet(String url);

    /**
     * 传递map类型参数的 post 请求
     *
     * @param url   请求的url地址
     * @param param 需要传递服务端map集合
     * @return 返回服务端响应内容
     */
    String doPost(String url, Map<String, String> param);

    /**
     * 不带参数的 post 请求
     *
     * @param url 请求的url地址
     * @return 返回服务端响应内容
     */
    String doPost(String url);

    /**
     * 传递json参数的 post 请求
     *
     * @param url  请求的url地址
     * @param json 需要传递服务端的json数据
     * @return 返回服务端响应内容
     */
    String doPostJson(String url, String json);
}
