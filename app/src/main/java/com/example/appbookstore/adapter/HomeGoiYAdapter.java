package com.example.appbookstore.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbookstore.R;
import com.example.appbookstore.model.HomeBook;
import com.example.appbookstore.my_interface.IClickItemBookListener;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;

public class HomeGoiYAdapter extends RecyclerView.Adapter<HomeGoiYAdapter.GoiYViewHolder> {
    private List<HomeBook> goiYList;
    private IClickItemBookListener iClickItemBookListener;

    public void setData(List<HomeBook> list, IClickItemBookListener listener){
        this.goiYList = list;
        this.iClickItemBookListener = listener;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HomeGoiYAdapter.GoiYViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_goiy, parent, false);
        return new HomeGoiYAdapter.GoiYViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeGoiYAdapter.GoiYViewHolder holder, int position) {
        HomeBook book = goiYList.get(position);
        if (book == null){
            return;
        }
        try {
            String url = "https://bookstoreandroid.000webhostapp.com/bookstore/image/" + book.getAnh();
            new LoadImageInternet(holder.imgAnh).execute(url);
        }catch (Exception e){
            //holder.imgAnh.setImageResource(R.drawable.th1);
        }
        String ten = book.getTitle();
        if(ten.length() > 27)
            ten = ten.substring(0, 25) + "...";
        holder.tvTieuDe.setText(ten);
        int gia = book.getPrice();
        if(gia == 0){
            holder.tvGia.setText("Miễn phí");
        }else{
            String str = String.format(Locale.US, "%,d", gia).replace(',', '.');
            holder.tvGia.setText(str + " đ");
        }
        holder.loItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemBookListener.onClichItemBook(book);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(goiYList != null){
            return goiYList.size();
        }
        return 0;
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
    public class GoiYViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgAnh;
        private TextView tvTieuDe;
        private TextView tvGia;
        private LinearLayout loItem;
        public GoiYViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAnh = (ImageView) itemView.findViewById(R.id.imageViewAnh);
            tvTieuDe = (TextView) itemView.findViewById(R.id.textViewTenSach);
            tvGia = (TextView) itemView.findViewById(R.id.textViewGiaSach);
            loItem = (LinearLayout) itemView.findViewById(R.id.layoutItem);
        }
    }
}