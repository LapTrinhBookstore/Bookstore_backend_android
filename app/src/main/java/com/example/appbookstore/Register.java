package com.example.appbookstore;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class Register extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        setColorStatusBar();
        TextView txv = (TextView)findViewById(R.id.textView36);
        txv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setColorStatusBar() {
        getWindow().setStatusBarColor(ContextCompat.getColor(Register.this, R.color.c_668CA6));

    }
}
