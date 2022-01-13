package com.example.appbookstore.model;

import java.io.Serializable;

public class HomeBook implements Serializable {
    private String anh;
    private String title;
    private int price;

    public HomeBook(String anh, String title, int price) {
        this.anh = anh;
        this.title = title;
        this.price = price;
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
