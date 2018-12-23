package com.example.diorous.quanlykhachsan.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.diorous.quanlykhachsan.Model.PhieuThuePhong;
import com.example.diorous.quanlykhachsan.R;

import java.util.List;

public class PhieuThuePhongAdapter extends RecyclerView.Adapter<PhieuThuePhongAdapter.ViewHolder> {
    private Context context;
    private List<PhieuThuePhong> phieuThuePhongs;
    private CallBackPhieuThuePhong callBackPhieuThuePhong;

    public PhieuThuePhongAdapter(Context context, List<PhieuThuePhong> phieuThuePhongs, CallBackPhieuThuePhong callBackPhieuThuePhong) {
        this.context = context;
        this.phieuThuePhongs = phieuThuePhongs;
        this.callBackPhieuThuePhong = callBackPhieuThuePhong;
    }

    public void updateData(List<PhieuThuePhong> phieuThuePhongs) {
        this.phieuThuePhongs = phieuThuePhongs;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PhieuThuePhongAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_phieu_thue_phong, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhieuThuePhongAdapter.ViewHolder holder, int position) {
        final PhieuThuePhong phieuThuePhong = phieuThuePhongs.get(position);
        if (phieuThuePhong != null) {
            holder.tvCMND.setText(context.getResources().getString(R.string.txt_cmnd, phieuThuePhong.getCmnd()));
            holder.tvHoTen.setText(context.getResources().getString(R.string.txt_ho_ten, phieuThuePhong.getHoTen()));
            holder.tvNgayNhan.setText(context.getResources().getString(R.string.txt_ngay_nhan, phieuThuePhong.getNgayNhan()));
            holder.tvNgayTra.setText(context.getResources().getString(R.string.txt_ngay_tra, phieuThuePhong.getNgayTra()));
            holder.imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callBackPhieuThuePhong.handleEdit(phieuThuePhong);
                }
            });
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callBackPhieuThuePhong.handleDelete(phieuThuePhong);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return phieuThuePhongs.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCMND, tvHoTen, tvNgayNhan, tvNgayTra;
        ImageView imgEdit, imgDelete;

        ViewHolder(View itemView) {
            super(itemView);
            tvCMND = itemView.findViewById(R.id.tvCMND);
            tvHoTen = itemView.findViewById(R.id.tvHoTen);
            tvNgayNhan = itemView.findViewById(R.id.tvNgayNhan);
            tvNgayTra = itemView.findViewById(R.id.tvNgayTra);
            imgEdit = itemView.findViewById(R.id.imgEdit);
            imgDelete = itemView.findViewById(R.id.imgDelete);
        }
    }

    public interface CallBackPhieuThuePhong {
        void handleEdit(PhieuThuePhong phieuThuePhong);

        void handleDelete(PhieuThuePhong phieuThuePhong);

    }
}
