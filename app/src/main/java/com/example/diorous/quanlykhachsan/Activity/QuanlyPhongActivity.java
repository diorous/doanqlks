package com.example.diorous.quanlykhachsan.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.diorous.quanlykhachsan.Adapter.QuanLyPhongAdapter;
import com.example.diorous.quanlykhachsan.Model.LoaiPhong;
import com.example.diorous.quanlykhachsan.Model.Phong;
import com.example.diorous.quanlykhachsan.ParamsContants.ParamContans;
import com.example.diorous.quanlykhachsan.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class QuanlyPhongActivity extends Activity implements ChildEventListener, View.OnClickListener {

    private static final String TAG = "QuanlyPhongActivity";
    private DatabaseReference mDatabase;
    RecyclerView rcvPhong;
    private List<Phong> phongList;
    private QuanLyPhongAdapter quanLyPhongAdapter;
    private TextView tvTaoPhong;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quan_ly_phong_activity);
        phongList = new ArrayList<>();
        rcvPhong = findViewById(R.id.rcvPhong);
        tvTaoPhong = findViewById(R.id.tvTaoPhong);
        tvTaoPhong.setOnClickListener(this);
        rcvPhong.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcvPhong.setHasFixedSize(true);
        quanLyPhongAdapter = new QuanLyPhongAdapter(this, phongList);
        rcvPhong.setAdapter(quanLyPhongAdapter);
        mDatabase = FirebaseDatabase.getInstance().getReference().child(ParamContans.PHONG);
        mDatabase.addChildEventListener(this);
        Log.d(TAG, "onCreate: " + mDatabase.toString());
    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String s) {
        try{
            Phong phong = new Phong();
            phong.setKeyPhong(Long.parseLong(snapshot.getKey()));
            phong.setDonGia((Long) snapshot.child(Phong.KEY_DON_GIA).getValue());
            phong.setMaPhong((Long) snapshot.child(Phong.KEY_MA_PHONG).getValue());
            phong.setPhuThu((Long) snapshot.child(Phong.KEY_PHU_THU).getValue());
            phong.setSoNguoi((Long) snapshot.child(Phong.KEY_SO_NGUOI).getValue());
            phong.setTinhTrang((Long) snapshot.child(Phong.KEY_TINH_TRANG).getValue());
            phong.setTenPhong(snapshot.child(Phong.KEY_TEN_Phong).getValue().toString());
            LoaiPhong loaiPhong = new LoaiPhong();
            Log.d(TAG, "onChildAdded: "+ snapshot.child(ParamContans.LOAI_PHONG).child(LoaiPhong.KEY_DON_GIA).toString());
            loaiPhong.setDonGia((Long) snapshot.child(ParamContans.LOAI_PHONG).child(LoaiPhong.KEY_DON_GIA).getValue());
            loaiPhong.setMaLoai((Long) snapshot.child(ParamContans.LOAI_PHONG).child(LoaiPhong.KEY_MA_LOAI).getValue());
            loaiPhong.setMoTa((String) snapshot.child(ParamContans.LOAI_PHONG).child(LoaiPhong.KEY_MO_TA).getValue());
            loaiPhong.setTenLoai((String) snapshot.child(ParamContans.LOAI_PHONG).child(LoaiPhong.KEY_TEN_LOAI).getValue());
            phong.setLoaiPhong(loaiPhong);
            phongList.add(phong);
            Log.d(TAG, "onChildAdded: " + phongList.size());
            quanLyPhongAdapter.updateData(phongList);
            rcvPhong.setAdapter(quanLyPhongAdapter);
        }catch (Exception e){

        }

    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        Log.d(TAG, "onChildChanged: " + dataSnapshot.toString());

    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
        Log.d(TAG, "onChildRemoved: " + dataSnapshot.toString());

    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        Log.d(TAG, "onChildMoved: " + dataSnapshot.toString());

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        switch (id){
            case R.id.tvTaoPhong:
                gotoTaoPhong();
                break;
        }
    }

    private void gotoTaoPhong() {
        Intent intent=new Intent(QuanlyPhongActivity.this,CreatePhongActivity.class);
        startActivity(intent);
    }
}
