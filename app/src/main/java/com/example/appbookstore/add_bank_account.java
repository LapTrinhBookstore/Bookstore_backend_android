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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.appbookstore.api.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class add_bank_account extends AppCompatActivity {

    private Spinner spnBankName;
    private EditText etAccountBankNumber;
    private Button btnSubmitAddBank;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bank_account);
        // set actionbar and statusBar
        // setActionBar();
        toolbarNavigation();
        setColorStatusBar();

        // mapping
        spnBankName = (Spinner) findViewById(R.id.addBank_bankName);
        etAccountBankNumber = (EditText) findViewById(R.id.addBank_accountBankNumber);
        btnSubmitAddBank = (Button) findViewById(R.id.addbank_submit);
        // empty
        etAccountBankNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()==0){
                    etAccountBankNumber.setError("Không được để trống");
                }
                else{
                    etAccountBankNumber.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        // call api
        btnSubmitAddBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bankAccountModel bankAccount = new bankAccountModel();
                String newBankName = "";
                newBankName = spnBankName.getSelectedItem().toString();
                if (!newBankName.equals("Chọn một ngân hàng")){
                    if (newBankName.equals("VietcomBank")){bankAccount.setBankID(1);}
                    else if(newBankName.equals("AgriBank")) {bankAccount.setBankID(2);}
                    else if(newBankName.equals("VietinBank")) {bankAccount.setBankID(3);}
                    else if(newBankName.equals("TechcomBank")) {bankAccount.setBankID(4);}
                    else if(newBankName.equals("SacomBank")) {bankAccount.setBankID(5);}
                    else if(newBankName.equals("MPBank")) {bankAccount.setBankID(6);}
                    else if(newBankName.equals("VPBank")) {bankAccount.setBankID(7);}
                }
                bankAccount.setAccountNumber(String.valueOf(etAccountBankNumber.getText()));
                bankAccount.setIdUser(1);
                callApiInserBankNumber(bankAccount);

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setColorStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        getWindow().setStatusBarColor(ContextCompat.getColor(add_bank_account.this,R.color.white));
    }

    private void setActionBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(Html.fromHtml("<font color=\"#005792\">" + "<small>Thêm tài khoản ngân hàng</small>"));
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));
        // insert icon back for actionbar
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back_gray);
    }

    private void toolbarNavigation() {
        Toolbar toolbar = findViewById(R.id.addBank_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
    }

    // call api insert bank account
    private void callApiInserBankNumber(bankAccountModel bankAccount){
        ApiService.apiService.saveBankAccount(bankAccount).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.isSuccessful()){
                    Toast.makeText(add_bank_account.this, "Thành Công", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(add_bank_account.this, profile_bookstore.class);
                    startActivity(intent);
                }
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(add_bank_account.this, "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }


}