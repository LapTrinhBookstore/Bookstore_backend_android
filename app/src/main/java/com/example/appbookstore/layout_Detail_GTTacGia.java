package com.example.appbookstore;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class layout_Detail_GTTacGia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_detail_gttac_gia);

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

            TextView book_name = findViewById(R.id.book_name),
                    book_name2 = findViewById(R.id.bookname2),
                    book_intro = findViewById(R.id.introAuthor);

            ShapeableImageView book_avt = findViewById(R.id.book_avt);
            book_name.setText(name);
            book_name2.setText(name);
            book_intro.setText(intro);

            Glide.with(layout_Detail_GTTacGia.this).load("http://192.168.1.7/android/Bookstore/public/bookstore/image/" + img).into(book_avt);
        }
    }

    private void setColorStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        getWindow().setStatusBarColor(ContextCompat.getColor(layout_Detail_GTTacGia.this,R.color.white));
    }

    private void toolbarNavigation() {
        Toolbar toolbar = findViewById(R.id.gioiThieuTgia_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
    }
}