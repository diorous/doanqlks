package com.example.diorous.quanlykhachsan.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.diorous.quanlykhachsan.Activity.CreatePhieuDatPhongActivity;
import com.example.diorous.quanlykhachsan.Adapter.PhieuThuePhongAdapter;
import com.example.diorous.quanlykhachsan.Model.LoaiPhong;
import com.example.diorous.quanlykhachsan.Model.PhieuThuePhong;
import com.example.diorous.quanlykhachsan.Model.Phong;
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

public class PhieuThuePhongFragment extends BaseFragment implements PhieuThuePhongAdapter.CallBackPhieuThuePhong, ChildEventListener {
    private static final String TAG = "PhieuThuePhongFragment";
    private List<PhieuThuePhong> phieuThuePhongList;
    RecyclerView rcvPhieuThuePhong;
    private PhieuThuePhongAdapter thuePhongAdapter;
    private DatabaseReference mDatabase;
    private Button btnAdd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.phieu_thue_phong_fragment, container, false);
        rcvPhieuThuePhong = view.findViewById(R.id.rcvPhieuThuePhong);
        phieuThuePhongList = new ArrayList<>();
        btnAdd = view.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiToiTrangTaophieu();
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rcvPhieuThuePhong.setLayoutManager(layoutManager);
        rcvPhieuThuePhong.setHasFixedSize(true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rcvPhieuThuePhong.getContext(),
                layoutManager.getOrientation());
        rcvPhieuThuePhong.addItemDecoration(dividerItemDecoration);
        setupRecyclerviewIfNeed();

        mDatabase = FirebaseDatabase.getInstance().getReference().child(ParamContans.THUE_PHONG);
        mDatabase.addChildEventListener(this);
        return view;
    }

    private void DiToiTrangTaophieu() {
        Intent intent = new Intent(getContext(), CreatePhieuDatPhongActivity.class);
        startActivityForResult(intent, ParamContans.REQUEST_CREATE_PHIEU_DAT_PHONG);
    }

    public void setupRecyclerviewIfNeed() {
        if (thuePhongAdapter == null) {
            thuePhongAdapter = new PhieuThuePhongAdapter(getContext(), phieuThuePhongList, this);
            rcvPhieuThuePhong.setAdapter(thuePhongAdapter);
            thuePhongAdapter.notifyDataSetChanged();
        } else {
            thuePhongAdapter.updateData(phieuThuePhongList);
        }
    }

    public static PhieuThuePhongFragment newInstance() {
        Bundle args = new Bundle();
        PhieuThuePhongFragment fragment = new PhieuThuePhongFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {

    }

    @Override
    public void initFireBase() {
        super.initFireBase();
    }

    @Override
    public void handleEdit(PhieuThuePhong phieuThuePhong) {
        Log.d(TAG, "handleEdit: " + phieuThuePhong.getMaPhieu());
        phieuThuePhong.setHoTen("Đăng đẹp trai");
        Map<String, Object> phieuThuePhongValues = phieuThuePhong.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(String.valueOf(phieuThuePhong.getMaPhieu()), phieuThuePhongValues);
        mDatabase.updateChildren(childUpdates);
    }

    @Override
    public void handleDelete(PhieuThuePhong phieuThuePhong) {
        Log.d(TAG, "handleEdit: " + phieuThuePhong.getMaPhieu());
        Map<String, Object> phieuThuePhongValues = phieuThuePhong.Delete();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(String.valueOf(phieuThuePhong.getMaPhieu()), phieuThuePhongValues);
        mDatabase.updateChildren(childUpdates);
        //mDatabase.child(String.valueOf(phieuThuePhong.getMaPhieu())).removeValue();
    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String s) {
        try{
            PhieuThuePhong phieuThuePhong = new PhieuThuePhong();
            Log.d(TAG, "onChildAdded: "+snapshot.toString());
            phieuThuePhong.setHoTen((String) snapshot.child(PhieuThuePhong.KEY_HO_TEN).getValue());
            phieuThuePhong.setCmnd((String) snapshot.child(PhieuThuePhong.KEY_CMND).getValue());
            phieuThuePhong.setDiaChi((String) snapshot.child(PhieuThuePhong.KEY_DIA_CHI).getValue());
            phieuThuePhong.setLoaiKhach((long) snapshot.child(PhieuThuePhong.KEY_LOAI_KHACH).getValue());
            phieuThuePhong.setMaPhieu(Integer.parseInt(snapshot.getKey()));
            Phong phong = new Phong();
            phong.setKeyPhong(Long.parseLong(snapshot.getKey()));
            if(snapshot.child(PhieuThuePhong.KEY_PHONG).child(Phong.KEY_DON_GIA).getValue()!=null){
                phong.setDonGia((Long) snapshot.child(PhieuThuePhong.KEY_PHONG).child(Phong.KEY_DON_GIA).getValue());
            }
            phong.setMaPhong((Long) snapshot.child(PhieuThuePhong.KEY_PHONG).child(Phong.KEY_MA_PHONG).getValue());
            phong.setPhuThu((Long) snapshot.child(PhieuThuePhong.KEY_PHONG).child(Phong.KEY_PHU_THU).getValue());
            phong.setSoNguoi((Long) snapshot.child(PhieuThuePhong.KEY_PHONG).child(Phong.KEY_SO_NGUOI).getValue());
            phong.setTinhTrang((Long) snapshot.child(PhieuThuePhong.KEY_PHONG).child(Phong.KEY_TINH_TRANG).getValue());
            phong.setTenPhong(snapshot.child(PhieuThuePhong.KEY_PHONG).child(Phong.KEY_TEN_Phong).getValue().toString());
            Log.d(TAG, "onChildAdded:"+       snapshot.child(PhieuThuePhong.KEY_PHONG).child(ParamContans.LOAI_PHONG).getKey());
            Log.d(TAG, "onChildAdded:"+       snapshot.child(PhieuThuePhong.KEY_PHONG).getValue());

        /*for (DataSnapshot snapLoaiPhong : dataSnapLoaiPhong) {

        }*/
            LoaiPhong loaiPhong = new LoaiPhong();
            //loaiPhong.setKeyLoaiPhong(Long.parseLong(snapshot.child(PhieuThuePhong.KEY_PHONG).child(ParamContans.LOAI_PHONG).getKey()));
            loaiPhong.setDonGia((Long) snapshot.child(PhieuThuePhong.KEY_PHONG).child(ParamContans.LOAI_PHONG).child(LoaiPhong.KEY_DON_GIA).getValue());
            loaiPhong.setMaLoai((Long) snapshot.child(PhieuThuePhong.KEY_PHONG).child(ParamContans.LOAI_PHONG).child(LoaiPhong.KEY_MA_LOAI).getValue());
            loaiPhong.setMoTa((String) snapshot.child(PhieuThuePhong.KEY_PHONG).child(ParamContans.LOAI_PHONG).child(LoaiPhong.KEY_MO_TA).getValue());
            loaiPhong.setTenLoai((String) snapshot.child(PhieuThuePhong.KEY_PHONG).child(ParamContans.LOAI_PHONG).child(LoaiPhong.KEY_TEN_LOAI).getValue());
            phong.setLoaiPhong(loaiPhong);
            phieuThuePhong.setNgayNhan((String) snapshot.child(PhieuThuePhong.KEY_NGAY_NHAN).getValue());
            phieuThuePhong.setNgayTra((String) snapshot.child(PhieuThuePhong.KEY_NGAY_TRA).getValue());
            phieuThuePhong.setKhachNoiDia((long) snapshot.child(PhieuThuePhong.KEY_KHACH_NOI_DIA).getValue());
            phieuThuePhong.setKhachNuocNgoai((long) snapshot.child(PhieuThuePhong.KEY_KHACH_NUOC_NGOAI).getValue());
            phieuThuePhong.setPhong(phong);
            phieuThuePhongList.add(phieuThuePhong);
            thuePhongAdapter.notifyDataSetChanged();
        }catch (Exception e){

        }

    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        phieuThuePhongList.clear();
        mDatabase.removeEventListener(this);
        mDatabase.addChildEventListener(this);

    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
        phieuThuePhongList.clear();
        mDatabase.removeEventListener(this);
        mDatabase.addChildEventListener(this);
    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
