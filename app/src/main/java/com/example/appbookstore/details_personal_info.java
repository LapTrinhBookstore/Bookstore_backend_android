package com.example.appbookstore;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.appbookstore.api.ApiService;

import java.security.PrivateKey;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class details_personal_info extends AppCompatActivity {

    private Button btnChangeInfo;
    private TextView tvDetailsFullName1;
    private TextView tvDetailsFullName2;
    private TextView tvDetailsGender;
    private TextView tvDetailsDOB;
    private TextView tvDetailsEmail;
    private TextView tvDetailsPhoneNumber;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_personal_info);
        // set actionbar and statusBar
        setColorStatusBar();
        // setActionBar();
        toolbarNavigation();
        // move to change information
        btnChangeInfo = (Button) findViewById(R.id.btnChange_profile);
        btnChangeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(details_personal_info.this,change_information_personal.class);
                startActivity(intent);
            }
        });
        // mapping
        tvDetailsFullName1 = (TextView) findViewById(R.id.detailsProfile_fullname1);
        tvDetailsFullName2 = (TextView) findViewById(R.id.detailsProfile_fullname2);
        tvDetailsGender = (TextView) findViewById(R.id.detailsProfile_gender);
        tvDetailsDOB = (TextView) findViewById(R.id.detailsProfile_dob);
        tvDetailsEmail = (TextView) findViewById(R.id.detailsProfile_email);
        tvDetailsPhoneNumber = (TextView) findViewById(R.id.detailsProfile_phoneNumer);
        // call api
        getListUsers();
    }

    private void setColorStatusBar() {
        getWindow().setStatusBarColor(ContextCompat.getColor(details_personal_info.this,R.color.c_005792));
    }

    private void toolbarNavigation() {
        Toolbar toolbar = findViewById(R.id.chiTietProfile_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
    }

    // call api
    private void getListUsers(){
        ApiService.apiService.getUsers(1)
                .enqueue(new Callback<Users>() {
                    @Override
                    public void onResponse(Call<Users> call, Response<Users> response) {
                        Users users = response.body();
                        if (users != null){
                            tvDetailsFullName1.setText(users.getName());
                            tvDetailsFullName2.setText(users.getName());
                            if(users.getGender() == 1){
                                tvDetailsGender.setText("Nam");
                            } else {tvDetailsGender.setText("Nữ");}
                            tvDetailsDOB.setText(users.getDateOfBirth());
                            tvDetailsEmail.setText(users.getEmail());
                            tvDetailsPhoneNumber.setText(users.getPhoneNumber());
                        }
                    }
                    @Override
                    public void onFailure(Call<Users> call, Throwable t) {
                        tvDetailsFullName1.setText("Lỗi!");
                    }
                });
    }
}