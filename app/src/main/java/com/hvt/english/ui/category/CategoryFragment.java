package com.hvt.english.ui.category;

import android.view.View;

import com.hvt.english.R;
import com.hvt.english.ui.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Hado on 7/13/17.
 */

public class CategoryFragment extends BaseFragment {

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_category;
    }

    @Override
    public Unbinder bindingView(View view) {
        return ButterKnife.bind(this, view);
    }
}
