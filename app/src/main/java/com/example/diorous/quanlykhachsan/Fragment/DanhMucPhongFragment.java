package com.example.diorous.quanlykhachsan.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.diorous.quanlykhachsan.Activity.CreateDanhMucPhongActivity;
import com.example.diorous.quanlykhachsan.Adapter.DanhMucPhongAdapter;
import com.example.diorous.quanlykhachsan.Model.LoaiPhong;
import com.example.diorous.quanlykhachsan.ParamsContants.ParamContans;
import com.example.diorous.quanlykhachsan.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DanhMucPhongFragment extends Fragment implements ChildEventListener, View.OnClickListener, DanhMucPhongAdapter.CallBackDanhMucPhong {
    RecyclerView rcvDanhMucPhong;
    private DatabaseReference mDatabase;
    private static final String TAG = "DanhMucPhongFragment";
    private List<LoaiPhong> loaiPhongs;
    DanhMucPhongAdapter danhMucPhongAdapter;
    private Button btnTaoDanhMucPhong;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.danh_muc_phong_fragment, container, false);
        rcvDanhMucPhong = view.findViewById(R.id.rcvDanhMucPhong);
        btnTaoDanhMucPhong = view.findViewById(R.id.btnTaoDanhMucPhong);
        btnTaoDanhMucPhong.setOnClickListener(this);
        rcvDanhMucPhong.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rcvDanhMucPhong.setHasFixedSize(true);
        loaiPhongs = new ArrayList<>();
        danhMucPhongAdapter = new DanhMucPhongAdapter(getContext(), loaiPhongs,this);
        rcvDanhMucPhong.setAdapter(danhMucPhongAdapter);
        mDatabase = FirebaseDatabase.getInstance().getReference().child(ParamContans.LOAI_PHONG);
        mDatabase.addChildEventListener(this);

        return view;
    }

    public static DanhMucPhongFragment newInstance() {

        Bundle args = new Bundle();

        DanhMucPhongFragment fragment = new DanhMucPhongFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot snapLoaiPhong, @Nullable String s) {
        try {
            Log.d(TAG, "onChildAdded: " + snapLoaiPhong.toString());
            LoaiPhong loaiPhong = new LoaiPhong();
            loaiPhong.setKeyLoaiPhong(Long.parseLong(snapLoaiPhong.getKey()));
            loaiPhong.setDonGia((Long) snapLoaiPhong.child(LoaiPhong.KEY_DON_GIA).getValue());
            loaiPhong.setMaLoai((Long) snapLoaiPhong.child(LoaiPhong.KEY_MA_LOAI).getValue());
            loaiPhong.setMoTa((String) snapLoaiPhong.child(LoaiPhong.KEY_MO_TA).getValue());
            loaiPhong.setTenLoai((String) snapLoaiPhong.child(LoaiPhong.KEY_TEN_LOAI).getValue());
            loaiPhongs.add(loaiPhong);
            danhMucPhongAdapter.notifyDataSetChanged();
        } catch (Exception e) {

        }
    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        loaiPhongs.clear();
        mDatabase.removeEventListener(this);
        mDatabase.addChildEventListener(this);
    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
        loaiPhongs.clear();
        mDatabase.removeEventListener(this);
        mDatabase.addChildEventListener(this);
    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        switch (id){
            case R.id.btnTaoDanhMucPhong:
                gotoCreateDanhMucPhong();
                break;
        }
    }

    private void gotoCreateDanhMucPhong() {
        Intent intent=new Intent(getContext(), CreateDanhMucPhongActivity.class);
        startActivity(intent);
    }

    @Override
    public void handleEdit(LoaiPhong loaiPhong) {
        Log.d(TAG, "handleEdit: " + loaiPhong.getKeyLoaiPhong());
        loaiPhong.setTenLoai("Phòng trung");
        Map<String, Object> phieuThuePhongValues = loaiPhong.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(String.valueOf(loaiPhong.getKeyLoaiPhong()), phieuThuePhongValues);
        mDatabase.updateChildren(childUpdates);
    }

    @Override
    public void handleDelete(LoaiPhong loaiPhong) {
        Log.d(TAG, "handleEdit: " + loaiPhong.getKeyLoaiPhong());
        Map<String, Object> loaiPhongValues = loaiPhong.Delete();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(String.valueOf(loaiPhong.getKeyLoaiPhong()), loaiPhongValues);
        mDatabase.updateChildren(childUpdates);
    }
}
