package com.example.appbookstore;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class layout_Detail_XemDanhGia extends AppCompatActivity {
    RadioGroup radioGroup;
    DanhGiaAdapter danhGiaAdapter;
    RecyclerView rv_DanhGia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_detail_xem_danh_gia);

        radioGroup = (RadioGroup) findViewById(R.id.xemDG_radioBtnGroup);

        setColorStatusBar();
        toolbarNavigation();
        LoadDanhGia();
        setOnclickBtnLocDanhGia();
    }

    private void setColorStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        getWindow().setStatusBarColor(ContextCompat.getColor(layout_Detail_XemDanhGia.this,R.color.white));
    }

    private void toolbarNavigation() {
        Toolbar toolbar = findViewById(R.id.xemDG_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
    }

    private void LoadDanhGia() {
        List<DanhGiaObj> list = getListDanhGia();
        danhGiaAdapter = new DanhGiaAdapter(list);
        rv_DanhGia = (RecyclerView) findViewById(R.id.xemDG_danhsachDG);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_DanhGia.setLayoutManager(linearLayoutManager);
        rv_DanhGia.setAdapter(danhGiaAdapter);
        KhongCoDanhGia(list.size());
    }

    private void KhongCoDanhGia(int listSize) {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.xemGD_khongCoGD);
        if (listSize == 0) {
            linearLayout.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;
        } else {
            linearLayout.getLayoutParams().height = 0;
        }
    }

    private List<DanhGiaObj> getListDanhGia() {
        List<DanhGiaObj> list = new ArrayList<>();

        list.add(new DanhGiaObj(R.drawable.avatar, "Trung Pham", "21/10/2021", "Good", 4));
        list.add(new DanhGiaObj(R.drawable.avatar, "Trung Pham", "21/10/2021", "Good", 4));
        list.add(new DanhGiaObj(R.drawable.avatar, "Trung Pham", "21/10/2021", "Good", 5));
        list.add(new DanhGiaObj(R.drawable.avatar, "Trung Pham", "21/10/2021", "Good", 5));
        list.add(new DanhGiaObj(R.drawable.avatar, "Trung Pham", "21/10/2021", "Good", 2));
        list.add(new DanhGiaObj(R.drawable.avatar, "Trung Pham", "21/10/2021", "Good", 3));
        list.add(new DanhGiaObj(R.drawable.avatar, "Trung Pham", "21/10/2021", "Good", 4));
        list.add(new DanhGiaObj(R.drawable.avatar, "Trung Pham", "21/10/2021", "Good", 4));
        list.add(new DanhGiaObj(R.drawable.avatar, "Trung Pham", "21/10/2021", "Good", 5));
        list.add(new DanhGiaObj(R.drawable.avatar, "Trung Pham", "21/10/2021", "Good", 5));
        list.add(new DanhGiaObj(R.drawable.avatar, "Trung Pham", "21/10/2021", "Good", 5));
        list.add(new DanhGiaObj(R.drawable.avatar, "Trung Pham", "21/10/2021", "Good", 5));
        list.add(new DanhGiaObj(R.drawable.avatar, "Trung Pham", "21/10/2021", "Good", 3));
        list.add(new DanhGiaObj(R.drawable.avatar, "Trung Pham", "21/10/2021", "Good", 3));
        list.add(new DanhGiaObj(R.drawable.avatar, "Trung Pham", "21/10/2021", "Good", 3));
        list.add(new DanhGiaObj(R.drawable.avatar, "Trung Pham", "21/10/2021", "Good", 4));
        list.add(new DanhGiaObj(R.drawable.avatar, "Trung Pham", "21/10/2021", "Good", 4));
        list.add(new DanhGiaObj(R.drawable.avatar, "Trung Pham", "21/10/2021", "Good", 5));
        list.add(new DanhGiaObj(R.drawable.avatar, "Trung Pham", "21/10/2021", "Good", 5));
        list.add(new DanhGiaObj(R.drawable.avatar, "Trung Pham", "21/10/2021", "Good", 5));
        list.add(new DanhGiaObj(R.drawable.avatar, "Trung Pham", "21/10/2021", "Good", 5));
        list.add(new DanhGiaObj(R.drawable.avatar, "Trung Pham", "21/10/2021", "Good", 4));

        return list;
    }

    private void LocDanhGia(float rating) {
        List<DanhGiaObj> list = new ArrayList<>();
        if(rating == 0)
            danhGiaAdapter.danhGiaObjList = danhGiaAdapter.tatCaDanhGia;
        else {
            for(DanhGiaObj item : danhGiaAdapter.tatCaDanhGia)
                if(item.getRating() ==  rating)
                    list.add(item);
            danhGiaAdapter.danhGiaObjList = list;
        }

        KhongCoDanhGia(danhGiaAdapter.danhGiaObjList.size());
        danhGiaAdapter.notifyDataSetChanged();
    }

    private void setOnclickBtnLocDanhGia() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton;

                radioButton = (RadioButton) findViewById(i);
                float rating;
                try {
                    rating = Float.parseFloat(radioButton.getText().toString());
                } catch (Exception e) {
                    rating = 0;
                    System.out.println(rating);
                }
                LocDanhGia(rating);

                for(int j = 0; j < radioGroup.getChildCount(); j++) {
                    radioButton = (RadioButton) radioGroup.getChildAt(j);

                    if(radioButton.isChecked()) {
                        if(radioButton.getId() != R.id.xemDG_tatCa)
                            radioButton.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_ratingbar_star_fill_14), null);
                        radioButton.setTextColor(getResources().getColor(R.color.c_005792));
                    } else {
                        radioButton.setTextColor(getResources().getColor(R.color.c_00204A));
                        if(radioButton.getId() != R.id.xemDG_tatCa)
                            radioButton.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_ratingbar_star_fill_14_dark), null);
                    }
                }


            }
        });
    }
}