package com.headerits.java.demo;

import com.headerits.util.DateUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.util.Date;
import java.util.Properties;

/**
 * <p>Description: </p>
 * <p>Title: Demo </p>
 * <p>Create Time: 2018/8/8 16:14 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
public class Demo {

    @Test
    public void test() throws Exception {
        Object obj = null;
        System.out.println((String) obj);
    }

    @Test
    public void test1() throws Exception {
        String path = "log4j.properties";
        ClassPathResource classPathResource = new ClassPathResource(path);
        System.out.println(classPathResource.getFilename());
        System.out.println(classPathResource.getPath());
        System.out.println(classPathResource.getFile());
        System.out.println(classPathResource.getInputStream());
        System.out.println(Demo.class.getClassLoader().getResource(path).getPath());
    }

    @Test
    public void test2() throws Exception {
        String path = "log4j.properties";
        String path1 = "properties/jdbc.properties";
        Properties properties = PropertiesLoaderUtils.loadAllProperties(path);
        System.out.println(properties.getProperty("log4j.appender.Console.layout.ConversionPattern"));
    }

    @Test
    public void test3() throws Exception {
        System.out.println(this.getClass().getName());
        System.out.println(this.getClass().getPackage());
        System.out.println(this.getClass().getSimpleName());
    }

    @Test
    public void test4() throws Exception {
        Demo1.Demo2 demo2 = new Demo1.Demo2();
        demo2.setAge(18);
        System.out.println(demo2);
    }

    @Test
    public void test5() throws Exception {
        String format = String.format("%.3f", 5.2222222);
        System.out.println(format);
    }
    
    @Test
    public void test6() throws Exception{
        //e10adc3949ba59abbe56e057f20f883e
        System.out.println(DigestUtils.md5Hex("123456").equalsIgnoreCase("e10adc3949ba59abbe56e057f20f883e"));
    }

    @Test
    public void test7() throws Exception{
        //22:54:05.446

        // 2018-11-07 23:25:00
        Date date = new Date(1541604300000L);
        String string = DateUtils.date2String(date, DateUtils.COMMON_DATETIME);
        System.out.println(string);
    }
}
