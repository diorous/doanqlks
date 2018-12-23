package com.example.diorous.quanlykhachsan.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.diorous.quanlykhachsan.Model.LoaiPhong;
import com.example.diorous.quanlykhachsan.ParamsContants.ParamContans;
import com.example.diorous.quanlykhachsan.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

public class CreateDanhMucPhongActivity extends Activity implements View.OnClickListener {
    private DatabaseReference mDatabase;
    ImageView btnBack;
    EditText edtMaPhong, edtTenLoaiPhong, edtMoTa, edtDonGia;
    private String TAG = "CreateDanhMucPhongActivity";
    private Button btnTaoDanhMucPhong;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tao_danh_muc_phong);
        addViews();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mDatabase = FirebaseDatabase.getInstance().getReference().child(ParamContans.LOAI_PHONG);
    }

    private void addViews() {
        btnTaoDanhMucPhong = findViewById(R.id.btnTaoDanhMucPhong);
        btnTaoDanhMucPhong.setOnClickListener(this);
        btnBack = findViewById(R.id.btnBack);
        edtMaPhong = findViewById(R.id.edtMaPhong);
        edtTenLoaiPhong = findViewById(R.id.edtTenLoaiPhong);
        edtMoTa = findViewById(R.id.edtMoTa);
        edtDonGia = findViewById(R.id.edtDonGia);

    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnTaoDanhMucPhong:
                TaoPhieuThuePhong();
                break;
        }
    }

    private void TaoPhieuThuePhong() {
        String maLoaiPhong = edtMaPhong.getText().toString();
        String tenLoaiPhong = edtTenLoaiPhong.getText().toString();
        String moTa = edtMoTa.getText().toString();
        String donGia = edtDonGia.getText().toString();
        LoaiPhong loaiPhong = new LoaiPhong();
        loaiPhong.setKeyLoaiPhong(Long.valueOf(maLoaiPhong));
        loaiPhong.setDonGia(Long.valueOf(donGia));
        loaiPhong.setTenLoai(tenLoaiPhong);
        loaiPhong.setMoTa(moTa);
        loaiPhong.setMaLoai(Long.valueOf(maLoaiPhong));
        Map<String, Object> phieuThuePhongValues = loaiPhong.toMap();
        Log.d(TAG, "TaoPhieuThuePhong: "+mDatabase.toString());
        mDatabase.child(String.valueOf(loaiPhong.getMaLoai())).setValue(phieuThuePhongValues);
    }


}
