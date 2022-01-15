package com.example.appbookstore;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.appbookstore.adapter.LibraryBookAdapter;
import com.example.appbookstore.adapter.SapXepAdapter;
import com.example.appbookstore.model.LibraryBook;
import com.example.appbookstore.model.SapXep;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LibraryFragment extends Fragment {

    ListView lvThuVien;
    ArrayList<LibraryBook> bookArrayList;
    LibraryBookAdapter adapter;
    private SapXepAdapter sapXepAdapter;
    private String urlGetLibrary = "https://bookstoreandroid.000webhostapp.com/bookstore2/getThuVien.php?iduser=1&sx=";
    private Spinner spnSapXep;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library, container, false);
        lvThuVien = (ListView) view.findViewById(R.id.listviewThuVien);
        spnSapXep = (Spinner) view.findViewById(R.id.spinnerSapXep);
        sapXepAdapter = new SapXepAdapter(getActivity(), R.layout.item_selected, getListSapXep());

        try {
            spnSapXep.setAdapter(sapXepAdapter);
            spnSapXep.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if(sapXepAdapter.getItem(i).getName().equals("Gần đây"))
                        GetData(urlGetLibrary + "purchaseTime");
                    else
                        if(sapXepAdapter.getItem(i).getName().equals("Tiêu đề"))
                            GetData(urlGetLibrary + "name");
                        else
                            GetData(urlGetLibrary + "authorname");
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }catch (Exception e){

        }


        bookArrayList = new ArrayList<>();
        adapter = new LibraryBookAdapter(getActivity(), R.layout.dong_thuvien, bookArrayList);
        lvThuVien.setAdapter(adapter);
        GetData(urlGetLibrary + "purchaseTime");

        lvThuVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                onCliclToDetail(bookArrayList.get(i));
            }
        });
        return view;
    }
    public void GetData(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        bookArrayList.clear();
                        for (int i = 0; i < response.length(); i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                bookArrayList.add(new LibraryBook(
                                        object.getInt("idProduct"),
                                        object.getInt("idUser"),
                                        object.getString("name"),
                                        object.getString("productImg"),
                                        object.getString("authorname")
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
    private void onCliclToDetail(LibraryBook book){
        Intent intent = new Intent(getActivity(), layout_Detail1.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("book", book);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    private List<SapXep> getListSapXep(){
        List<SapXep> list = new ArrayList<>();
        list.add(new SapXep("Gần đây"));
        list.add(new SapXep("Tiêu đề"));
        list.add(new SapXep("Tác giả"));
        return list;
    }
}