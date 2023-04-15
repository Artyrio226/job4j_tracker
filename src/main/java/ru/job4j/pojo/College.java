package ru.job4j.pojo;

import java.util.Date;

public class College {
    public static void main(String[] args) {
        Student student = new Student();
        student.setFullName("Voronkov Nikita Denisovich");
        student.setGroup("AM201");
        student.setEntered(new Date());
        System.out.println("Name: " + student.getFullName()
                           + ", Group: " + student.getGroup()
                           + ", Entered: " + student.getEntered());
    }
}
