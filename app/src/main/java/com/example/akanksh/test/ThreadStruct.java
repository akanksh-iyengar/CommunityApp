package com.example.akanksh.test;

/**
 * Created by AKANKSH on 12/17/2017.
 */

public class ThreadStruct {
    public String name;
    public String desc;
    ThreadStruct(){}
    ThreadStruct(String name,String desc){
        this.name=name;
        this.desc=desc;

    }
    public String getDesc() {
        return desc;
    }

    public void setText(String text) {
        this.desc = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
