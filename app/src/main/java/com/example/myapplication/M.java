package com.example.myapplication;


import com.example.annotationinsp.MyLibClass;
import com.example.annotationinsp.annotations.CarefulNow;

public class M {

    @CarefulNow
    public void v() {
        MyLibClass myLibClass = new MyLibClass();
        myLibClass.someDangerousMethod();
    }

    public void u() {
        MyLibClass myLibClass = new MyLibClass();
        v();
    }
}
