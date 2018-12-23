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
import android.widget.TextView;

import com.example.diorous.quanlykhachsan.Dialog.DialogPhong;
import com.example.diorous.quanlykhachsan.Model.LoaiPhong;
import com.example.diorous.quanlykhachsan.Model.PhieuThuePhong;
import com.example.diorous.quanlykhachsan.Model.Phong;
import com.example.diorous.quanlykhachsan.ParamsContants.ParamContans;
import com.example.diorous.quanlykhachsan.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreatePhieuDatPhongActivity extends Activity implements ValueEventListener, View.OnClickListener, DialogPhong.CallBackChooseRoom {
    private List<Phong> phongList;
    private DatabaseReference mDatabase, mDatabasePhieuThuePhong;
    ImageView btnBack;
    EditText edtName, edtCMND, edtDiaChi, edtkhachNoiDia, edtKhachNuocNgoai, edtNgayTraPhong, edtNgayNhanPhong;
    private String TAG = "CreatePhieuDatPhongActivity";
    private TextView btnChooseRoom;
    private Phong chooseRoom;
    private Button btnTaoPhieuDatPhong;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tao_phieu_dat_phong);
        addViews();
        phongList = new ArrayList<>();

        btnChooseRoom.setOnClickListener(this);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mDatabase = FirebaseDatabase.getInstance().getReference().child(ParamContans.PHONG);
        mDatabase.addValueEventListener(this);
        mDatabasePhieuThuePhong = FirebaseDatabase.getInstance().getReference().child(ParamContans.THUE_PHONG);
    }

    private void addViews() {
        btnTaoPhieuDatPhong = findViewById(R.id.btnTaoPhieuDatPhong);
        btnTaoPhieuDatPhong.setOnClickListener(this);
        btnBack = findViewById(R.id.btnBack);
        btnChooseRoom = findViewById(R.id.btnChooseRoom);
        edtName = findViewById(R.id.edtName);
        edtCMND = findViewById(R.id.edtCMND);
        edtDiaChi = findViewById(R.id.edtDiaChi);
        edtkhachNoiDia = findViewById(R.id.edtkhachNoiDia);
        edtKhachNuocNgoai = findViewById(R.id.edtKhachNuocNgoai);
        edtNgayTraPhong = findViewById(R.id.edtNgayTraPhong);
        edtNgayNhanPhong = findViewById(R.id.edtNgayNhanPhong);
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        try {
            phongList.clear();
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                Phong phong = new Phong();
                phong.setKeyPhong(Long.parseLong(snapshot.getKey()));
                phong.setDonGia((Long) snapshot.child(Phong.KEY_DON_GIA).getValue());
                phong.setMaPhong((Long) snapshot.child(Phong.KEY_MA_PHONG).getValue());
                phong.setPhuThu((Long) snapshot.child(Phong.KEY_PHU_THU).getValue());
                phong.setSoNguoi((Long) snapshot.child(Phong.KEY_SO_NGUOI).getValue());
                phong.setTinhTrang((Long) snapshot.child(Phong.KEY_TINH_TRANG).getValue());
                phong.setTenPhong(snapshot.child(Phong.KEY_TEN_Phong).getValue().toString());
                Iterable<DataSnapshot> dataSnapLoaiPhong = snapshot.child(ParamContans.LOAI_PHONG).getChildren();
                for (DataSnapshot snapLoaiPhong : dataSnapLoaiPhong) {
                    LoaiPhong loaiPhong = new LoaiPhong();
                    loaiPhong.setKeyLoaiPhong(Long.parseLong(snapLoaiPhong.getKey()));
                    loaiPhong.setDonGia((Long) snapLoaiPhong.child(LoaiPhong.KEY_DON_GIA).getValue());
                    loaiPhong.setMaLoai((Long) snapLoaiPhong.child(LoaiPhong.KEY_MA_LOAI).getValue());
                    loaiPhong.setMoTa((String) snapLoaiPhong.child(LoaiPhong.KEY_MO_TA).getValue());
                    loaiPhong.setTenLoai((String) snapLoaiPhong.child(LoaiPhong.KEY_TEN_LOAI).getValue());
                    phong.setLoaiPhong(loaiPhong);
                }
                phongList.add(phong);
            }
        }catch (Exception e){
            Log.e(TAG, "onDataChange: "+e.getMessage() );
        }

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnChooseRoom:
                showDialogRoom();
                break;
            case R.id.btnTaoPhieuDatPhong:
                TaoPhieuThuePhong();
                break;
        }
    }

    private void TaoPhieuThuePhong() {
        String hoten = edtName.getText().toString();
        String cmnd = edtCMND.getText().toString();
        String diachi = edtDiaChi.getText().toString();
        String khachNoiDia = edtkhachNoiDia.getText().toString();
        String khachNuocNgoai = edtKhachNuocNgoai.getText().toString();
        String ngayNhan = edtNgayNhanPhong.getText().toString();
        String ngayTra = edtNgayTraPhong.getText().toString();
        PhieuThuePhong phieuThuePhong = new PhieuThuePhong();
        phieuThuePhong.setKhachNuocNgoai(Long.valueOf(khachNuocNgoai));
        phieuThuePhong.setKhachNoiDia(Long.valueOf(khachNoiDia));
        phieuThuePhong.setNgayTra(ngayTra);
        phieuThuePhong.setNgayNhan(ngayNhan);
        phieuThuePhong.setMaPhieu(2);
        phieuThuePhong.setDiaChi(diachi);
        phieuThuePhong.setCmnd(cmnd);
        phieuThuePhong.setHoTen(hoten);
        phieuThuePhong.setLoaiKhach(5);
        Map<String, Object> phieuThuePhongValues = phieuThuePhong.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(String.valueOf(phieuThuePhong.getMaPhieu()), phieuThuePhongValues);
        mDatabasePhieuThuePhong.child(String.valueOf(phieuThuePhong.getMaPhieu())).setValue(phieuThuePhongValues);
        mDatabasePhieuThuePhong.child(String.valueOf(phieuThuePhong.getMaPhieu())).child(ParamContans.PHONG).setValue(chooseRoom.toMap());
        mDatabasePhieuThuePhong.child(String.valueOf(phieuThuePhong.getMaPhieu())).child(ParamContans.PHONG).child(ParamContans.LOAI_PHONG).setValue(chooseRoom.getLoaiPhong().toMap());
    }

    private void showDialogRoom() {
        DialogPhong.getInstance().show(this, phongList, this);
    }

    @Override
    public void getChooseRoom(Phong phong) {
        chooseRoom = phong;
        if (chooseRoom != null) {
            btnChooseRoom.setText(chooseRoom.getTenPhong());
        }
    }
}
