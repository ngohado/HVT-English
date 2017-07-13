package com.hvt.english.ui.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    protected OnClickItemListener listener;

    public BaseViewHolder(View itemView) {
        super(itemView);
        bindView();
    }

    public BaseViewHolder(View itemView, OnClickItemListener listener) {
        super(itemView);
        bindView();
        this.listener = listener;
        itemView.setOnClickListener(view -> {
            if (BaseViewHolder.this.listener != null) {
                BaseViewHolder.this.listener.onItemClicked(getAdapterPosition());
            }
        });
    }

    public abstract void bindData(T data);

    public abstract void bindView();
}
