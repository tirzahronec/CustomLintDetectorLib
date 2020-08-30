package com.example.lintrules;

import com.example.lintrules.annotations.CarefulNow;

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
