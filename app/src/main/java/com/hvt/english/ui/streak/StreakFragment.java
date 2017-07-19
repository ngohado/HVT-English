package com.hvt.english.ui.streak;

import android.view.View;

import com.hvt.english.R;
import com.hvt.english.ui.base.BaseFragment;
import com.shinelw.library.ColorArcProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Hado on 7/13/17.
 */

public class StreakFragment extends BaseFragment implements StreakContract.View {

    @BindView(R.id.pb_today)
    ColorArcProgressBar pbToday;

    @Override
    public void initView() {
        pbToday.setCurrentValues(98);
    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_streak;
    }

    @Override
    public Unbinder bindingView(View view) {
        return ButterKnife.bind(this, view);
    }

    @Override
    public void attachView() {

    }

    @Override
    public void detachView() {

    }
}
