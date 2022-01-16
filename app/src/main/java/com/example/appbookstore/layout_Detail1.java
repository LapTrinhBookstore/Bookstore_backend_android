package com.example.appbookstore;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appbookstore.api.ApiService;
import com.example.appbookstore.model.DetailBook;
import com.example.appbookstore.model.LibraryBook;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.imageview.ShapeableImageView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class layout_Detail1 extends AppCompatActivity {
    RatingBar ratingBar1;
    DanhGiaAdapter danhGiaAdapter;
    Dialog searchDialog;
    DetailBook detailBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_detail1);

        ratingBar1 = findViewById(R.id.user_ratingBar);

        Intent intent = getIntent();
        int idbook= intent.getIntExtra("idbook", 0);
        int iduser = intent.getIntExtra("iduser", 0);
        getBook(iduser,idbook);

        setColorStatusBar();
        toolbarNavigation();
        LoadDanhGia(idbook);
        RatingBarChanged();
        VietDanhGiaClick();
        MuaSachClick(iduser, idbook);
    }

    // call api
    private void getBook(int idUser, int idProduct){
        ApiService.apiService.getBook(idUser, idProduct).enqueue(new Callback<DetailBook>() {
            @Override
            public void onResponse(Call<DetailBook> call, Response<DetailBook> response) {
                DetailBook book = response.body();
                ConstraintLayout detail_layout = findViewById(R.id.detail_layout);
                TextView book_name = findViewById(R.id.book_name),
                        book_author = findViewById(R.id.book_author),
                        book_nxb = findViewById(R.id.book_nxb),
                        book_star = findViewById(R.id.book_star),
                        slDG = findViewById(R.id.slDG),
                        book_page = findViewById(R.id.book_page),
                        book_intro = findViewById(R.id.book_intro),
                        btn_tenTG = findViewById(R.id.btn_tenTG),
                        book_author_intro = findViewById(R.id.book_author_intro),
                        big_rating = findViewById(R.id.big_rating),
                        slDG_2 = findViewById(R.id.slDG_2);

                ShapeableImageView book_avt = findViewById(R.id.book_avt);
                AppCompatButton book_muaSach = findViewById(R.id.book_muaSach);
                SvgRatingBar book_5sao = findViewById(R.id.book_5sao);


                book_name.setText(book.getName());
                book_nxb.setText(book.getPublisher());
                book_author.setText(book.getAuthor());
                book_page.setText(book.getPageNumber() + "");
                book_star.setText( book.getStar() + "");
                String str = String.format(Locale.US, "%,d", (int) book.getPrice()).replace(',', '.');

                if(book.getBought() == 0)
                    book_muaSach.setText( str + " đ");
                else
                    book_muaSach.setText("Đọc sách");

                slDG.setText(book.getSlDG() + " đánh giá");
                book_intro.setText(book.getIntroduction());
                btn_tenTG.setText(book.getAuthor());
                book_author_intro.setText(book.getAuthorIntro());
                book_5sao.setRating((float) book.getStar());
                slDG_2.setText(book.getSlDG() + "");
                big_rating.setText(book.getStar() + "");

                Glide.with(layout_Detail1.this).load("http://192.168.1.7/android/Bookstore/public/bookstore/image/" + book
                        .getProductImg()).into(book_avt);
                detail_layout.getLayoutParams().height = ConstraintLayout.LayoutParams.MATCH_PARENT;
                detailBook = book;
            }

            @Override
            public void onFailure(Call<DetailBook> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }


    private void MuaSachClick(int iduser, int idbook) {
        Button muaSach = findViewById(R.id.book_muaSach);
        muaSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   if(muaSach.getText() != "Đọc sách") {
                       ShowThanhToanDialog();
                   } else {
                       Intent intent = new Intent(layout_Detail1.this, ReadBook.class);
                       intent.putExtra("idbook", idbook);
                       intent.putExtra("iduser", iduser);
                       startActivity(intent);
                   }
            }
        });
    }

    private void ShowThanhToanDialog() {
        final BottomSheetDialog dialog = new BottomSheetDialog(this, R.style.BottomSheetDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_bottom_sheet_thanh_toan);
        dialog.show();

        TextView tongTien = dialog.findViewById(R.id.thanhToan_tongTien),
                tienGiam = dialog.findViewById(R.id.thanhTOan_tienGiam);
        CheckBox checkBox = dialog.findViewById(R.id.thanhToan_soXu);
        Button thanhToan_btnThanhToan = dialog.findViewById(R.id.thanhToan_btnThanhToan);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBox.isChecked()) {
                    checkBox.setTextColor(getResources().getColor(R.color.c_00204A));
                    tienGiam.setTextColor(getResources().getColor(R.color.c_F4900C));
                    tongTien.setText("155.000 đ");
                } else {
                    checkBox.setTextColor(getResources().getColor(R.color.c_60000000));
                    tienGiam.setTextColor(getResources().getColor(R.color.c_DAA55E));
                    tongTien.setText("170.000 đ");
                }
            }
        });
        thanhToan_btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button muaSach = findViewById(R.id.book_muaSach);
                muaSach.setText("Đọc sách");
                dialog.cancel();
            }
        });
    }

    private void setColorStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        getWindow().setStatusBarColor(ContextCompat.getColor(layout_Detail1.this,R.color.white));
    }

    private void toolbarNavigation() {
        Toolbar toolbar = findViewById(R.id.detail1_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
    }

    public void GioiThieuSach(View view) {
        Intent intent = new Intent(layout_Detail1.this, layout_Detail_GioiThieuSach.class);
        Bundle bundle = new Bundle();
        bundle.putString("name", detailBook.getName());
        bundle.putString("img", detailBook.getProductImg());
        bundle.putString("intro", detailBook.getIntroduction());
        bundle.putString("lang", detailBook.getLanguage());
        bundle.putString("nxb", detailBook.getPublisher());
        bundle.putString("ngayxb", detailBook.getPublishDate());
        bundle.putInt("trang", detailBook.getPageNumber());
        bundle.putString("cate", detailBook.getCate());
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void GioiThieuTacGia(View view) {
        Intent intent = new Intent(layout_Detail1.this, layout_Detail_GTTacGia.class);
        Bundle bundle = new Bundle();
        bundle.putString("name", detailBook.getName());
        bundle.putString("img", detailBook.getProductImg());
        bundle.putString("intro", detailBook.getAuthorIntro());
        intent.putExtras(bundle);
        startActivity(intent);
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
        SearchAdapter searchAdapter = new SearchAdapter(getListBooks(), getHistorySearch(), searchView, layout_Detail1.this, searchDialog);

        searchView.onActionViewExpanded();
        ChinhSuaSearchView(searchView, searchDialog);

        rv_searchHistory.setLayoutManager(linearLayoutManager);
        rv_searchHistory.setAdapter(searchAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(layout_Detail1.this, layout_Detail_SachTK.class);
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
        Typeface tf = ResourcesCompat.getFont(layout_Detail1.this,R.font.googlesans_regular);
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

    public void XemDanhGia(View view) {
        Intent intent = new Intent(layout_Detail1.this, layout_Detail_XemDanhGia.class);
        startActivity(intent);
    }

    public void DanhGiaSach() {
        Intent intent = new Intent(layout_Detail1.this, layout_Detail_DanhGiaSach.class);
        intent.putExtra("rating", ratingBar1.getRating());
        startActivity(intent);
    }

    private void VietDanhGiaClick() {
        Button vietDanhGia = findViewById(R.id.btn_vietDanhGia);
        vietDanhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DanhGiaSach();
            }
        });
    }

    private void RatingBarChanged() {
        ratingBar1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                DanhGiaSach();
            }
        });
    }

    private void LoadDanhGia(int idProduct) {
        ApiService.apiService.getTopRating(idProduct).enqueue(new Callback<List<DanhGiaObj>>() {
            @Override
            public void onResponse(Call<List<DanhGiaObj>> call, Response<List<DanhGiaObj>> response) {
                List<DanhGiaObj> list = response.body();
                RecyclerView rv_DanhGia;
                danhGiaAdapter = new DanhGiaAdapter(list);
                danhGiaAdapter.setContext(layout_Detail1.this);
                rv_DanhGia = findViewById(R.id.book_top3DG);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(layout_Detail1.this, LinearLayoutManager.VERTICAL, false);
                rv_DanhGia.setLayoutManager(linearLayoutManager);
                rv_DanhGia.setAdapter(danhGiaAdapter);
            }

            @Override
            public void onFailure(Call<List<DanhGiaObj>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail1_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.detail1_search) {
            Search();
        }

        return super.onOptionsItemSelected(item);
    }
}