package com.example.appbookstore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appbookstore.R;
import com.example.appbookstore.model.SapXep;

import java.util.List;

public class SapXepAdapter extends ArrayAdapter<SapXep> {
    public SapXepAdapter(@NonNull Context context, int resource, @NonNull List<SapXep> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected, parent, false);
        TextView tvSelected = convertView.findViewById(R.id.textViewSelected);
        SapXep sapXep = this.getItem(position);
        if(sapXep != null){
            tvSelected.setText("Sắp xếp theo: " + sapXep.getName());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sapxep, parent, false);
        TextView tvSapXep = convertView.findViewById(R.id.tv_sapxep);
        SapXep sapXep = this.getItem(position);
        if(sapXep != null){
            tvSapXep.setText(sapXep.getName());
        }
        return convertView;
    }
}
