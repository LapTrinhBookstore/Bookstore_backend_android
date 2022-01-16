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
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
    private CheckBox checkBoxMale;
    private CheckBox checkBoxFemale;
    private int gender = 0;

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
        checkBoxMale = (CheckBox) findViewById(R.id.changeInfo_checkBoxMale);
        checkBoxFemale = (CheckBox) findViewById(R.id.changeInfo_checkBoxFeMale);
        btnSave = (Button) findViewById(R.id.changeInfo_Submit);
        // gender
        checkBoxMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();
                if (checked){
                    gender = 1;
                }
            }
        });

        checkBoxFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();
                if (checked){
                    gender = 0;
                }
            }
        });
        // date picker
        etDoB.setFocusable(false);
        DatePicker datePicker = new DatePicker();
        etDoB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.openDatePicker_EditText(change_information_personal.this, etDoB);
            }
        });
        // fix full name
        etFullName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()==0){
                    etFullName.setError("Không được để trống");
                }
                else{
                    etFullName.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // call api
        getUsers(1);
        // update data
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etFullName.getText().length() != 0){
                    UsersModel usersModel = new UsersModel();
                    usersModel.setName(String.valueOf(etFullName.getText()));
                    usersModel.setDateOfBirth(String.valueOf(etDoB.getText()));

                    usersModel.setGender(gender);
                    usersModel.setId(1);
                    callApiUpdateInfoPersonal(usersModel);
                }
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
    private void getUsers(int id){
        ApiService.apiService.getUsers(id)
                .enqueue(new Callback<UsersModel>() {
                    @Override
                    public void onResponse(Call<UsersModel> call, Response<UsersModel> response) {
                        UsersModel usersModel = response.body();
                        if (usersModel != null){
                            etFullName.setText(usersModel.getName());
                            etDoB.setText(usersModel.getDateOfBirth());
                            gender = usersModel.getGender();
                        }
                    }
                    @Override
                    public void onFailure(Call<UsersModel> call, Throwable t) {
                        Toast.makeText(change_information_personal.this, "Lỗi", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // call api update information personal
    private void callApiUpdateInfoPersonal(UsersModel usersModel){
        ApiService.apiService.updateDetails(usersModel).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.isSuccessful()){
                    Toast.makeText(change_information_personal.this, "Thành Công", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(change_information_personal.this, profile_bookstore.class);
                    startActivity(intent);
                }
            }
            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(change_information_personal.this, "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}