package com.sdyin.guava.demo.test;


import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Author liuye
 * @Date 2019/10/30 17:14
 **/
public class Test2 {

    public static void main(String[] args) {
        List<String> listCatalog = new ArrayList<>();
        List<String> result = new ArrayList<>();
        listCatalog.add("1");
        listCatalog.add("0");
        listCatalog.add("2");
        String[] handleStatusArray = new String[]{"1","2","0"};
        for (int i = 0; i < handleStatusArray.length; i++) {
            String s = handleStatusArray[i];
            for (int j = 0; j < listCatalog.size(); j++) {
                String a = listCatalog.get(j);
                if(s.equals(a)){
                    result.add(a);
                }
            }
        }
        System.out.println("result:" + JSON.toJSONString(result));
    }
}
