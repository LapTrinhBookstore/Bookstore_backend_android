package com.example.appbookstore;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class ViewPageAdapter extends FragmentStatePagerAdapter {
    private List<Pages> mListpages;
    public ViewPageAdapter(@NonNull FragmentManager fm, int behavior,List<Pages> list) {
        super(fm, behavior);
        this.mListpages = list;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Pages page = mListpages.get(position);
        PageFragment pageFragment = new PageFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data",page);

        pageFragment.setArguments(bundle);
        return pageFragment;
    }

    @Override
    public int getCount() {
        if(mListpages != null){
            return mListpages.size();
        }
        return 0;
    }
}