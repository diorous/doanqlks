package com.example.diorous.quanlykhachsan.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.diorous.quanlykhachsan.Dialog.DialogLoaiPhong;
import com.example.diorous.quanlykhachsan.Model.LoaiPhong;
import com.example.diorous.quanlykhachsan.Model.Phong;
import com.example.diorous.quanlykhachsan.ParamsContants.ParamContans;
import com.example.diorous.quanlykhachsan.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

class CreatePhongActivity extends Activity implements View.OnClickListener, DialogLoaiPhong.CallBackChooseLoaiPhong, ChildEventListener {
    private ImageView btnBack;
    private EditText edtTenPhong;
    private TextView tvAddLoaiPhong, edtSoNguoi, edtPhuThu, edtDonGia;
    private RadioButton rdClose, rdOpen;
    private List<LoaiPhong> loaiPhongs;
    private LoaiPhong chooseLoaiPhong;
    private DatabaseReference mDatabaseLoaiPhong, mDataBasePhong;
    private Button btnTaoPhong;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_phong_activity);
        loaiPhongs = new ArrayList<>();
        mDataBasePhong = FirebaseDatabase.getInstance().getReference().child(ParamContans.PHONG);
        mDatabaseLoaiPhong = FirebaseDatabase.getInstance().getReference().child(ParamContans.LOAI_PHONG);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        edtTenPhong = findViewById(R.id.edtTenPhong);
        tvAddLoaiPhong = findViewById(R.id.tvAddLoaiPhong);
        tvAddLoaiPhong.setOnClickListener(this);
        edtSoNguoi = findViewById(R.id.edtSoNguoi);
        edtPhuThu = findViewById(R.id.edtPhuThu);
        edtDonGia = findViewById(R.id.edtDonGia);
        rdClose = findViewById(R.id.rdClose);
        rdOpen = findViewById(R.id.rdOpen);
        btnTaoPhong = findViewById(R.id.btnTaoPhong);
        btnTaoPhong.setOnClickListener(this);
        mDatabaseLoaiPhong.addChildEventListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnBack:
                onBackPressed();
                break;
            case R.id.tvAddLoaiPhong:
                DialogLoaiPhong.getInstance().show(CreatePhongActivity.this, loaiPhongs, CreatePhongActivity.this);
                break;
            case R.id.btnTaoPhong:
                createPhong();
                break;
        }
    }

    private void createPhong() {
        try {
            String tenPhong = edtTenPhong.getText().toString();
            String soNguoi = edtSoNguoi.getText().toString();
            String phuThu = edtPhuThu.getText().toString();
            String donGia = edtDonGia.getText().toString();
            int trangThai;
            if (rdOpen.isChecked()) {
                trangThai = 1;
            } else {
                trangThai = 0;
            }
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss", Locale.ENGLISH);
            String maPhong = sdf.format(date);
            Phong phong = new Phong();
            phong.setPhuThu(Long.parseLong(phuThu));
            phong.setTinhTrang(Long.parseLong(String.valueOf(trangThai)));
            phong.setMaPhong(Long.parseLong(maPhong));
            phong.setDonGia(Long.parseLong(donGia));
            phong.setKeyPhong(Long.parseLong(maPhong));
            phong.setSoNguoi(Long.parseLong(soNguoi));
            phong.setTenPhong(tenPhong);
            Map<String, Object> phongValues = phong.toMap();
            mDataBasePhong.child(maPhong).setValue(phongValues);
            if (chooseLoaiPhong != null) {
                mDataBasePhong.child(maPhong).child(ParamContans.LOAI_PHONG).setValue(chooseLoaiPhong.toMap());
            }
        } catch (Exception e) {
            Log.e("error", "createPhong: " + e.getMessage());
        }

    }

    @Override
    public void getChooseLoaiPhong(LoaiPhong loaiPhong) {
        if (loaiPhong != null) {
            chooseLoaiPhong = loaiPhong;
            tvAddLoaiPhong.setText(loaiPhong.getTenLoai());
        } else {
            chooseLoaiPhong = null;
            tvAddLoaiPhong.setText("+ Thêm loại phòng");
        }
        DialogLoaiPhong.getInstance().dismiss();
    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot snapLoaiPhong, @Nullable String s) {
        LoaiPhong loaiPhong = new LoaiPhong();
        loaiPhong.setKeyLoaiPhong(Long.parseLong(snapLoaiPhong.getKey()));
        loaiPhong.setDonGia((Long) snapLoaiPhong.child(LoaiPhong.KEY_DON_GIA).getValue());
        loaiPhong.setMaLoai((Long) snapLoaiPhong.child(LoaiPhong.KEY_MA_LOAI).getValue());
        loaiPhong.setMoTa((String) snapLoaiPhong.child(LoaiPhong.KEY_MO_TA).getValue());
        loaiPhong.setTenLoai((String) snapLoaiPhong.child(LoaiPhong.KEY_TEN_LOAI).getValue());
        loaiPhongs.add(loaiPhong);
    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
