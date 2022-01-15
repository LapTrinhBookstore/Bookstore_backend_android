package com.example.appbookstore.model;

import java.io.Serializable;

public class HomeBook implements Serializable {
    private int id;
    private String anh;
    private String title;
    private int price;

    public HomeBook(int id, String anh, String title, int price) {
        this.id = id;
        this.anh = anh;
        this.title = title;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
