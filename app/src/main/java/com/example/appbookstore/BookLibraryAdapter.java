package com.example.appbookstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class BookLibraryAdapter extends BaseAdapter{
    private Context context;
    private int layout;
    private List<BookLibrary> bookList;

    public BookLibraryAdapter(Context context, int layout, List<BookLibrary> bookList) {
        this.context = context;
        this.layout = layout;
        this.bookList = bookList;
    }

    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        ImageView imgHinh, imgTrangThai;
        TextView txtTen, txtNguoiDang;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder = new BookLibraryAdapter.ViewHolder();
            //anh xa view
            holder.txtTen = (TextView) view.findViewById(R.id.textViewTen);
            holder.txtNguoiDang = (TextView) view.findViewById(R.id.textViewNguoiDang);
            holder.imgHinh = (ImageView) view.findViewById(R.id.imageViewHinh);
            holder.imgTrangThai = (ImageButton) view.findViewById(R.id.imageButtonTrangThai);
            view.setTag(holder);
        }else {
            holder = (BookLibraryAdapter.ViewHolder) view.getTag();
        }
        try {
            BookLibrary book = bookList.get(i);
            String ten = book.getTen();
            if(ten.length() > 27)
                ten = ten.substring(0, 25) + "...";
            holder.txtTen.setText(ten);
            holder.imgHinh.setImageResource(book.getHinh());
            holder.txtNguoiDang.setText(book.getNguoiDang());
            holder.imgTrangThai.setImageResource(book.getHinhTrangThai());
        } catch (Exception e){

        }
        return view;
    }
}