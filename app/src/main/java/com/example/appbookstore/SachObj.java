package com.example.appbookstore;

public class SachObj {
    private int biaSach;
    private String tenSach, tacGia, loaiSach;
    private float rating;
    private double giaBan;

    public SachObj(int biaSach, String tenSach, String tacGia, String loaiSach, float rating, double giaBan) {
        this.biaSach = biaSach;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.loaiSach = loaiSach;
        this.rating = rating;
        this.giaBan = giaBan;
    }

    public SachObj(SachObj sachObj) {
        this.biaSach = sachObj.getBiaSach();
        this.tenSach = sachObj.getTenSach();
        this.tacGia = sachObj.getTacGia();
        this.loaiSach = sachObj.getLoaiSach();
        this.rating = sachObj.getRating();
        this.giaBan = sachObj.getGiaBan();
    }


    public int getBiaSach() {
        return biaSach;
    }

    public void setBiaSach(int biaSach) {
        this.biaSach = biaSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getLoaiSach() {
        return loaiSach;
    }

    public void setLoaiSach(String loaiSach) {
        this.loaiSach = loaiSach;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }
}