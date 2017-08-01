package com.hvt.english.ui.category.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hvt.english.R;
import com.hvt.english.model.Category;
import com.hvt.english.ui.base.BaseAdapter;
import com.hvt.english.ui.base.OnClickItemListener;
import com.hvt.english.ui.category.adapter.viewholder.CategoryViewHolder;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends BaseAdapter<CategoryViewHolder> {

    private OnClickItemListener listener;

    private List<Category> categories = new ArrayList<>();

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        holder.bindData(categories.get(position));
    }

    public void setOnClickItemListener(OnClickItemListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public void addData(List<Category> data) {
        categories.clear();
        categories.addAll(data);
        notifyDataSetChanged();
    }
}
