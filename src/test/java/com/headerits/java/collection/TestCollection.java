package com.headerits.java.collection;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> Description: 集合测试类 </p>
 * <p> Title: TestCollection </p>
 * <p> Create Time: 2018/10/24 16:47 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
public class TestCollection {

    private List<String> list = new ArrayList<>();

    @Before
    public void before() {
        list.add("demo1");
        list.add("demo2");
        list.add("demo3");
        list.add("demo4");
        list.add("demo5");
    }

    @Test
    public void test() throws Exception {
        List<String> removeList = new ArrayList<>();
        removeList.add("demo1");
        removeList.add("demo76");
        System.out.println("移除之前,元素内容 = " + list);
        list.removeAll(removeList);
        System.out.println("移除之后,元素内容 = " + list);
    }

    @Test
    public void test1() throws Exception {
        List<String> retainList = new ArrayList<>();
        retainList.add("demo1");
        retainList.add("demo2333333");
        list.retainAll(retainList);
        System.out.println("list ==" + list);
    }
}
