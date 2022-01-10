package com.example.appbookstore;

public class Book {
    private int stt;
    private String ten;
    private int hinh;
    private String nguoiDang;
    private String luotDoc;
    private int gia;

    public Book(int stt, String ten, int hinh, String nguoiDang, String luotDoc, int gia) {
        this.stt = stt;
        this.ten = ten;
        this.hinh = hinh;
        this.nguoiDang = nguoiDang;
        this.luotDoc = luotDoc;
        this.gia = gia;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }

    public String getNguoiDang() {
        return nguoiDang;
    }

    public void setNguoiDang(String nguoiDang) {
        this.nguoiDang = nguoiDang;
    }

    public String getLuotDoc() {
        return luotDoc;
    }

    public void setLuotDoc(String luotDoc) {
        this.luotDoc = luotDoc;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }
}