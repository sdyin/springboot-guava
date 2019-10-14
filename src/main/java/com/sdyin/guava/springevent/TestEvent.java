package com.sdyin.guava.springevent;

import org.springframework.context.ApplicationEvent;

/**
 * @Description TODO
 * @Author liuye
 * @Date 2019/10/14 10:19
 **/
public class TestEvent extends ApplicationEvent {

    private static final long serialVersionUID = -7056841418193254583L;

    private String address;

    private String text;

    public TestEvent(Object source) {
        super(source);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "TestEvent{" +
                "address='" + address + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
