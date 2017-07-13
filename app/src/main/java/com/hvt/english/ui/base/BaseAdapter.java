package com.hvt.english.ui.base;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Hado on 7/7/17.
 */

public abstract class BaseAdapter<VH extends BaseViewHolder<?>> extends RecyclerView.Adapter<VH> {
    protected OnClickItemListener listener;

    public BaseAdapter() {

    }

    public BaseAdapter(OnClickItemListener listener) {
        this.listener = listener;
    }

    public void setItemClickListener(OnClickItemListener listener) {
        this.listener = listener;
    }
}
