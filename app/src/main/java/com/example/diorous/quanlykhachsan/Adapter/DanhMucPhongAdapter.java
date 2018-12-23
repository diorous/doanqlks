package com.example.diorous.quanlykhachsan.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.diorous.quanlykhachsan.Model.LoaiPhong;
import com.example.diorous.quanlykhachsan.Model.PhieuThuePhong;
import com.example.diorous.quanlykhachsan.R;

import java.util.List;

public class DanhMucPhongAdapter extends RecyclerView.Adapter<DanhMucPhongAdapter.ViewHolder> {
    CallBackDanhMucPhong callBackDanhMucPhong;
    private List<LoaiPhong> loaiPhongs;
    private Context context;

    public DanhMucPhongAdapter(Context context, List<LoaiPhong> loaiPhongs,CallBackDanhMucPhong callBackDanhMucPhong) {
        this.loaiPhongs = loaiPhongs;
        this.context = context;
        this.callBackDanhMucPhong=callBackDanhMucPhong;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_loai_phong, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final LoaiPhong loaiPhong = loaiPhongs.get(position);
        if (loaiPhong != null) {
            holder.tvMaLoai.setText(context.getResources().getString(R.string.txt_ma_loai_phong, String.valueOf(loaiPhong.getMaLoai())));
            holder.tvTenLoai.setText(context.getResources().getString(R.string.txt_ten_loai_phong, loaiPhong.getTenLoai()));
            holder.tvMoTa.setText(context.getResources().getString(R.string.txt_mo_ta, loaiPhong.getMoTa()));
            holder.tvDonGia.setText(context.getResources().getString(R.string.txt_don_gia, String.valueOf(loaiPhong.getDonGia())));
            holder.imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callBackDanhMucPhong.handleEdit(loaiPhong);
                }
            });
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callBackDanhMucPhong.handleDelete(loaiPhong);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return loaiPhongs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaLoai, tvTenLoai, tvMoTa, tvDonGia;
        ImageView imgEdit, imgDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            tvMaLoai = itemView.findViewById(R.id.tvMaLoai);
            tvTenLoai = itemView.findViewById(R.id.tvTenLoai);
            tvMoTa = itemView.findViewById(R.id.tvMoTa);
            tvDonGia = itemView.findViewById(R.id.tvDonGia);
            imgEdit = itemView.findViewById(R.id.imgEdit);
            imgDelete = itemView.findViewById(R.id.imgDelete);
        }

    }

    public interface CallBackDanhMucPhong {
        void handleEdit(LoaiPhong loaiPhong);

        void handleDelete(LoaiPhong loaiPhong);

    }
}
