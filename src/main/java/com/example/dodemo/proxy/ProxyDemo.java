package com.example.dodemo.proxy;

/**
 * @author jiangdongzhao
 * @version V1.0.0
 * @date 2019/10/17
 */
public class ProxyDemo {

    public static void main(String[] args) {
        Apple apple = new Apple();
        Bananer bananer = new Bananer();
        Proxy proxy = new Proxy(apple);
        proxy.eat();
        proxy = new Proxy(bananer);
        proxy.eat();


        Fruit proxyInstance = (Fruit) java.lang.reflect.Proxy.newProxyInstance(Fruit.class.getClassLoader(),
                new Class[]{Fruit.class},
                new FruitInvocationHandler(apple));
        proxyInstance.eat();
        Fruit proxyInstance2 = (Fruit) java.lang.reflect.Proxy.newProxyInstance(Fruit.class.getClassLoader(),
                new Class[]{Fruit.class},
                new FruitInvocationHandler(bananer));
        System.out.println(proxyInstance.getClass().getName());
        System.out.println(proxyInstance2.getClass().getName());


    }

}