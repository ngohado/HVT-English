package com.hvt.english.ui.category.adapter.viewholder;

import android.view.View;

import com.hvt.english.model.Category;
import com.hvt.english.ui.base.BaseViewHolder;
import com.hvt.english.ui.base.OnClickItemListener;

import butterknife.ButterKnife;

public class CategoryViewHolder extends BaseViewHolder<Category> {

    public CategoryViewHolder(View itemView, OnClickItemListener listener) {
        super(itemView, listener);
    }

    @Override
    public void bindData(Category data) {

    }

    @Override
    public void bindView() {
        ButterKnife.bind(this, itemView);
    }
}
