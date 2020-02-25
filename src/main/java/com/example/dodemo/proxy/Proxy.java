package com.example.dodemo.proxy;

/**
 * @author jiangdongzhao
 * @version V1.0.0
 * @date 2019/10/22
 */
public class Proxy implements Fruit{
    private Fruit fruit;

    public Proxy(Fruit fruit) {
        this.fruit = fruit;
    }

    @Override
    public void eat() {
        System.out.println("削皮后，");
        fruit.eat();
    }

}