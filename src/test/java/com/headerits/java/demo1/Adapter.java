package com.headerits.java.demo1;

/**
 * <p> Description: 类适配器适配器 </p>
 * <p> Title: Adapter </p>
 * <p> Create Time: 2018/11/12 18:16 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
public class Adapter extends Adaptee{

    // 集成被适配对象,调用父类方法

    public void request(){
        super.specificRequest();
    }
}
