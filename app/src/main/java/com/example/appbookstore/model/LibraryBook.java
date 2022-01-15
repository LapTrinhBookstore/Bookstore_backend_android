package com.example.appbookstore.model;

import java.io.Serializable;

public class LibraryBook implements Serializable {
    private int idProduct;
    private int idUser;
    private String ten;
    private String hinh;
    private String tacGia;

    public LibraryBook(int idProduct, int idUser, String ten, String hinh, String tacGia) {
        this.idProduct = idProduct;
        this.idUser = idUser;
        this.ten = ten;
        this.hinh = hinh;
        this.tacGia = tacGia;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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
}