package com.example.appbookstore;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class LibraryFragment extends Fragment {

    ListView lvThuVien;
    ArrayList<BookLibrary> bookArrayList;
    BookLibraryAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library, container, false);
        lvThuVien = (ListView) view.findViewById(R.id.listviewThuVien);
        danhSach();
        adapter = new BookLibraryAdapter(getActivity(), R.layout.dong_thuvien, bookArrayList);
        lvThuVien.setAdapter(adapter);
        lvThuVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), layout_Detail1.class);
                startActivity(intent);
            }
        });
        return view;
    }
    private void danhSach() {
        bookArrayList = new ArrayList<>();
        bookArrayList.add(new BookLibrary("Tạo dựng cuộc sống ý nghĩa",
                R.drawable.th1, "Alex Nguyen", R.drawable.download));
        bookArrayList.add(new BookLibrary("Tự Học Kỳ Môn Độn Giáp - Binh Pháp",
                R.drawable.th2, "Nguyễn Thành Phương", R.drawable.saved));
        bookArrayList.add(new BookLibrary("Tự Học Kỳ Môn Độn Giáp - Phân tích đầu tư kinh doanh",
                R.drawable.th3, "Nguyễn Thành Phương", R.drawable.download));
        bookArrayList.add(new BookLibrary("Cẩm Nang Phong Thủy Chiêm Tinh 12 Con Giáp năm Nhâm Dần 2022",
                R.drawable.th4, "Nguyễn Thành Phương", R.drawable.saved));
        bookArrayList.add(new BookLibrary("Nghĩ Giàu và Làm Giàu",
                R.drawable.th5, "Napoleon Hill", R.drawable.saved));
    }
}