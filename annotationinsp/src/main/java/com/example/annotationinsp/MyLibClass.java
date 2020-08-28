package com.example.annotationinsp;

import com.example.annotationinsp.annotations.CarefulNow;

public class MyLibClass {

    @CarefulNow
    public void someDangerousMethod() {
        // do nothing
    }

    public void someDangerousMethodc() {
        // do nothing
        someDangerousMethod();
    }
}
