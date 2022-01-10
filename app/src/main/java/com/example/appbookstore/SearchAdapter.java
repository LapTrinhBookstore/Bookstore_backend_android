package com.example.appbookstore;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.searchViewHolder> implements Filterable {
    private List<SearchObj> listKeyWords;
    private List<SearchObj> listBooks;
    private List<SearchObj> historySearch;
    private SearchView searchView;
    private Context context;
    private Dialog dialog;



    public SearchAdapter(List<SearchObj> listBooks, List<SearchObj> historySearch, SearchView searchView, Context context, Dialog dialog) {
        this.listBooks = listBooks;
        this.historySearch = historySearch;
        listKeyWords = historySearch;
        this.searchView = searchView;
        this.context = context;
        this.dialog = dialog;
    }

    @NonNull
    @Override
    public searchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_seach, parent,false);
        return new searchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull searchViewHolder holder, int position) {
        SearchObj searchObj = listKeyWords.get(position);
        if(searchObj == null)
            return;
        holder.tv_keyword.setText(Html.fromHtml(searchObj.getKeyWord()));
        holder.tv_search_icon.setCompoundDrawablesWithIntrinsicBounds(searchObj.getIcon(), 0, 0, 0);
        holder.im_arrowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchView.setQuery(holder.tv_keyword.getText().toString(),false);
                searchView.setIconified(false);
                searchView.requestFocusFromTouch();
            }
        });
        holder.lt_keyWordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, layout_Detail_SachTK.class);
                intent.putExtra("keyWord", holder.tv_keyword.getText().toString());
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        if(listKeyWords != null) {
            return listKeyWords.size();

        }
        return 0;
    }

    public class searchViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_keyword;
        private TextView tv_search_icon;
        private ImageButton im_arrowBtn;
        private LinearLayout lt_keyWordBtn;

        public searchViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_keyword = itemView.findViewById(R.id.search_txt);
            im_arrowBtn = itemView.findViewById(R.id.search_arrowBtn);
            lt_keyWordBtn = itemView.findViewById(R.id.search_keyword);
            tv_search_icon = itemView.findViewById(R.id.search_icon);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String searchStr = charSequence.toString().toLowerCase().trim();
                FilterResults filterResults = new FilterResults();
                String text = "";

                if(searchStr.isEmpty()) {
                    listKeyWords = historySearch;
                    filterResults.values = listKeyWords;
                    return filterResults;
                }
                List<SearchObj> list = new ArrayList<>();
                for(SearchObj item : listBooks) {
                    text = item.getKeyWord().toLowerCase();
                    if(text.contains(searchStr)) {
                        text = text.replace(searchStr, "<b>" + searchStr + "</b>");
                        list.add(new SearchObj(item.getIcon(), text));
                    }
                }
                listKeyWords = list;
                filterResults.values = listKeyWords;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listKeyWords = (List<SearchObj>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}