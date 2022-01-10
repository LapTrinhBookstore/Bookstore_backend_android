package com.example.appbookstore;

public class BookLibrary {
    private String ten;
    private int hinh;
    private String nguoiDang;
    private int hinhTrangThai;

    public BookLibrary(String ten, int hinh, String nguoiDang, int hinhTrangThai) {
        this.ten = ten;
        this.hinh = hinh;
        this.nguoiDang = nguoiDang;
        this.hinhTrangThai = hinhTrangThai;
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

    public int getHinhTrangThai() {
        return hinhTrangThai;
    }

    public void setHinhTrangThai(int hinhTrangThai) {
        this.hinhTrangThai = hinhTrangThai;
    }
}