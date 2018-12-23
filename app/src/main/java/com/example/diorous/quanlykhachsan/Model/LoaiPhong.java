package com.example.diorous.quanlykhachsan.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class LoaiPhong {

    public static final String KEY_DON_GIA = "DonGia";
    public static final String KEY_MA_LOAI = "MaLoai";
    public static final String KEY_MO_TA = "MoTa";
    public static final String KEY_TEN_LOAI = "TenLoai";

    private long keyLoaiPhong;
    private long donGia;
    private long maLoai;
    private String moTa;
    private String tenLoai;

    public long getKeyLoaiPhong() {
        return keyLoaiPhong;
    }

    public void setKeyLoaiPhong(long keyLoaiPhong) {
        this.keyLoaiPhong = keyLoaiPhong;
    }

    public long getDonGia() {
        return donGia;
    }

    public void setDonGia(long donGia) {
        this.donGia = donGia;
    }

    public long getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(long maLoai) {
        this.maLoai = maLoai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put(KEY_DON_GIA, donGia);
        result.put(KEY_MA_LOAI, maLoai);
        result.put(KEY_MO_TA, moTa);
        result.put(KEY_TEN_LOAI, tenLoai);
        return result;
    }

    @Exclude
    public Map<String, Object> Delete() {
        HashMap<String, Object> result = new HashMap<>();
        result.put(KEY_DON_GIA, null);
        result.put(KEY_MA_LOAI, null);
        result.put(KEY_MO_TA, null);
        result.put(KEY_TEN_LOAI, null);
        return result;
    }
}
