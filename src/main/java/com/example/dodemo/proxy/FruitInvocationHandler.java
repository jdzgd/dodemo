package com.example.dodemo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author jiangdongzhao
 * @version V1.0.0
 * @date 2019/10/22
 */
public class FruitInvocationHandler implements InvocationHandler {

    private Fruit fruit;

    public FruitInvocationHandler(Fruit fruit) {
        this.fruit = fruit;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开始削皮！");
        method.invoke(fruit,args);
        return null;
    }
}
