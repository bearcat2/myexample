package com.headerits.java.demo1;

/**
 * <p> Description: 对象适配器 </p>
 * <p> Title: Adapter1 </p>
 * <p> Create Time: 2018/11/12 18:18 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
public class Adapter1 {

    // 持续一个被适配对象的引用,一般由spring容器注入
    private Adaptee adaptee;

    public Adapter1(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    public void request() {
        // 转发调用
        this.adaptee.specificRequest();
    }
}
