package com.example.diorous.quanlykhachsan.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.diorous.quanlykhachsan.Dialog.DialogPhong;
import com.example.diorous.quanlykhachsan.Model.LoaiPhong;
import com.example.diorous.quanlykhachsan.Model.Phong;
import com.example.diorous.quanlykhachsan.R;

import java.util.List;

public class PhongAdapter extends RecyclerView.Adapter<PhongAdapter.ViewHolder> {

    private Context context;
    private List<Phong> phongList;
    private DialogPhong.CallBackChooseRoom chooseRoom;

    public PhongAdapter(Context context, List<Phong> phongList, DialogPhong.CallBackChooseRoom chooseRoom) {
        this.context = context;
        this.phongList = phongList;
        this.chooseRoom = chooseRoom;
    }

    public void updatePhongList(List<Phong> phongList) {
        this.phongList = phongList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PhongAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_phong, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhongAdapter.ViewHolder holder, int position) {
        final Phong phong = phongList.get(position);
        if (phong != null) {
            holder.tvTenPhong.setText(context.getResources().getString(R.string.txt_ten_phong, String.valueOf(phong.getTenPhong())));
            holder.tvTinhTrang.setText(context.getResources().getString(R.string.txt_tinh_trang, String.valueOf(phong.getTinhTrang())));
            LoaiPhong loaiPhong = phong.getLoaiPhong();
            if (loaiPhong != null) {
                holder.tvTenLoaiPhong.setText(context.getResources().getString(R.string.txt_ten_loai_phong, String.valueOf(loaiPhong.getTenLoai())));
            }
            holder.tvDonGia.setText(context.getResources().getString(R.string.txt_gia_tien, String.valueOf(phong.getDonGia())));
            holder.tvPhuThu.setText(context.getResources().getString(R.string.txt_phu_thu, String.valueOf(phong.getPhuThu())));
            holder.tvSoNguoiToiDa.setText(context.getResources().getString(R.string.txt_so_nguoi_toi_da, String.valueOf(phong.getSoNguoi())));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    chooseRoom.getChooseRoom(phong);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return phongList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenPhong, tvTenLoaiPhong, tvDonGia, tvPhuThu, tvSoNguoiToiDa, tvTinhTrang;

        ViewHolder(View itemView) {
            super(itemView);
            tvDonGia = itemView.findViewById(R.id.tvDonGia);
            tvPhuThu = itemView.findViewById(R.id.tvPhuThu);
            tvSoNguoiToiDa = itemView.findViewById(R.id.tvSoNguoiToiDa);
            tvTenLoaiPhong = itemView.findViewById(R.id.tvTenLoaiPhong);
            tvTenPhong = itemView.findViewById(R.id.tvTenPhong);
            tvTinhTrang = itemView.findViewById(R.id.tvTinhTrang);
        }
    }
}
