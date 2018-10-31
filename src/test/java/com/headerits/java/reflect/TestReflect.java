package com.headerits.java.reflect;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * <p> Description: 反射测试 </p>
 * <p> Title: TestReflect </p>
 * <p> Create Time: 2018/10/24 17:13 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
public class TestReflect {

    private Class<?> clazz;

    @Before
    public void before() throws Exception {
        clazz = Class.forName("com.headerits.java.reflect.User");
    }

    @Test
    public void test() throws Exception {
        Constructor<?> constructor = clazz.getConstructor();
        User user = (User) constructor.newInstance();
        user.setUserName("测试");
        user.setPassword("123456");
        System.out.println(user);
    }

    @Test
    public void test1() throws Exception {
        User user = (User) clazz.newInstance();
        Field field = clazz.getDeclaredField("userName");
        field.setAccessible(true);
        field.set(user, "reflect field");
        System.out.println(user);
    }

    @Test
    public void test2() throws Exception {
        test3();
        User user = (User) clazz.newInstance();
        Method method = clazz.getMethod("setPassword", String.class);
        Object invoke = method.invoke(user, "reflect method");
        System.out.println(invoke);
        System.out.println(user);
        
        System.out.println(Thread.currentThread().getName());
    }

    @Test
    public void test3() throws Exception {
        User user = (User) clazz.newInstance();
        Method method = clazz.getDeclaredMethod("demo", double.class, double.class);
        System.out.println(method.getName());
        System.out.println(Arrays.toString(method.getParameterTypes()));
        System.out.println(method.getReturnType());
        method.setAccessible(true);
        double invoke = (double) method.invoke(user, 4.5, 4.5);
        System.out.println(invoke);
    }

    @Test
    public void test4() throws Exception{

        System.out.println("demo。。。。");
    }
}
