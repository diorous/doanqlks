package com.example.diorous.quanlykhachsan.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.diorous.quanlykhachsan.Model.LoaiPhong;
import com.example.diorous.quanlykhachsan.Model.Phong;
import com.example.diorous.quanlykhachsan.R;

import java.util.List;

public class QuanLyPhongAdapter extends RecyclerView.Adapter<QuanLyPhongAdapter.ViewHolder> {
    private Context context;
    private List<Phong> phongList;


    public QuanLyPhongAdapter(Context context, List<Phong> phongList) {
        this.context = context;
        this.phongList = phongList;
    }

    public void updateData(List<Phong> phongList) {
        this.phongList = phongList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_quan_ly_phong, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Phong phong = phongList.get(position);
        if (phong != null) {
            holder.tvStt.setText(String.valueOf(position + 1));
            holder.tvTenPhong.setText(phong.getTenPhong());
            LoaiPhong loaiPhong = phong.getLoaiPhong();
            if (loaiPhong != null) {
                holder.tvTenLoaiPhong.setText(loaiPhong.getTenLoai());
            } else {
                holder.tvTenLoaiPhong.setText("");
            }
            holder.tvDonGia.setText(String.valueOf(phong.getDonGia()));
            holder.tvTinhTrang.setText(String.valueOf(phong.getTinhTrang()));
        }
    }

    @Override
    public int getItemCount() {
        return phongList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvStt, tvTenPhong, tvTenLoaiPhong, tvDonGia, tvTinhTrang;

        public ViewHolder(View itemView) {
            super(itemView);
            tvStt = itemView.findViewById(R.id.tvStt);
            tvTenPhong = itemView.findViewById(R.id.tvTenPhong);
            tvTenLoaiPhong = itemView.findViewById(R.id.tvTenLoaiPhong);
            tvDonGia = itemView.findViewById(R.id.tvDonGia);
            tvTinhTrang = itemView.findViewById(R.id.tvTinhTrang);

        }
    }
}
