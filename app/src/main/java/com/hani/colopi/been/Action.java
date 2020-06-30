package com.hani.colopi.been;

public interface Action {

    default String getName(){
        return "name";
    }
    static int getAge(){
        return 1;
    }
}
