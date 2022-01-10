package com.example.appbookstore;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.Fragment;

public class OptionFragment extends Fragment {
    RelativeLayout rlUser;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_option, container, false);
        rlUser = (RelativeLayout) view.findViewById(R.id.relativeLayoutUser);
        rlUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), profile_bookstore.class);
                startActivity(intent);
            }
        });
        return view;
    }
}