package com.example.diorous.quanlykhachsan.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.diorous.quanlykhachsan.Dialog.DialogLoaiPhong;
import com.example.diorous.quanlykhachsan.Model.LoaiPhong;
import com.example.diorous.quanlykhachsan.R;

import java.util.List;

public class LoaiPhongAdapter extends RecyclerView.Adapter<LoaiPhongAdapter.ViewHolder> {
    private Context context;
    private List<LoaiPhong> loaiPhongList;
    DialogLoaiPhong.CallBackChooseLoaiPhong callBackChooseLoaiPhong;

    public LoaiPhongAdapter(Context context, List<LoaiPhong> loaiPhongList, DialogLoaiPhong.CallBackChooseLoaiPhong callBackChooseLoaiPhong) {
        this.context = context;
        this.callBackChooseLoaiPhong = callBackChooseLoaiPhong;
        this.loaiPhongList = loaiPhongList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_choose_loai_phong,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final LoaiPhong loaiPhong = loaiPhongList.get(position);
        if (loaiPhong != null) {
            holder.tvTenLoaiPhong.setText(context.getResources().getString(R.string.txt_ten_loai_phong, loaiPhong.getTenLoai()));
            holder.tvMoTa.setText(context.getResources().getString(R.string.txt_mo_ta, loaiPhong.getMoTa()));
            holder.tvDonGia.setText(context.getResources().getString(R.string.txt_don_gia, String.valueOf(loaiPhong.getDonGia())));
            holder.tvMaLoai.setText(context.getResources().getString(R.string.txt_ma_loai_phong, String.valueOf(loaiPhong.getMaLoai())));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callBackChooseLoaiPhong.getChooseLoaiPhong(loaiPhong);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return loaiPhongList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenLoaiPhong, tvMoTa, tvDonGia, tvMaLoai;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTenLoaiPhong = itemView.findViewById(R.id.tvTenLoaiPhong);
            tvMoTa = itemView.findViewById(R.id.tvMoTa);
            tvDonGia = itemView.findViewById(R.id.tvDonGia);
            tvMaLoai = itemView.findViewById(R.id.tvMaLoai);
        }
    }
}
