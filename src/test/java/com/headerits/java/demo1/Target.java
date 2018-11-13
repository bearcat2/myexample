package com.headerits.java.demo1;

/**
 * <p> Description: 具体调用目标对象</p>
 * <p> Title: Target </p>
 * <p> Create Time: 2018/11/12 18:19 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
public class Target {

    public static void main(String[] args) {
        // 类适配器
        Adapter adapter = new Adapter();
        adapter.request();

        // 对象设配器
        Adaptee adaptee = new Adaptee();
        Adapter1 adapter1 = new Adapter1(adaptee);
        adapter1.request();

    }
}
