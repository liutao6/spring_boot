package com.boot.test.controller;

/**
 * Created by tao on 2017/2/23.
 */
public class Lie {

    private int ping;

    private int hang;

    private int lie;

    private String name;

    public Lie(int ping, int hang, int lie, String name) {
        this.ping = ping;
        this.hang = hang;
        this.lie = lie;
        this.name = name;
    }

    public int getPing() {
        return ping;
    }

    public void setPing(int ping) {
        this.ping = ping;
    }

    public int getHang() {
        return hang;
    }

    public void setHang(int hang) {
        this.hang = hang;
    }

    public int getLie() {
        return lie;
    }

    public void setLie(int lie) {
        this.lie = lie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
