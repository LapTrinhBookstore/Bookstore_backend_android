package com.example.appbookstore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

public class SachTimKiemAdapter extends RecyclerView.Adapter<SachTimKiemAdapter.SachTimKiemHolder> {
    private List<SachObj> sachObjList;

    public SachTimKiemAdapter(List<SachObj> sachObjList) {
        this.sachObjList = sachObjList;
    }

    @NonNull
    @Override
    public SachTimKiemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sach, parent,false);
        return new SachTimKiemAdapter.SachTimKiemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SachTimKiemHolder holder, int position) {
        SachObj sachObj = sachObjList.get(position);
        if(sachObj == null)
            return;
        holder.sachTK_bia.setImageResource(sachObj.getBiaSach());
        holder.sachTK_ten.setText(sachObj.getTenSach());
        holder.sachTK_tacGia.setText(sachObj.getTacGia());
        holder.sachTK_loaiSach.setText(sachObj.getLoaiSach());
        holder.sachTK_rating.setText(String.valueOf(sachObj.getRating()));
        DecimalFormat formatter = new DecimalFormat("###,###,###.##");
        holder.sachTK_giaBan.setText(formatter.format(sachObj.getGiaBan())+" đ");
    }

    @Override
    public int getItemCount() {
        if(sachObjList != null) {
            return sachObjList.size();
        }
        return 0;
    }

    public class SachTimKiemHolder extends RecyclerView.ViewHolder {
        private ImageView sachTK_bia;
        private TextView sachTK_ten, sachTK_tacGia, sachTK_loaiSach, sachTK_rating, sachTK_giaBan;
        public SachTimKiemHolder(@NonNull View itemView) {
            super(itemView);
            sachTK_bia = itemView.findViewById(R.id.sachTK_bia);
            sachTK_ten = itemView.findViewById(R.id.sachTK_ten);
            sachTK_tacGia = itemView.findViewById(R.id.sachTK_tacGia);
            sachTK_loaiSach = itemView.findViewById(R.id.sachTK_loai);
            sachTK_rating = itemView.findViewById(R.id.sachTK_rating);
            sachTK_giaBan = itemView.findViewById(R.id.sachTK_gia);
        }
    }
}