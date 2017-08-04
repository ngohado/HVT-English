package com.hvt.english.ui.categorydetail;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hvt.english.MyApplication;
import com.hvt.english.R;
import com.hvt.english.ui.base.BaseActivity;
import com.hvt.english.ui.categorydetail.adapter.SectionAdapter;

import java.util.List;

import butterknife.BindView;

public class CategoryDetailActivity extends BaseActivity implements CategoryDetailContract.View {

    public static final String CATEGORY_ID_DATA = "CATEGORY_ID_DATA";

    @BindView(R.id.vp_section)
    ViewPager vpSection;

    @BindView(R.id.iv_category)
    ImageView ivCategory;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    SectionAdapter adapter;

    CategoryDetailContract.Presenter presenter;

    public static void navigate(Context context, int categoryID) {
        Intent intent = new Intent(context, CategoryDetailActivity.class);
        intent.putExtra(CATEGORY_ID_DATA, categoryID);
        context.startActivity(intent);
    }

    @Override
    public void attachView() {
        if (presenter == null) {
            presenter = new CategoryDetailPresenter(MyApplication.getApplication().getApiClient());
        }
        presenter.onAttach(this);
    }

    @Override
    public void detachView() {
        presenter.onDetach();
    }

    @Override
    public void initView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        vpSection.setClipToPadding(false);
        vpSection.setPadding(170, 0, 170, 0);
        vpSection.setPageMargin(50);
        adapter = new SectionAdapter(getSupportFragmentManager());
        vpSection.setAdapter(adapter);
    }

    @Override
    public void initData() {
        presenter.loadDataSections(getIntent().getIntExtra(CATEGORY_ID_DATA, 0));
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_detail;
    }

    @Override
    public void showSections(List<Fragment> sections) {
        adapter.setData(sections);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showCategoryImage(String image) {
        Glide.with(this).load(image).into(ivCategory);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
