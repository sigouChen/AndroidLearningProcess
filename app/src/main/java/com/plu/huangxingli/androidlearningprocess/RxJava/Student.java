package com.plu.huangxingli.androidlearningprocess.RxJava;

import android.database.Cursor;

import java.util.List;

/**
 * Created by lily on 16-3-10.
 */
public class Student {

    String name;
    int age;
    List<Courses> mCourses;



    public void setName(String name) {
        this.name = name;
    }

    public void setCourses(List<Courses> mCourses) {
        this.mCourses = mCourses;
    }

    public List<Courses> getCourses() {
        return mCourses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", mCourses=" + mCourses +
                '}';
    }
}
