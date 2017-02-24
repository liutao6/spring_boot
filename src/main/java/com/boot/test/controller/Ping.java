package com.boot.test.controller;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tao on 2017/2/23.
 */
public class Ping implements Serializable {

    private int pingNum;

    private List<Hang> hang;


    public int getPingNum() {
        return pingNum;
    }

    public void setPingNum(int pingNum) {
        this.pingNum = pingNum;
    }

    public List<Hang> getHang() {
        return hang;
    }

    public void setHang(List<Hang> hang) {
        this.hang = hang;
    }
}
