package com.sdyin.guava.demo;

import com.sdyin.guava.springevent.TestEvent;
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
        TestEvent testEvent = new TestEvent("hello");
        testEvent.setAddress("127.0.0.2");
        testEvent.setText("天青色等烟雨");
        ac.publishEvent(testEvent);
        ac.close();
    }
}
