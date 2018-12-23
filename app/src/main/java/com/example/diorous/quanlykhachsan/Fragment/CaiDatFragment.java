package com.example.diorous.quanlykhachsan.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.diorous.quanlykhachsan.Activity.QuanlyPhongActivity;
import com.example.diorous.quanlykhachsan.R;

public class CaiDatFragment extends Fragment implements View.OnClickListener {

    LinearLayout btnQuanLyPhong;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cai_dat_fragment, container, false);
        btnQuanLyPhong = view.findViewById(R.id.btnQuanLyPhong);
        btnQuanLyPhong.setOnClickListener(this);
        return view;
    }

    public static CaiDatFragment newInstance() {

        Bundle args = new Bundle();

        CaiDatFragment fragment = new CaiDatFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnQuanLyPhong:
                gotoQuanLyPhong();
                break;
        }
    }

    private void gotoQuanLyPhong() {
        Intent intent = new Intent(getContext(),QuanlyPhongActivity.class);
        startActivity(intent);
    }
}
