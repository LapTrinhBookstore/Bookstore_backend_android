package com.example.appbookstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbookstore.Book;

import java.util.List;

public class BookAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Book> bookList;

    public BookAdapter(Context context, int layout, List<Book> bookList) {
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
        ImageView imgHinh;
        TextView txtSTT, txtTen, txtNguoiDang, txtLuotDoc, txtGia;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder = new ViewHolder();
            //anh xa view
            holder.txtSTT = (TextView) view.findViewById(R.id.textViewSTT);
            holder.txtTen = (TextView) view.findViewById(R.id.textViewTen);
            holder.txtNguoiDang = (TextView) view.findViewById(R.id.textViewNguoiDang);
            holder.txtLuotDoc = (TextView) view.findViewById(R.id.textViewLuotDoc);
            holder.txtGia = (TextView) view.findViewById(R.id.textViewGia);
            holder.imgHinh = (ImageView) view.findViewById(R.id.imageViewHinh);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        try {
            Book book = bookList.get(i);
            holder.txtSTT.setText(String.valueOf(book.getStt()));
            String ten = book.getTen();
            if(ten.length() > 27)
                ten = ten.substring(0, 25) + "...";
            holder.txtTen.setText(ten);
            holder.imgHinh.setImageResource(book.getHinh());
            holder.txtNguoiDang.setText(book.getNguoiDang());
            holder.txtLuotDoc.setText(book.getLuotDoc());
            int gia = book.getGia();
            String strGia = String.format("%.d", gia) + " đ";
            holder.txtGia.setText(strGia);
        } catch (Exception e){

        }
        return view;
    }
}