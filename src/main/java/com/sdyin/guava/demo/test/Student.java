package com.sdyin.guava.demo.test;

import lombok.Data;

/**
 * @Description TODO
 * @Author liuye
 * @Date 2019/11/30 17:57
 **/
@Data
public class Student {

    private String name;

    public Student(String name) {
        this.name = name;
    }
}
