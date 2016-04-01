package com.jang.overcharge.domain;

import java.io.Serializable;

/**
 * Created by Jang on 2016-04-01.
 */
public class TestObj implements Serializable {
    private int a;
    private int b;

    public TestObj(int a, int b ){
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
}
