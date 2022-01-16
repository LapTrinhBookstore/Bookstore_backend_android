package com.example.appbookstore;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.appbookstore.model.DetailBook;
import com.google.android.material.imageview.ShapeableImageView;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class layout_Detail_GioiThieuSach extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_detail_gioi_thieu_sach);

        setColorStatusBar();
        toolbarNavigation();

        init();
    }

    private void init() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String name = bundle.getString("name");
            String img = bundle.getString("img");
            String intro = bundle.getString("intro");
            String lang = bundle.getString("lang");
            String nxb = bundle.getString("nxb");
            String ngayxb = bundle.getString("ngayxb");
            int trang = bundle.getInt("trang", 0);
            String cate = bundle.getString("cate");

            TextView book_name = findViewById(R.id.book_name),
                    book_nxb = findViewById(R.id.GTSach_nxb),
                    book_page = findViewById(R.id.GTSach_sotrang),
                    book_intro = findViewById(R.id.detail_intro),
                    GTSach_ngonNgu = findViewById(R.id.GTSach_ngonNgu),
                    ngay_xb = findViewById(R.id.GTSach_ngayXb),
                    GTSach_theLoai = findViewById(R.id.GTSach_theLoai);

            ShapeableImageView book_avt = findViewById(R.id.book_avt);

            book_name.setText(name);
            book_intro.setText(intro);
            book_nxb.setText(nxb);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
            try {
                ngay_xb.setText(sdf2.format(sdf.parse(ngayxb)));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            book_page.setText(trang + "");
            GTSach_ngonNgu.setText(lang);
            GTSach_theLoai.setText(cate);

            Glide.with(layout_Detail_GioiThieuSach.this).load("http://192.168.1.7/android/Bookstore/public/bookstore/image/" + img).into(book_avt);
        }
    }

    private void setColorStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        getWindow().setStatusBarColor(ContextCompat.getColor(layout_Detail_GioiThieuSach.this,R.color.white));
    }

    private void toolbarNavigation() {
        Toolbar toolbar = findViewById(R.id.gioiThieuSach_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
    }
}