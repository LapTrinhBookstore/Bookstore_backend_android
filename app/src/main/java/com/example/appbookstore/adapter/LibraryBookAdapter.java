package com.example.appbookstore.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appbookstore.R;
import com.example.appbookstore.model.LibraryBook;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class LibraryBookAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<LibraryBook> bookList;

    public LibraryBookAdapter(Context context, int layout, List<LibraryBook> bookList) {
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
        TextView txtTen, txtTacGia;
        ImageView ibtAn;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder = new ViewHolder();
            //anh xa view
            holder.txtTen = (TextView) view.findViewById(R.id.textViewTen);
            holder.txtTacGia = (TextView) view.findViewById(R.id.textViewNguoiDang);
            holder.imgHinh = (ImageView) view.findViewById(R.id.imageViewHinh);
            holder.ibtAn = (ImageView) view.findViewById(R.id.imageButtonAn);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        try {
            LibraryBook book = bookList.get(i);
            String ten = book.getTen();
            if(ten.length() > 65)
                ten = ten.substring(0, 63) + "...";
            holder.txtTen.setText(ten);
            try {
                String url = "http://192.168.1.7/android/Bookstore/public/bookstore/image/" + book.getHinh();
//                String url = "http://192.168.1.3/Bookstore_android/public/bookstore/image/" + book.getHinh();
                new LoadImageInternet(holder.imgHinh).execute(url);
            }catch (Exception e){
                //holder.imgAnh.setImageResource(R.drawable.th1);
            }
            String tacGia = book.getTacGia();
            tacGia = tacGia.substring(0, tacGia.length()-2);
            holder.txtTacGia.setText(tacGia);

        } catch (Exception e){

        }

        return view;
    }
    public Bitmap loadImageInternet(String imageUrl) {
        Bitmap bitmapHinh = null;
        try {
            URL url = new URL(imageUrl);
            InputStream inputStream = url.openConnection().getInputStream();
            bitmapHinh = BitmapFactory.decodeStream(inputStream);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmapHinh;
    }

    public class LoadImageInternet extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public LoadImageInternet(ImageView bmImage) {
            this.bmImage = bmImage;
        }
        Bitmap bitmapHinh = null;
        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                InputStream inputStream = url.openConnection().getInputStream();
                bitmapHinh = BitmapFactory.decodeStream(inputStream);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmapHinh;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            bmImage.setImageBitmap(bitmap);
        }
    }
//    private void updateAn(String url, String productID, String userID, int status){
//        RequestQueue requestQueue = Volley.newRequestQueue(context);
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        String urlGetLibrary = "https://bookstoreandroid.000webhostapp.com/bookstore/getthuvien.php?iduser=US00000001&status=2";
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(context, "Xãy ra lỗi!", Toast.LENGTH_SHORT).show();
//                    }
//                }
//        ){
//            @Nullable
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("productID", productID);
//                params.put("userID", userID);
//                params.put("status", String.valueOf(status));
//
//                return params;
//            }
//        };
//        requestQueue.add(stringRequest);
//    }
}
