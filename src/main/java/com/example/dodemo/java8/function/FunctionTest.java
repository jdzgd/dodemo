package com.example.dodemo.java8.function;

import java.util.function.Function;

/**
 * java8Function
 *
 * @author jiangdongzhao
 * @version V1.0.0
 * @date 2020/4/14
 */
public class FunctionTest {

    public static String numFunction(Integer num, Function<Integer, String> function) {
        return function.apply(num);
    }

    public static void main(String[] args) {
        String result = numFunction(10, x -> x + 10 + "元");
        System.out.println(result);
    }
}
