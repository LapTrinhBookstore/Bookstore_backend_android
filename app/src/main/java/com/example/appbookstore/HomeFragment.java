package com.example.appbookstore;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.appbookstore.adapter.HomeGoiYAdapter;
import com.example.appbookstore.adapter.HomeTheLoaiAdapter;
import com.example.appbookstore.model.HomeBook;
import com.example.appbookstore.my_interface.IClickItemBookListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView rcvTheLoai;
    private RecyclerView rcvviewGoiY;

    private HomeTheLoaiAdapter theLoaiAdapter;
    private HomeGoiYAdapter goiYAdapter;

    List<HomeBook> listGY;
    List<HomeBook> listTL;

    private String urlGetData = "https://bookstoreandroid.000webhostapp.com/bookstore2/getSachGoiY.php";
    private String urlGetTheLoai = "https://bookstoreandroid.000webhostapp.com/bookstore2/getSachTheLoai.php?idcategory=1";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //Anh xa
        rcvTheLoai = view.findViewById(R.id.recyclerviewTheLoai);
        rcvviewGoiY = view.findViewById(R.id.recyclerviewGoiY);

        //Load sach theo the loai
        listTL = new ArrayList<>();
        theLoaiAdapter = new HomeTheLoaiAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        //myRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcvTheLoai.setLayoutManager(linearLayoutManager);
        rcvTheLoai.setFocusable(false);
        rcvTheLoai.setNestedScrollingEnabled(false);
        theLoaiAdapter.setData(listTL, new IClickItemBookListener() {
            @Override
            public void onClichItemBook(HomeBook book) {
                onCliclToDetail(book);
            }
        });
        rcvTheLoai.setAdapter(theLoaiAdapter);
        GetDataTheLoai(urlGetTheLoai);

        //Load sach goi y danh cho ban
        goiYAdapter = new HomeGoiYAdapter();
        listGY = new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        rcvviewGoiY.setLayoutManager(gridLayoutManager);
        rcvviewGoiY.setFocusable(false);
        rcvviewGoiY.setNestedScrollingEnabled(false);
        goiYAdapter.setData(listGY, new IClickItemBookListener() {
            @Override
            public void onClichItemBook(HomeBook book) {
                onCliclToDetail(book);
            }
        });
        rcvviewGoiY.setAdapter(goiYAdapter);
        GetData(urlGetData);


        return view;
    }

    private void GetData(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                listGY.add(new HomeBook(
                                        object.getInt("id"),
                                        object.getString("productImg"),
                                        object.getString("name"),
                                        object.getInt("price")
                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        goiYAdapter.notifyDataSetChanged();
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
    private void GetDataTheLoai(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                listTL.add(new HomeBook(
                                        object.getInt("id"),
                                        object.getString("productImg"),
                                        object.getString("name"),
                                        object.getInt("price")
                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        theLoaiAdapter.notifyDataSetChanged();
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
    private void onCliclToDetail(HomeBook book){
        Intent intent = new Intent(getActivity(), layout_Detail1.class);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("book", book);
        intent.putExtra("idbook", book.getId());
        intent.putExtra("iduser", 1);
        startActivity(intent);
    }
}