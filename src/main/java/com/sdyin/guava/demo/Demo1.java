package com.sdyin.guava.demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Description:
 * @Author: liuye
 * @time: 2019/9/15$ 下午3:57$
 */
public class Demo1 {

    public static void main(String[] args) {
        //注解方式 扫描指定包
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext("com.sdyin.guava");
        ac.close();
    }
}
