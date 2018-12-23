package com.example.diorous.quanlykhachsan.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.diorous.quanlykhachsan.Adapter.LoaiPhongAdapter;
import com.example.diorous.quanlykhachsan.Model.LoaiPhong;
import com.example.diorous.quanlykhachsan.R;

import java.util.List;

public class DialogLoaiPhong {
    private static DialogLoaiPhong Instance = null;
    private Dialog dialog;

    public static DialogLoaiPhong getInstance() {
        if (Instance == null) {
            Instance = new DialogLoaiPhong();
        }
        return Instance;
    }

    public void show(Context context, List<LoaiPhong> loaiPhongList, CallBackChooseLoaiPhong callBackChooseLoaiPhong) {
        if (context != null && context instanceof Activity && !((Activity) context).isFinishing()) {
            dialog = new Dialog(context, R.style.dialog_full_transparent_background);
            dialog.setOwnerActivity((Activity) context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_choose_loai_phong);
            Window window = dialog.getWindow();
            if (window != null) {
                WindowManager.LayoutParams wlp = window.getAttributes();
                wlp.gravity = Gravity.CENTER;
                window.setAttributes(wlp);
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                dialog.show();
            }
            View container = dialog.findViewById(R.id.container);
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            RecyclerView rcvLoaiPhong = dialog.findViewById(R.id.rcvLoaiPhong);
            rcvLoaiPhong.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
            rcvLoaiPhong.setHasFixedSize(true);
            if (rcvLoaiPhong != null) {
                LoaiPhongAdapter loaiPhongAdapter = new LoaiPhongAdapter(context, loaiPhongList, callBackChooseLoaiPhong);
                rcvLoaiPhong.setAdapter(loaiPhongAdapter);
                loaiPhongAdapter.notifyDataSetChanged();
            }
        }
    }

    public void dismiss() {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }


    public interface CallBackChooseLoaiPhong {
        void getChooseLoaiPhong(LoaiPhong loaiPhong);
    }
}
