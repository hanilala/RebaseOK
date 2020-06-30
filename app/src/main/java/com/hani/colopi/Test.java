package com.hani.colopi;

import java.util.ArrayList;

public class Test {

    public static void main(String[] args) throws Exception {

        ArrayList<String> list = new ArrayList<String>();

        list.add("1");  //这样调用 add 方法只能存储整形，因为泛型类型的实例为 Integer

        list.getClass().getMethod("add", Object.class).invoke(list, 11);

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

}