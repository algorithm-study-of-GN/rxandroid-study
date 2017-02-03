package com.nobrain.rx_study;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

class SearchImageViewHolder extends RecyclerView.ViewHolder {

    public final TextView tv;

    public SearchImageViewHolder(View itemView) {
        super(itemView);
        tv = (TextView) itemView.findViewById(R.id.tv_item_image);
    }
}
