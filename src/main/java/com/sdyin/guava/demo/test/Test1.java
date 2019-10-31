package com.sdyin.guava.demo.test;

import java.util.function.IntConsumer;

/**
 * @Description
 * @Author liuye
 * @Date 2019/10/30 11:27
 **/
public class Test1 {

    private static final ThreadLocal<Integer> catalog = new ThreadLocal<>();

    public void zero(IntConsumer printNumber) throws InterruptedException {
        //函数式接口,及参数
        printNumber.accept(0);
    }

    public static void main(String[] args) {
        Test1 test1 = new Test1();
        try {
            test1.zero((i)-> System.out.println(i));
        } catch (InterruptedException e) {

        }
    }
}
