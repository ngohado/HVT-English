package com.hvt.english.ui.categorydetail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.hvt.english.MyApplication;
import com.hvt.english.R;
import com.hvt.english.ui.base.BaseActivity;
import com.hvt.english.ui.categorydetail.adapter.SectionAdapter;

import java.util.List;
import java.util.Random;

import butterknife.BindView;

public class CategoryDetailActivity extends BaseActivity implements CategoryDetailContract.View {

    public static final String CATEGORY_ID_DATA = "CATEGORY_ID_DATA";
    public static final String CATEGORY_NAME_DATA = "CATEGORY_NAME_DATA";
    public static final String CATEGORY_IMAGE_DATA = "CATEGORY_IMAGE_DATA";

    @BindView(R.id.vp_section)
    ViewPager vpSection;

    @BindView(R.id.iv_category)
    ImageView ivCategory;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.container)
    RelativeLayout container;

    int mainColor;

    SectionAdapter adapter;

    CategoryDetailContract.Presenter presenter;

    public static void navigate(Context context, int categoryID, String name, String image) {
        Intent intent = new Intent(context, CategoryDetailActivity.class);
        intent.putExtra(CATEGORY_ID_DATA, categoryID);
        intent.putExtra(CATEGORY_NAME_DATA, name);
        intent.putExtra(CATEGORY_IMAGE_DATA, image);
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

        Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        vpSection.setClipToPadding(false);
        vpSection.setPadding(170, 0, 170, 0);
        vpSection.setPageMargin(50);
        adapter = new SectionAdapter(getSupportFragmentManager());
        vpSection.setAdapter(adapter);

        int[] colorsBackground = getResources().getIntArray(R.array.detail_background);
        mainColor = colorsBackground[new Random().nextInt(colorsBackground.length - 1)];
        container.setBackgroundColor(mainColor);
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        String name = intent.getStringExtra(CATEGORY_NAME_DATA);
        showTitleCategory(name);
        String image = intent.getStringExtra(CATEGORY_IMAGE_DATA);
        showCategoryImage(image);
        presenter.loadDataSections(getIntent().getIntExtra(CATEGORY_ID_DATA, 0), mainColor);
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
    public void showTitleCategory(String title) {
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
