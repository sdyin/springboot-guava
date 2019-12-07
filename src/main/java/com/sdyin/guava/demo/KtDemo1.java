package com.sdyin.guava.demo;

/**
 * @Description
 * @Author liuye
 * @Date 2019/12/7 18:12
 **/
public class KtDemo1 {

    public static void main(String[] args) {
        //调用kotlin方法
        String show = FuncKt.show("123");
        System.out.println(show);
    }
}
