package com.sdyin.guava.demo.test;

/**
 * @Description
 * 一个方法不能修改一个基本数据类型的参数（即数值型或布尔型）。
 * 一个方法可以改变一个对象参数的状态。
 * 一个方法不能让对象参数引用一个新的对象。
 * @Author liuye
 * @Date 2019/11/30 17:53
 **/
public class Test3 {


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Student s1 = new Student("小张");
        Student s2 = new Student("小李");
        Test3.swap(s1, s2);
        System.out.println("s1:" + s1.getName());
        System.out.println("s2:" + s2.getName());
    }

    public static void swap(Student x, Student y) {
        Student temp = x;
        x = y;
        y = temp;
        System.out.println("x:" + x.getName());
        System.out.println("y:" + y.getName());
    }


}
