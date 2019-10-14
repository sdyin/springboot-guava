package com.sdyin.guava.springevent;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author liuye
 * @Date 2019/10/14 10:22
 **/
@Component
public class TestListener implements ApplicationListener<ApplicationEvent> {

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if(applicationEvent instanceof TestEvent){
            System.out.println("监听到TestEvent事件类型:" + applicationEvent);
            System.out.println("data:"+ applicationEvent);
        }else{
            System.out.println("监听到其他类型事件,data:"+ applicationEvent);
        }
    }
}
