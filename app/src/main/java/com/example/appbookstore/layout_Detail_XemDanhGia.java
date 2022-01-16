package com.example.appbookstore;

import android.content.Intent;
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

import com.example.appbookstore.api.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class layout_Detail_XemDanhGia extends AppCompatActivity {
    RadioGroup radioGroup;
    DanhGiaAdapter danhGiaAdapter;
    RecyclerView rv_DanhGia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_detail_xem_danh_gia);

        radioGroup = (RadioGroup) findViewById(R.id.xemDG_radioBtnGroup);

        Intent intent = getIntent();

        setColorStatusBar();
        toolbarNavigation();
        LoadDanhGia(intent.getIntExtra("idbook", 0));
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

    private void LoadDanhGia(int idProduct) {
        ApiService.apiService.AllRating(idProduct).enqueue(new Callback<List<DanhGiaObj>>() {
            @Override
            public void onResponse(Call<List<DanhGiaObj>> call, Response<List<DanhGiaObj>> response) {
                List<DanhGiaObj> list = response.body();
                danhGiaAdapter = new DanhGiaAdapter(list);
                rv_DanhGia = (RecyclerView) findViewById(R.id.xemDG_danhsachDG);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(layout_Detail_XemDanhGia.this, LinearLayoutManager.VERTICAL, false);
                rv_DanhGia.setLayoutManager(linearLayoutManager);
                rv_DanhGia.setAdapter(danhGiaAdapter);
                KhongCoDanhGia(list.size());
            }

            @Override
            public void onFailure(Call<List<DanhGiaObj>> call, Throwable t) {

            }
        });
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