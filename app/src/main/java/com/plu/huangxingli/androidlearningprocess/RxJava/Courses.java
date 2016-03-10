package com.plu.huangxingli.androidlearningprocess.RxJava;

/**
 * Created by lily on 16-3-10.
 */
public class Courses {
    public String name;
    public int grade;

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Courses{" +
                "name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }
}
