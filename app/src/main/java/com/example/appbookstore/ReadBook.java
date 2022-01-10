package com.example.appbookstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ReadBook extends AppCompatActivity {
    TextView back, next, currentPage, totalPage;
    ViewPager viewPager;
    List<Pages> mListPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_book);
        toolbarNavigation();
        setColorStatusBar();
        AnhXa();
        mListPage = getPageList();
        ViewPageAdapter adapter = new ViewPageAdapter(getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, mListPage);
        viewPager.setAdapter(adapter);
        currentPage.setText("1");
        totalPage.setText(String.valueOf(mListPage.size()));

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

    private List<Pages> getPageList() {
        List<Pages> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            list.add(new Pages("Content " + i));
        }
        return list;
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