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
import android.widget.TextView;

import com.example.appbookstore.api.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class exchange_coins extends AppCompatActivity {

    private TextView tvCoins;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_coins);
        // setActionBar and statusBar
        setColorStatusBar();
        // setActionBar();
        toolbarNavigation();

        // mapping
        tvCoins = (TextView) findViewById(R.id.coins);
        // call api
        getListUsers();
    }

    private void setColorStatusBar() {
        getWindow().setStatusBarColor(ContextCompat.getColor(exchange_coins.this,R.color.c_005792));
    }

    private void setActionBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(Html.fromHtml("<small>Bookstore xu</small>"));
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0070A9")));
        // insert icon back for actionbar
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
    }

    private void toolbarNavigation() {
        Toolbar toolbar = findViewById(R.id.coin_toolbar);
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
                            tvCoins.setText(users.getNumberOfCoins() + "");
                        }
                    }
                    @Override
                    public void onFailure(Call<Users> call, Throwable t) {
                        tvCoins.setText("Lỗi!");
                    }
                });
    }
}