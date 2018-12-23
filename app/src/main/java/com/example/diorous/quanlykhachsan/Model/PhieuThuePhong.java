package com.example.diorous.quanlykhachsan.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class PhieuThuePhong {
    public static final String KEY_HO_TEN = "Hoten";
    public static final String KEY_CMND = "CMND";
    public static final String KEY_DIA_CHI = "DiaChi";
    public static final String KEY_LOAI_KHACH = "LoaiKhach";
    public static final String KEY_PHONG = "Phong";
    public static final String KEY_NGAY_NHAN = "NgayNhan";
    public static final String KEY_NGAY_TRA = "NgayTra";
    public static final String KEY_KHACH_NOI_DIA = "KhachNoiDia";
    public static final String KEY_KHACH_NUOC_NGOAI = "KhachNuocNgoai";

    private int maPhieu;
    private String cmnd;
    private String diaChi;
    private String hoTen;
    private long loaiKhach;
    private Phong phong;
    private long khachNoiDia;
    private long khachNuocNgoai;
    private String ngayNhan;
    private String ngayTra;

    public int getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(int maPhieu) {
        this.maPhieu = maPhieu;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public long getLoaiKhach() {
        return loaiKhach;
    }

    public void setLoaiKhach(long loaiKhach) {
        this.loaiKhach = loaiKhach;
    }


    public String getNgayNhan() {
        return ngayNhan;
    }

    public void setNgayNhan(String ngayNhan) {
        this.ngayNhan = ngayNhan;
    }

    public String getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(String ngayTra) {
        this.ngayTra = ngayTra;
    }


    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put(KEY_HO_TEN, hoTen);
        result.put(KEY_CMND, cmnd);
        result.put(KEY_DIA_CHI, diaChi);
        result.put(KEY_LOAI_KHACH, loaiKhach);
        result.put(KEY_PHONG, phong);
        result.put(KEY_NGAY_NHAN, ngayNhan);
        result.put(KEY_NGAY_TRA, ngayTra);
        result.put(KEY_KHACH_NOI_DIA, khachNoiDia);
        result.put(KEY_KHACH_NUOC_NGOAI, khachNuocNgoai);

        return result;
    }

    @Exclude
    public Map<String, Object> Delete() {
        HashMap<String, Object> result = new HashMap<>();
        result.put(KEY_HO_TEN, null);
        result.put(KEY_CMND, null);
        result.put(KEY_DIA_CHI, null);
        result.put(KEY_LOAI_KHACH, null);
        result.put(KEY_PHONG, null);
        result.put(KEY_NGAY_NHAN, null);
        result.put(KEY_NGAY_TRA, null);
        result.put(KEY_KHACH_NOI_DIA, null);
        result.put(KEY_KHACH_NUOC_NGOAI, null);
        return result;
    }

    public long getKhachNoiDia() {
        return khachNoiDia;
    }

    public void setKhachNoiDia(long khachNoiDia) {
        this.khachNoiDia = khachNoiDia;
    }

    public long getKhachNuocNgoai() {
        return khachNuocNgoai;
    }

    public void setKhachNuocNgoai(long khachNuocNgoai) {
        this.khachNuocNgoai = khachNuocNgoai;
    }

    public Phong getPhong() {
        return phong;
    }

    public void setPhong(Phong phong) {
        this.phong = phong;
    }
}
