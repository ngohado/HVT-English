package com.hvt.english.ui.category;

import android.view.View;

import com.hvt.english.R;
import com.hvt.english.model.Category;
import com.hvt.english.ui.base.BaseFragment;
import com.hvt.english.ui.base.OnClickItemListener;
import com.hvt.english.ui.category.adapter.CategoryAdapter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Hado on 7/13/17.
 */

public class CategoryFragment extends BaseFragment implements OnClickItemListener, CategoryContract.View {

    private CategoryPresenter presenter;

    private CategoryAdapter adapter = new CategoryAdapter();

    @Override
    public void initView() {
        adapter.setOnClickItemListener(this);
    }

    @Override
    public void initData() {
        presenter.loadCategories();
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_category;
    }

    @Override
    public Unbinder bindingView(View view) {
        return ButterKnife.bind(this, view);
    }

    @Override
    public void onItemClicked(int position) {
        presenter.clickCategory(position);
    }

    @Override
    public void showCategories(List<Category> categories) {

    }

    @Override
    public void openDetailCategoryUI(int categoryID) {

    }
}
