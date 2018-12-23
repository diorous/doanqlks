package com.example.diorous.quanlykhachsan.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.diorous.quanlykhachsan.R;

public class BaoCaoFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bao_cao_fragment, container, false);
    }

    public static BaoCaoFragment newInstance() {
        
        Bundle args = new Bundle();
        
        BaoCaoFragment fragment = new BaoCaoFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
