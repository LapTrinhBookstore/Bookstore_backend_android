package com.example.appbookstore.model;

import java.io.Serializable;

public class PopularBook implements Serializable {
    private int stt;
    private String id;
    private String anh;
    private String title;
    private String publisher;
    private int view;
    private int price;

    public PopularBook(int stt, String id, String anh, String title, String publisher, int view, int price) {
        this.stt = stt;
        this.id = id;
        this.anh = anh;
        this.title = title;
        this.publisher = publisher;
        this.view = view;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }
}

