package com.example.dodemo.proxy;

/**
 * @author jiangdongzhao
 * @version V1.0.0
 * @date 2019/10/22
 */
public class Apple implements Fruit{
    private String name;

    @Override
    public void eat() {
        System.out.println("吃苹果");
    }
}