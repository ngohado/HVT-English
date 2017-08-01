package com.hvt.english.ui.category.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hvt.english.R;
import com.hvt.english.model.Category;
import com.hvt.english.ui.base.BaseViewHolder;
import com.hvt.english.ui.base.OnClickItemListener;
import com.hvt.english.util.font.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryViewHolder extends BaseViewHolder<Category> {

    @BindView(R.id.iv_category)
    ImageView ivCategory;

    @BindView(R.id.tv_category_name)
    TextView tvName;

    public CategoryViewHolder(View itemView, OnClickItemListener listener) {
        super(itemView, listener);
    }

    @Override
    public void bindData(Category data) {
        StringUtils.setText(tvName, data.name);
        Glide.with(itemView.getContext()).load(data.image).into(ivCategory);
    }

    @Override
    public void bindView() {
        ButterKnife.bind(this, itemView);
    }
}
