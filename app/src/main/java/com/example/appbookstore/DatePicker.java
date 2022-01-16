package com.example.appbookstore;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DatePicker {

    Calendar calendar = Calendar.getInstance();
    public int dayOfMonth = calendar.get(calendar.DATE);
    public int month = calendar.get(calendar.MONTH);
    public int year = calendar.get(calendar.YEAR);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public void openDatePicker(Context mcontext, TextInputEditText mtext){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                mcontext,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year, month, dayOfMonth);
                        mtext.setText("");
                        mtext.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                },
                year,month,dayOfMonth );
        datePickerDialog.show();
    }
    public void openDatePicker_EditText(Context mcontext, EditText mtext){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                mcontext,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year, month, dayOfMonth);
                        mtext.setText("");
                        mtext.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                },
                year,month,dayOfMonth );
        datePickerDialog.show();
    }
}
