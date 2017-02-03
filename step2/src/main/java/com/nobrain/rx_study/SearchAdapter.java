package com.nobrain.rx_study;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

class SearchAdapter extends RecyclerView.Adapter<SearchImageViewHolder> {

    private List<SearchResult.SearchImage> images;

    public SearchAdapter() {
        images = new ArrayList<>();
    }

    @Override
    public SearchImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new SearchImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchImageViewHolder holder, int position) {
        SearchResult.SearchImage item = getItem(position);
        holder.tv.setText(item.title);
    }

    private SearchResult.SearchImage getItem(int position) {
        return images.get(position);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public void add(SearchResult.SearchImage item) {
        images.add(item);
    }

    public void clear() {
        images.clear();
    }
}
