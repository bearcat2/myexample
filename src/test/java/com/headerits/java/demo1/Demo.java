package com.headerits.java.demo1;

import org.junit.Test;

import java.text.MessageFormat;
import java.util.Date;

/**
 * <p> Description: 测试类 </p>
 * <p> Title: Demo </p>
 * <p> Create Time: 2018/11/2 8:45 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
public class Demo {

    @Test
    public void test() throws Exception {
        String demo = "  d e m   o";
        System.out.println(demo.replaceAll("\\s", ""));
    }

    @Test
    public void test1() throws Exception {
        String format = MessageFormat.format("{0,date,yyyy-MM-dd HH:mm:ss}", new Date());
        System.out.println(format);
    }

    @Test
    public void test2() throws Exception {
        String format = MessageFormat.format("{0,number,#.##}", 3.1411111592653);
        System.out.println(format);
    }



}
