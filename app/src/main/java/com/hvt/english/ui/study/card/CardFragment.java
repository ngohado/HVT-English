package com.hvt.english.ui.study.card;

import android.view.View;

import com.hvt.english.R;
import com.hvt.english.ui.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Hado on 7/14/17.
 */

public class CardFragment extends BaseFragment {
    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_card;
    }

    @Override
    public Unbinder bindingView(View view) {
        return ButterKnife.bind(this, view);
    }
}
