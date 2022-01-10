package com.example.appbookstore;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

public class layout_Detail_DanhGiaSach extends AppCompatActivity {
    private TextView textView;
    private EditText editText;
    private RatingBar ratingBar;
    private Button btn_vietDanhGia;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_detail_danh_gia_sach);


        ratingBar = (RatingBar) findViewById(R.id.danhGia_ratingBar);
        editText = (EditText) findViewById(R.id.danhGia_editText);
        textView = (TextView) findViewById(R.id.danhGia_soKiTu);
        btn_vietDanhGia = (Button) findViewById(R.id.btn_DangGia);

        setColorStatusBar();
        toolbarNavigation();
        setRatingBar();
        RatingBarChanged();
        SoKiTu();
    }

    private void SetClickAbleBtnDang(float rating) {
        Boolean clickAble = true;
        int textColor = R.color.c_005792;

        if(rating == 0) {
            clickAble = false;
            textColor = R.color.c_60000000;
        }

        btn_vietDanhGia.setClickable(clickAble);
        btn_vietDanhGia.setTextColor(getResources().getColor(textColor));
    }

    private void setRatingBar() {
        intent = getIntent();
        ratingBar.setRating(intent.getFloatExtra("rating", 0));
        SetClickAbleBtnDang(ratingBar.getRating());
    }

    private void RatingBarChanged() {
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                SetClickAbleBtnDang(ratingBar.getRating());
            }
        });
    }

    private void SoKiTu() {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int soKiTu = charSequence.length();
                textView.setText(soKiTu + "/1000");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setColorStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        getWindow().setStatusBarColor(ContextCompat.getColor(layout_Detail_DanhGiaSach.this,R.color.white));
    }

    private void toolbarNavigation() {
        Toolbar toolbar = findViewById(R.id.danhGiaSach_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(layout_Detail_DanhGiaSach.this)
                        .setTitle("")
                        .setMessage("Hủy bản nháp?")
                        .setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .setNegativeButton("Giữ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
//                , R.style.AlertDialog
            }
        });
    }
}