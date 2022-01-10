package com.example.appbookstore;

import static androidx.fragment.app.FragmentStatePagerAdapter.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    private ViewPager vpgNoiDung;
    private BottomNavigationView bnvMenu;
    private ImageButton imSearch;
    private Dialog searchDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        setColorStatusBar();


        vpgNoiDung = (ViewPager) findViewById(R.id.viewPagerNoiDung);
        bnvMenu = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        imSearch = (ImageButton) findViewById(R.id.imageButtonSearch);

        ViewPagerAdapter vpAdapter = new ViewPagerAdapter(getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpgNoiDung.setAdapter(vpAdapter);

        vpgNoiDung.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bnvMenu.getMenu().findItem(R.id.menu_home).setChecked(true);
                        break;
                    case 1:
                        bnvMenu.getMenu().findItem(R.id.menu_popular).setChecked(true);
                        break;
                    case 2:
                        bnvMenu.getMenu().findItem(R.id.menu_library).setChecked(true);
                        break;
                    case 3:
                        bnvMenu.getMenu().findItem(R.id.menu_option).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bnvMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_home:
                        vpgNoiDung.setCurrentItem(0);
                        break;
                    case R.id.menu_popular:
                        vpgNoiDung.setCurrentItem(1);
                        break;
                    case R.id.menu_library:
                        vpgNoiDung.setCurrentItem(2);
                        break;
                    case R.id.menu_option:
                        vpgNoiDung.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });

        imSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Search();
            }
        });
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

    private void Search() {
        searchDialog = ShowDialog(R.layout.activity_layout_detail_search);

        SearchView searchView = searchDialog.findViewById(R.id.book_search);
        RecyclerView rv_searchHistory = searchDialog.findViewById(R.id.rv_searchHistory);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        SearchAdapter searchAdapter = new SearchAdapter(getListBooks(), getHistorySearch(), searchView, Home.this, searchDialog);

        searchView.onActionViewExpanded();
        ChinhSuaSearchView(searchView, searchDialog);

        rv_searchHistory.setLayoutManager(linearLayoutManager);
        rv_searchHistory.setAdapter(searchAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(Home.this, layout_Detail_SachTK.class);
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
        Typeface tf = ResourcesCompat.getFont(Home.this,R.font.googlesans_regular);
        searchEditText.setTypeface(tf);
        searchEditText.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_search), null);
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

    private void setColorStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        getWindow().setStatusBarColor(ContextCompat.getColor(Home.this,R.color.white));
    }
}
