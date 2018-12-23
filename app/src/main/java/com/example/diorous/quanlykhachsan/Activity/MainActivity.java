package com.example.diorous.quanlykhachsan.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.diorous.quanlykhachsan.Fragment.BaoCaoFragment;
import com.example.diorous.quanlykhachsan.Fragment.CaiDatFragment;
import com.example.diorous.quanlykhachsan.Fragment.DanhMucPhongFragment;
import com.example.diorous.quanlykhachsan.Fragment.HoaDonPhongFragment;
import com.example.diorous.quanlykhachsan.Fragment.PhieuThuePhongFragment;
import com.example.diorous.quanlykhachsan.R;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.phieuThuePhong:
                        selectedFragment = PhieuThuePhongFragment.newInstance();
                        break;
                    case R.id.danhMucPhong:
                        selectedFragment = DanhMucPhongFragment.newInstance();
                        break;
                    case R.id.baoCao:
                        selectedFragment = BaoCaoFragment.newInstance();
                        break;
                    case R.id.LapHoaDonThanhToan:
                        selectedFragment = HoaDonPhongFragment.newInstance();
                        break;
                    case R.id.caidat:
                        selectedFragment = CaiDatFragment.newInstance();
                        break;
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.commit();
                return true;
            }
        });
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, PhieuThuePhongFragment.newInstance());
        transaction.commit();
    }


}
