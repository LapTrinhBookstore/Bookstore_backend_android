package com.example.appbookstore;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class HomeFragment extends Fragment {

    private LinearLayout llBook1;
    private LinearLayout llBook2;
    private LinearLayout llBook3;
    private LinearLayout llBook4;
    private LinearLayout llBook5;
    private LinearLayout llBook6;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        llBook1 = (LinearLayout) view.findViewById(R.id.layoutBook1);
        llBook1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutClicked();
            }
        });
        llBook2 = (LinearLayout) view.findViewById(R.id.layoutBook2);
        llBook2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutClicked();
            }
        });
        llBook3 = (LinearLayout) view.findViewById(R.id.layoutBook3);
        llBook3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutClicked();
            }
        });
        llBook4 = (LinearLayout) view.findViewById(R.id.layoutBook4);
        llBook4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutClicked();
            }
        });
        llBook5 = (LinearLayout) view.findViewById(R.id.layoutBook5);
        llBook5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutClicked();
            }
        });
        llBook6 = (LinearLayout) view.findViewById(R.id.layoutBook6);
        llBook6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutClicked();
            }
        });
        return view;
    }
    public void layoutClicked() {
        Intent intent = new Intent(getActivity(), layout_Detail1.class);
        startActivity(intent);
    }
}