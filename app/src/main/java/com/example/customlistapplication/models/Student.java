package com.example.customlistapplication.models;

public class Student {
    String name = "";
    String id = "";
    boolean flag;

    public Student(String name, String id) {
        this.name = name;
        this.id = id;
        this.flag = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
