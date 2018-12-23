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

import com.example.diorous.quanlykhachsan.Adapter.PhongAdapter;
import com.example.diorous.quanlykhachsan.Model.Phong;
import com.example.diorous.quanlykhachsan.R;

import java.util.List;

public class DialogPhong {
    private static DialogPhong Instance = null;
    private Dialog dialog;
    public static DialogPhong getInstance() {
        if (Instance == null) {
            Instance = new DialogPhong();
        }
        return Instance;
    }

    public void show(Context context, List<Phong> phongList,CallBackChooseRoom chooseRoom) {
        if (context != null && context instanceof Activity && !((Activity) context).isFinishing()) {
            dialog = new Dialog(context, R.style.dialog_full_transparent_background);
            dialog.setOwnerActivity((Activity) context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_choose_room);
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
            RecyclerView rcvPhong = dialog.findViewById(R.id.rcvPhong);
            rcvPhong.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
            rcvPhong.setHasFixedSize(true);
            if (phongList != null) {
                PhongAdapter phongAdapter = new PhongAdapter(context, phongList,chooseRoom);
                rcvPhong.setAdapter(phongAdapter);
                phongAdapter.notifyDataSetChanged();
            }
        }
    }

    public void dismiss(){
        if(dialog.isShowing()){
            dialog.dismiss();
        }
    }


    public interface CallBackChooseRoom {
        void getChooseRoom(Phong phong);
    }
}
