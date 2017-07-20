package com.hvt.english.ui.categorydetail;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.hvt.english.MyApplication;
import com.hvt.english.R;
import com.hvt.english.ui.base.BaseFragment;
import com.hvt.english.ui.categorydetail.adapter.SectionAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by doannh on 7/20/17.
 */

public class CategoryDetailFragment extends BaseFragment implements CategoryDetailContract.View {

    public static final String CATEGORY_ID_DATA = "CATEGORY_ID_DATA";

    @BindView(R.id.vp_section)
    ViewPager vpSection;

    SectionAdapter adapter;

    CategoryDetailContract.Presenter presenter;

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
        vpSection.setClipToPadding(false);
        vpSection.setPadding(170, 0, 170, 0);
        vpSection.setPageMargin(50);
        adapter = new SectionAdapter(getChildFragmentManager());
        vpSection.setAdapter(adapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_detail;
    }

    @Override
    public Unbinder bindingView(View view) {
        return ButterKnife.bind(this, view);
    }

    @Override
    public void showSections(List<Fragment> sections) {
        adapter.setData(sections);
        adapter.notifyDataSetChanged();
    }
}
