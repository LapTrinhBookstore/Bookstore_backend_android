package com.example.appbookstore;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.appbookstore.adapter.PopularBookAdapter;
import com.example.appbookstore.model.PopularBook;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PopularFragment extends Fragment {

    private ListView lvThinhHanh;
    private ArrayList<PopularBook> bookArrayList;
    private PopularBookAdapter adapter;
    private TextView tvBanChay, tvMienPhi;
    private boolean kt = true;
//    private String urlGetBanChay = "http://192.168.1.3/Bookstore_android/public/bookstore/thinhHanhTraPhi.php";
//    private String urlGetMienPhi = "http://192.168.1.3/Bookstore_android/public/bookstore/thinhHanhMienPhi.php";

    private String urlGetBanChay = "http://192.168.1.7/android/Bookstore/public/bookstore/thinhHanhTraPhi.php";
    private String urlGetMienPhi = "http://192.168.1.7/android/Bookstore/public/bookstore/thinhHanhMienPhi.php";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular, container, false);

        lvThinhHanh = (ListView) view.findViewById(R.id.listviewThinhHanh);
        tvBanChay = view.findViewById(R.id.textViewBanChay);
        tvMienPhi = view.findViewById(R.id.textViewMienPhi);

        bookArrayList = new ArrayList<>();
        adapter = new PopularBookAdapter(getActivity(), R.layout.dong_sach, bookArrayList);
        lvThinhHanh.setAdapter(adapter);
        GetData(urlGetBanChay);

        tvBanChay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(kt == false){
                    GetData(urlGetBanChay);
                    kt = true;
                    tvBanChay.setTextColor(Color.parseColor("#2196F3"));
                    tvMienPhi.setTextColor(Color.BLACK);
                }
            }
        });

        tvMienPhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(kt == true){
                    GetData(urlGetMienPhi);
                    kt = false;
                    tvMienPhi.setTextColor(Color.parseColor("#2196F3"));
                    tvBanChay.setTextColor(Color.BLACK);
                }
            }
        });
        lvThinhHanh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                onCliclToDetail(bookArrayList.get(i));
            }
        });
        return view;
    }

    private void GetData(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        bookArrayList.clear();
                        for (int i = 0; i < response.length(); i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                bookArrayList.add(new PopularBook(
                                        i + 1,
                                        object.getInt("id"),
                                        object.getString("productImg"),
                                        object.getString("name"),
                                        object.getString("publisher"),
                                        object.getInt("view"),
                                        object.getInt("price")
                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Loi!", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
    private void onCliclToDetail(PopularBook book){
        Intent intent = new Intent(getActivity(), layout_Detail1.class);
        intent.putExtra("idbook", book.getId());
        intent.putExtra("iduser", 1);
        startActivity(intent);
    }
}