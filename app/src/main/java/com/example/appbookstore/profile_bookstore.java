package com.example.appbookstore;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbookstore.api.ApiService;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class profile_bookstore extends AppCompatActivity {

    private Button btnDetailInfo;
    private Button btnBankAccount;
    private Button btnCoins;
    private Button btnPhoneNumber;
    private Button btnEmail;
    private Button btnChangePassword;
    private Button btnOpenDialog_changePhone;
    private Button btnOpenDialog_changeEmail;
    private Button btnOpenDialog_changePassword;
    private TextView tvNameProfile;
    private TextView tvBankAccountProfile;
    private TextView tvNumberCoinsProfile;
    private TextView tvPhoneNumberProfile;
    private TextView tvEmailProfile;

    private EditText etPhoneNumber;
    private Button btnSubmitChangePhone;

    private EditText etEmail;
    private Button btnSubmitChangeEmail;

    private TextInputEditText etOldPassword;
    private TextInputEditText etNewPassword;
    private Button btnSubmitChangePassword;
    private String password = "";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_bookstore);
        // change status bar
        setColorStatusBar();
        // setActionBar();
        toolbarNavigation();
        // move to details information
        btnDetailInfo = (Button) findViewById(R.id.btn_detail_info);
        btnDetailInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile_bookstore.this, details_personal_info.class);
                startActivity(intent);
            }
        });
        // move to bank account
        btnBankAccount = (Button) findViewById(R.id.btn_bank_account);
        btnBankAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile_bookstore.this, bank_account.class);
                startActivity(intent);
            }
        });
        // move to Bookstore Coins
        btnCoins = (Button) findViewById(R.id.btn_coins);
        btnCoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile_bookstore.this, exchange_coins.class);
                startActivity(intent);
            }
        });
        // move to diaglog change phone number
        btnOpenDialog_changePhone = (Button) findViewById(R.id.btn_phoneNumber);
        btnOpenDialog_changePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(Gravity.BOTTOM,R.layout.activity_dialog_change_phonenumber);
            }
        });
        // move to dialog change email
        btnOpenDialog_changeEmail = (Button) findViewById(R.id.btn_email);
        btnOpenDialog_changeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(Gravity.BOTTOM, R.layout.activity_dialog_change_email);
            }
        });
        // move to dialog change password
        btnOpenDialog_changePassword = (Button) findViewById(R.id.btn_changePassword);
        btnOpenDialog_changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*AlertDialog.Builder mBuilder = new AlertDialog.Builder(profile_bookstore.this);
                View mView = getLayoutInflater().inflate(R.layout.activity_dialog_enter_old_password, null);
                mBuilder.setPositiveButton(Html.fromHtml("<font color='#00BBF0'>Tiếp tục</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        openDialog(Gravity.BOTTOM, R.layout.activity_dialog_enter_newpassword);
                    }
                });
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
                dialog.getWindow().setGravity(Gravity.BOTTOM);*/
                openDialog(Gravity.BOTTOM, R.layout.activity_dialog_enter_newpassword);
            }
        });

        // call api
        tvNameProfile = (TextView) findViewById(R.id.profile_fullName);
        tvBankAccountProfile = (TextView) findViewById(R.id.profile_bankAccount);
        tvNumberCoinsProfile = (TextView) findViewById(R.id.profile_numberCoins);
        tvPhoneNumberProfile = (TextView) findViewById(R.id.profile_phoneNumber);
        tvEmailProfile = (TextView) findViewById(R.id.profile_email);

        getUsers(1);
    }

    private void setColorStatusBar() {
        getWindow().setStatusBarColor(ContextCompat.getColor(profile_bookstore.this,R.color.c_005792));
    }

    private void toolbarNavigation() {
        Toolbar toolbar = findViewById(R.id.profile_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
    }


    // open dialog
    public void openDialog(int gravity, int view){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);

        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);
        // if click outside area, dialog will hide
        if(Gravity.BOTTOM == gravity){
            dialog.setCancelable(true);
        } else{
            dialog.setCancelable(false);
        }

        // processing dialog change phone number
        if(view == R.layout.activity_dialog_change_phonenumber){
            etPhoneNumber = dialog.findViewById(R.id.dialogPhoneNumber);
            btnSubmitChangePhone = dialog.findViewById(R.id.dialogPhoneNumber_Submit);
            btnSubmitChangePhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UsersModel usersModel = new UsersModel();
                    tvPhoneNumberProfile.setText(etPhoneNumber.getText());
                    usersModel.setPhoneNumber(String.valueOf(etPhoneNumber.getText()));
                    usersModel.setId(1);
                    callApiUpdatePhoneNumber(usersModel);
                    dialog.dismiss();
                }
            });
        }

        // processing dialog change email
        if(view == R.layout.activity_dialog_change_email){
            etEmail = dialog.findViewById(R.id.dialogChangeEmail_email);
            btnSubmitChangeEmail = dialog.findViewById(R.id.dialogChangeEmail_submit);
            btnSubmitChangeEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UsersModel usersModel1 = new UsersModel();
                    tvEmailProfile.setText(etEmail.getText());
                    usersModel1.setEmail(String.valueOf(etEmail.getText()));
                    usersModel1.setId(1);
                    callApiUpdateEmail(usersModel1);
                    dialog.dismiss();
                }
            });
        }

        // processing dialog change password
        if(view == R.layout.activity_dialog_enter_newpassword){
            etOldPassword = dialog.findViewById(R.id.dialogNewPassword_oldPassword);
            etNewPassword = dialog.findViewById(R.id.dialogNewPassword_newPassword);
            btnSubmitChangePassword = dialog.findViewById(R.id.dialogNewPassword_submit);

            etOldPassword.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(s.length()==0){
                        etOldPassword.setError("Không được để trống");
                    }
                    else{
                        etOldPassword.setError(null);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            etNewPassword.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(s.length()==0){
                        etNewPassword.setError("Không được để trống");
                    }
                    else{
                        etNewPassword.setError(null);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            btnSubmitChangePassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UsersModel usersModel = new UsersModel();
                    String oldPass = String.valueOf(etOldPassword.getText());
                    String newPass = String.valueOf(etNewPassword.getText());

                    if(oldPass.length()!=0 && newPass.length()!=0){
                        if(oldPass.equals(password)){
                            etOldPassword.setError(null);
                            usersModel.setPassword(newPass);
                            usersModel.setId(1);
                            callApiUpdatePassword(usersModel);
                            dialog.dismiss();
                        }
                        else{
                            etOldPassword.setError("Mật khẩu không đúng!");
                        }
                    }
                    else{
                        if(oldPass.length()==0){
                            etOldPassword.setError("Không được để trống");
                        }
                        else{
                            etOldPassword.setError("Không được để trống");
                        }
                    }
                }
            });

        }

        dialog.show();
    }

    // call api
    private void getUsers(int id){
        ApiService.apiService.getUsers(id)
                .enqueue(new Callback<UsersModel>() {
                    @Override
                    public void onResponse(Call<UsersModel> call, Response<UsersModel> response) {
                        UsersModel usersModel = response.body();
                        if (usersModel != null){
                            tvNameProfile.setText(usersModel.getName());
                            /*tvBankAccountProfile.setText(users.get);*/
                            tvNumberCoinsProfile.setText(usersModel.getNumberOfCoins() + " xu");
                            tvPhoneNumberProfile.setText(usersModel.getPhoneNumber());
                            tvEmailProfile.setText(usersModel.getEmail());
                            password = usersModel.getPassword();
                        }
                    }
                    @Override
                    public void onFailure(Call<UsersModel> call, Throwable t) {
                        tvNameProfile.setText("Lỗi!");
                    }
                });
    }

    // call api update phone number
    private void callApiUpdatePhoneNumber(UsersModel usersModel){
        ApiService.apiService.updatePhoneNumber(usersModel).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.isSuccessful()){
                    Toast.makeText(profile_bookstore.this, "Thành Công", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(profile_bookstore.this, "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // call api update email
    private void callApiUpdateEmail(UsersModel usersModel){
        ApiService.apiService.updateEmail(usersModel).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.isSuccessful()){
                    Toast.makeText(profile_bookstore.this, "Thành Công", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(profile_bookstore.this, "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // call api update password
    private void callApiUpdatePassword(UsersModel usersModel){
        ApiService.apiService.updatePassword(usersModel).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.isSuccessful()){
                    Toast.makeText(profile_bookstore.this, "Thành Công", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(profile_bookstore.this, "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }

}