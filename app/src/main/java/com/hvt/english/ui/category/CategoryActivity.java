package com.hvt.english.ui.category;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.hvt.english.MyApplication;
import com.hvt.english.R;
import com.hvt.english.model.Category;
import com.hvt.english.ui.base.BaseActivity;
import com.hvt.english.ui.base.OnClickItemListener;
import com.hvt.english.ui.category.adapter.CategoryAdapter;
import com.hvt.english.ui.categorydetail.CategoryDetailActivity;
import com.hvt.english.widget.SettingsDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CategoryActivity extends BaseActivity implements OnClickItemListener, CategoryContract.View {

    private CategoryContract.Presenter mPresenter;

    private CategoryAdapter mCategoryAdapter = new CategoryAdapter();

    @BindView(R.id.rv_category)
    RecyclerView rvCategories;
    @BindView(R.id.toolbar)
    Toolbar toolbar;



    @Override
    public void initView() {
        setSupportActionBar(toolbar);
        rvCategories.setLayoutManager(new GridLayoutManager(this, 3));
        rvCategories.setAdapter(mCategoryAdapter);
        mCategoryAdapter.setOnClickItemListener(this);
    }

    @Override
    public void initData() {
        mPresenter.loadCategories();
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_category;
    }

    @Override
    public void onItemClicked(int position) {
        mPresenter.chooseCategory(position);
    }

    @Override
    public void showCategories(List<Category> categories) {
        mCategoryAdapter.addData(categories);
    }

    @Override
    public void openDetailCategoryUI(int categoryID, String categoryName, String categoryImage) {
        CategoryDetailActivity.navigate(this, categoryID, categoryName, categoryImage);

    }

    @Override
    public void attachView() {
        if (mPresenter == null) {
            mPresenter = new CategoryPresenter(MyApplication.getApplication().getApiClient());
        }
        mPresenter.onAttach(this);
    }

    @OnClick(R.id.btn_settings)
    public void settingsOnClick() {
        SettingsDialog.newInstance(this).show();
    }

    @Override
    public void detachView() {
        mPresenter.onDetach();
    }
}
