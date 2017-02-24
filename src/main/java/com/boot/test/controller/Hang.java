package com.boot.test.controller;

import java.util.List;

/**
 * Created by tao on 2017/2/23.
 */
public class Hang {

    private int hangNum;

    private List<Lie> lie;

    public int getHangNum() {
        return hangNum;
    }

    public void setHangNum(int hangNum) {
        this.hangNum = hangNum;
    }

    public List<Lie> getLie() {
        return lie;
    }

    public void setLie(List<Lie> lie) {
        this.lie = lie;
    }
}
