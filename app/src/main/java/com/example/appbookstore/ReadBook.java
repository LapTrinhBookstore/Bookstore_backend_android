package com.example.appbookstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.appbookstore.model.HomeBook;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ReadBook extends AppCompatActivity {
    TextView back, next, currentPage, totalPage;
    ViewPager viewPager;
    List<Pages> mListPage;
    ViewPageAdapter adapter;
    private String url = "http://192.168.1.3/Bookstore_android/public/bookstore/getPageBook.php?idproduct=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_book);
        toolbarNavigation();
        setColorStatusBar();
        AnhXa();
        mListPage = new ArrayList<>();
        Intent intent = getIntent();
        int id = intent.getIntExtra("idbook", 1);
        url = url + id;

        adapter = new ViewPageAdapter(getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, mListPage);
        viewPager.setAdapter(adapter);
        GetData(url);
        currentPage.setText("1");


        //Sự kiện chuyển viewPager
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                currentPage.setText(String.valueOf(position + 1));
            }

            @Override
            public void onPageSelected(int position) {
                currentPage.setText(String.valueOf(position - 1));
                if (position == 0) {
                    back.setVisibility(View.GONE);
                    next.setVisibility(View.VISIBLE);
                } else if (position == mListPage.size() - 1) {
                    next.setVisibility(View.GONE);
                    back.setVisibility(View.VISIBLE);
                } else {
                    back.setVisibility(View.VISIBLE);
                    next.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // Back - next
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            }
        });
    }

    public void AnhXa() {
        back = findViewById(R.id.back);
        next = findViewById(R.id.next);
        currentPage = findViewById(R.id.current_page);
        totalPage = findViewById(R.id.total_page);
        viewPager = findViewById(R.id.view_pager);
    }

//    private List<Pages> getPageList() {
//        List<Pages> list = new ArrayList<>();
//        for (int i = 1; i <= 10; i++) {
//            list.add(new Pages("Content " + i));
//        }
//        return list;
//    }

    private void GetData(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                mListPage.add(new Pages(
                                        object.getString("imageName")
                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();
                        totalPage.setText(String.valueOf(mListPage.size()));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ReadBook.this, "Loi!", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
    private void toolbarNavigation() {
        Toolbar toolbar = findViewById(R.id.docSach_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
    }

    private void setColorStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        getWindow().setStatusBarColor(ContextCompat.getColor(ReadBook.this, R.color.white));

    }
}