package com.example.customlistapplication.models;

import java.util.ArrayList;
import java.util.List;

public class Model {

    public static final Model instance = new Model();
    private Model() {
    }

    List<Student> studentList = new ArrayList<>();

    public List<Student> getStudentList() {
        return studentList;
    }

    public void addStudent(Student student) {
        studentList.add(student);
    }
}
