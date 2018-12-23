package com.example.diorous.quanlykhachsan.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Phong {
    public static final String KEY_DON_GIA = "DonGia";
    public static final String KEY_MA_PHONG = "MaPhong";
    public static final String KEY_PHU_THU = "PhuThu";
    public static final String KEY_SO_NGUOI = "SoNguoi";
    public static final String KEY_TINH_TRANG = "TinhTrang";
    public static final String KEY_TEN_Phong ="TenPhong";
    private long keyPhong;
    private long donGia;
    private long maPhong;
    private long phuThu;
    private long soNguoi;
    private long tinhTrang;
    private LoaiPhong loaiPhong;
    private String tenPhong;

    public Phong() {
        loaiPhong = new LoaiPhong();
    }

    public long getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(long tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public long getSoNguoi() {
        return soNguoi;
    }

    public void setSoNguoi(long soNguoi) {
        this.soNguoi = soNguoi;
    }

    public long getPhuThu() {
        return phuThu;
    }

    public void setPhuThu(long phuThu) {
        this.phuThu = phuThu;
    }

    public long getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(long maPhong) {
        this.maPhong = maPhong;
    }

    public long getDonGia() {
        return donGia;
    }

    public void setDonGia(long donGia) {
        this.donGia = donGia;
    }

    public long getKeyPhong() {
        return keyPhong;
    }

    public void setKeyPhong(long keyPhong) {
        this.keyPhong = keyPhong;
    }

    public LoaiPhong getLoaiPhong() {
        return loaiPhong;
    }

    public void setLoaiPhong(LoaiPhong loaiPhong) {
        this.loaiPhong = loaiPhong;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put(KEY_DON_GIA, donGia);
        result.put(KEY_MA_PHONG, maPhong);
        result.put(KEY_PHU_THU, phuThu);
        result.put(KEY_SO_NGUOI, soNguoi);
        result.put(KEY_TINH_TRANG, tinhTrang);
        result.put(KEY_TEN_Phong, tenPhong);
        return result;
    }
}
