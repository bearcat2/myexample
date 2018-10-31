package com.headerits.util;

import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * <p>Description: 资源文件读取工具 </p>
 * <p>Title: PropertiesUtils </p>
 * <p>Create Time: 2018/6/1 14:19 </p>
 *
 * @author: 未知
 * @version: 1.0
 */
public class PropertiesUtils {
    /**
     * 当打开多个资源文件时，缓存资源文件
     */
    private static HashMap<String, PropertiesUtils> configMap = new HashMap<String, PropertiesUtils>();
    /**
     * 打开文件时间，判断超时使用
     */
    private Date loadTime = null;
    /**
     * 资源文件
     */
    private ResourceBundle resourceBundle = null;
    /**
     * 默认资源文件名称
     */
    private static final String NAME = "resource";
    /**
     * 缓存时间 5 分钟
     */
    private static final Integer TIME_OUT = 5 * 60 * 1000;
    /**
     * 布尔字符串
     */
    private static final String TRUE_STR = "true";

    /**
     * 私有构造方法，创建单例
     *
     * @param name
     */
    private PropertiesUtils(String name) {
        this.loadTime = new Date();
        this.resourceBundle = ResourceBundle.getBundle(name);
    }

    public static synchronized PropertiesUtils getInstance() {
        return getInstance(NAME);
    }

    public static synchronized PropertiesUtils getInstance(String name) {
        PropertiesUtils conf = configMap.get(name);
        if (null == conf) {
            conf = new PropertiesUtils(name);
            configMap.put(name, conf);
        }
        // 判断是否打开的资源文件是否超时5分钟
        if ((System.currentTimeMillis() - conf.getLoadTime().getTime()) > TIME_OUT) {
            conf = new PropertiesUtils(name);
            configMap.put(name, conf);
        }
        return conf;
    }

    /**
     * 根据key读取value(字符串)
     *
     * @param key 属性key
     * @return 异常返回空字符串
     */
    public String get(String key) {
        try {
            return resourceBundle.getString(key);
        } catch (MissingResourceException e) {
            return "";
        }
    }

    /**
     * 根据key读取value(整形)
     *
     * @param key 属性key
     * @return 异常返回 null
     */
    public Integer getInt(String key) {
        try {
            return Integer.parseInt(resourceBundle.getString(key));
        } catch (MissingResourceException e) {
            return null;
        }
    }

    /**
     * 根据key读取value(长整形)
     *
     * @param key 属性key
     * @return 异常返回 null
     */
    public Long getLong(String key) {
        try {
            return Long.parseLong(resourceBundle.getString(key));
        } catch (MissingResourceException e) {
            return null;
        }
    }

    /**
     * 根据key读取value(布尔)
     *
     * @param key 属性key
     * @return 异常返回false
     */
    public boolean getBoolean(String key) {
        try {
            String value = resourceBundle.getString(key);
            if (TRUE_STR.equals(value)) {
                return true;
            }
            return false;
        } catch (MissingResourceException e) {
            return false;
        }
    }

    /**
     * 资源文件载入时间
     *
     * @return
     */
    public Date getLoadTime() {
        return loadTime;
    }

    /**
     * 获取资源文件所有key的集合
     *
     * @return 返回所有的list集合
     */
    public List<String> getKeyList() {
        List<String> reslist = new ArrayList<String>();
        Set<String> keyset = resourceBundle.keySet();
        for (Iterator<String> it = keyset.iterator(); it.hasNext(); ) {
            String lkey = (String) it.next();
            reslist.add(lkey);
        }
        if (!CollectionUtils.isEmpty(reslist)) {
            Collections.sort(reslist);
        }
        return reslist;
    }
}
