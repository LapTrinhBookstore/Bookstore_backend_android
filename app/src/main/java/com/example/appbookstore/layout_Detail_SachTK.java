package com.example.appbookstore;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class layout_Detail_SachTK extends AppCompatActivity {
    private RecyclerView rv_SachTK;
    private SachTimKiemAdapter sachTimKiemAdapter;
    private TextView textView;
    private String keyWord;
    private Dialog searchDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_detail_sach_tk);

        setColorStatusBar();
        toolbarNavigation();

        Intent intent = getIntent();
        keyWord = intent.getStringExtra("keyWord");
        HienThiTuKhoa(keyWord);

        rv_SachTK = findViewById(R.id.rv_SachTK);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_SachTK.setLayoutManager(linearLayoutManager);

        List<SachObj> list = KetQuaTimKiem(keyWord);
        sachTimKiemAdapter = new SachTimKiemAdapter(list);
        rv_SachTK.setAdapter(sachTimKiemAdapter);
        KhongCoKetQua(list.size());

    }

    private void HienThiTuKhoa(String tuKhoa) {
        textView = findViewById(R.id.sachTK_tuKhoa);
        textView.setText(tuKhoa);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Search();
            }
        });
    }

    private List<SachObj> KetQuaTimKiem(String keyWord) {
        List<SachObj> list = new ArrayList<>();
        List<SachObj> allSach = getAllSach();
        for(SachObj sach : allSach) {
            if(sach.getTenSach().toLowerCase().contains(keyWord.toLowerCase()))
                list.add(sach);
        }

        return list;
    }

    private List<SachObj> getAllSach() {
        List<SachObj> list = new ArrayList<>();

        list.add(new SachObj(R.drawable.demo, "Winter", "Nguyễn Nga", "Sách điện tử", 4.2f, 150000));
        list.add(new SachObj(R.drawable.demo, "Winter blues", "Nguyễn Nga", "Sách điện tử", 4.2f, 150000));
        list.add(new SachObj(R.drawable.demo, "Winter soldier", "Nguyễn Nga", "Sách điện tử", 4.2f, 150000));
        list.add(new SachObj(R.drawable.demo, "Winter princess", "Nguyễn Nga", "Sách điện tử", 4.2f, 150000));
        list.add(new SachObj(R.drawable.demo, "The hobbit", "Nguyễn Nga", "Sách điện tử", 4.2f, 150000));
        list.add(new SachObj(R.drawable.demo, "Hẹn anh lúc nữa đêm", "Nguyễn Nga", "Sách điện tử", 4.2f, 150000));
        list.add(new SachObj(R.drawable.demo, "Transformer", "Nguyễn Nga", "Sách điện tử", 4.2f, 150000));
        list.add(new SachObj(R.drawable.demo, "The History of the Hobbit", "Nguyễn Nga", "Sách điện tử", 4.2f, 150000));
        list.add(new SachObj(R.drawable.demo, "The Lord of the Ring", "Nguyễn Nga", "Sách điện tử", 4.2f, 150000));
        list.add(new SachObj(R.drawable.demo, "Batman vs Superman", "Nguyễn Nga", "Sách điện tử", 4.2f, 150000));
        list.add(new SachObj(R.drawable.demo, "Spider Man", "Nguyễn Nga", "Sách điện tử", 4.2f, 150000));
        list.add(new SachObj(R.drawable.demo, "Doctor Strange", "Nguyễn Nga", "Sách điện tử", 4.2f, 150000));
        list.add(new SachObj(R.drawable.demo, "Star war", "Nguyễn Nga", "Sách điện tử", 4.2f, 150000));

        return list;
    }

    private Dialog ShowDialog(int layout) {
        Dialog dialog = new Dialog(this, android.R.style.Theme_Light_NoTitleBar);
        dialog.setContentView(layout);

        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        dialog.getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        dialog.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        dialog.show();

        return dialog;
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(searchDialog != null) {
            searchDialog.cancel();
        }
    }

    public void Search() {
        searchDialog = ShowDialog(R.layout.activity_layout_detail_search);

        SearchView searchView = (SearchView) searchDialog.findViewById(R.id.book_search);
        SearchAdapter searchAdapter = new SearchAdapter(getListBooks(), getHistorySearch(), searchView, layout_Detail_SachTK.this, searchDialog);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView rv_searchHistory = searchDialog.findViewById(R.id.rv_searchHistory);

        searchView.onActionViewExpanded();
        searchView.setQuery(keyWord, false);
        ChinhSuaSearchView(searchView, searchDialog);

        rv_searchHistory.setLayoutManager(linearLayoutManager);
        rv_searchHistory.setAdapter(searchAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(layout_Detail_SachTK.this, layout_Detail_SachTK.class);
                intent.putExtra("keyWord", searchView.getQuery().toString());
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    private void ChinhSuaSearchView(SearchView searchView, Dialog dialog) {
        ImageView closeBtn = searchView.findViewById(androidx.appcompat.R.id.search_close_btn);
        closeBtn.setBackground(getResources().getDrawable(R.drawable.bg_btn_arrow_up_left));
        closeBtn.setFocusable(true);

        Toolbar toolbar = dialog.findViewById(R.id.search_toolbar);
        toolbar.setNavigationOnClickListener(view -> dialog.cancel());


        EditText searchEditText = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.c_00204A));
        searchEditText.setHintTextColor(getResources().getColor(R.color.c_4000204A));
        Typeface tf = ResourcesCompat.getFont(layout_Detail_SachTK.this,R.font.googlesans_regular);
        searchEditText.setTypeface(tf);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 0) {
                    searchEditText.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_search), null);
                } else {
                    searchEditText.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private List<SearchObj> getHistorySearch() {
        List<SearchObj> list = new ArrayList<>();
        list.add(new SearchObj(R.drawable.ic_clock,"The hobbit"));
        list.add(new SearchObj(R.drawable.ic_clock,"Chien tranh giua cac vi sao"));
        list.add(new SearchObj(R.drawable.ic_clock,"transformer"));
        list.add(new SearchObj(R.drawable.ic_clock,"chu cuoi"));
        list.add(new SearchObj(R.drawable.ic_clock,"Hoa cỏ xanh"));

        return list;
    }

    private List<SearchObj> getListBooks() {
        List<SearchObj> list = new ArrayList<>();
        list.add(new SearchObj(R.drawable.ic_ant_design_search_outlined, "Winter"));
        list.add(new SearchObj(R.drawable.ic_ant_design_search_outlined, "Winter blues"));
        list.add(new SearchObj(R.drawable.ic_ant_design_search_outlined, "Winter soldier"));
        list.add(new SearchObj(R.drawable.ic_ant_design_search_outlined, "Winter princess"));
        list.add(new SearchObj(R.drawable.ic_ant_design_search_outlined, "The hobbit"));
        list.add(new SearchObj(R.drawable.ic_ant_design_search_outlined, "Hẹn anh lúc nữa đêm"));
        list.add(new SearchObj(R.drawable.ic_ant_design_search_outlined, "Transformer"));
        list.add(new SearchObj(R.drawable.ic_ant_design_search_outlined, "The History of the Hobbit"));
        list.add(new SearchObj(R.drawable.ic_ant_design_search_outlined, "The Lord of the Ring"));
        list.add(new SearchObj(R.drawable.ic_ant_design_search_outlined, "Batman vs Superman"));
        list.add(new SearchObj(R.drawable.ic_ant_design_search_outlined, "Spider Man"));
        list.add(new SearchObj(R.drawable.ic_ant_design_search_outlined, "Doctor Strange"));
        list.add(new SearchObj(R.drawable.ic_ant_design_search_outlined, "Star war"));

        return list;
    }

    private void KhongCoKetQua(int listSize) {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.sachTK_khongCoKetQua);
        if (listSize == 0) {
            linearLayout.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;
        } else {
            linearLayout.getLayoutParams().height = 0;
        }
    }

    private void setColorStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        getWindow().setStatusBarColor(ContextCompat.getColor(layout_Detail_SachTK.this,R.color.white));
    }

    private void toolbarNavigation() {
        Toolbar toolbar = findViewById(R.id.sachTK_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
    }
}