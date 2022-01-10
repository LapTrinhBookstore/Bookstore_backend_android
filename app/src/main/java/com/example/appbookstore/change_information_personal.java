package com.example.appbookstore;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appbookstore.api.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class change_information_personal extends AppCompatActivity {

    private EditText etFullName;
    private EditText etDoB;
    private Button btnSave;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_information_personal);
        // set actionBar and statusBar
        // setActionBar();
        toolbarNavigation();
        setColorStatusBar();

        // mapping
        etFullName = (EditText) findViewById(R.id.changeInfo_fullName);
        etDoB = (EditText) findViewById(R.id.changeInfo_DoB);
        btnSave = (Button) findViewById(R.id.changeInfo_Submit);
        // call api
        getListUsers(1);
        // update data
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*callApiUpdate(1);*/
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setColorStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        getWindow().setStatusBarColor(ContextCompat.getColor(change_information_personal.this,R.color.white));
    }

    private void setActionBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(Html.fromHtml("<font color=\"#005792\">" + "<small>Chỉnh sửa thông tin</small>"));
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));
        // insert icon back for actionbar
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back_gray);
    }
    private void toolbarNavigation() {
        Toolbar toolbar = findViewById(R.id.changeInfo_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
    }

    // call api
    private void getListUsers(int id){
        ApiService.apiService.getUsers(id)
                .enqueue(new Callback<Users>() {
                    @Override
                    public void onResponse(Call<Users> call, Response<Users> response) {
                        Users users = response.body();
                        if (users != null){
                            etFullName.setText(users.getName());
                            etDoB.setText(users.getDateOfBirth());
                        }
                    }
                    @Override
                    public void onFailure(Call<Users> call, Throwable t) {
                        Toast.makeText(change_information_personal.this, "Lỗi", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /*// call api update data
    private void callApiUpdate(int id){
        ApiService.apiService.updateUser(id).enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if(response.isSuccessful()){
                    Toast.makeText(change_information_personal.this, "Thành Công", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(change_information_personal.this, "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }*/
}