package com.headerits.java.date;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>Description: </p>
 * <p>Title: Demo1 </p>
 * <p>Create Time: 2018/9/27 9:13 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
public class Demo1 {

    @Test
    public void test() throws Exception{
        List<String> list = Collections.emptyList();
        list = Arrays.asList("11", "22");
        System.out.println(list);
    }
}
