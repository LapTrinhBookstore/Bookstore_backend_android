package com.example.appbookstore.model;

import java.io.Serializable;

public class BookLibrary implements Serializable {
    private String productID;
    private String userID;
    private String ten;
    private String hinh;
    private String tacGia;
    private int status;

    public BookLibrary(String productID, String userID, String ten, String hinh, String tacGia, int status) {
        this.productID = productID;
        this.userID = userID;
        this.ten = ten;
        this.hinh = hinh;
        this.tacGia = tacGia;
        this.status = status;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}