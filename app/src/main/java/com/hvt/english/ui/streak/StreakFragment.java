package com.hvt.english.ui.streak;

import android.view.View;

import com.hvt.english.MyApplication;
import com.hvt.english.R;
import com.hvt.english.ui.base.BaseFragment;
import com.hvt.english.widget.CustomFontTextView;
import com.shinelw.library.ColorArcProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Hado on 7/13/17.
 */

public class StreakFragment extends BaseFragment implements StreakContract.View {

    public static final String POINT_DATA = "POINT_DATA";
    public static final String TYPE_DATA = "TYPE_DATA";
    public static final int EXAM = 1;
    public static final int PRACTICE = 2;

    @BindView(R.id.pb_today)
    ColorArcProgressBar pbToday;

    @BindView(R.id.tv_title)
    CustomFontTextView tvTitle;

    @BindView(R.id.tv_score_plus)
    CustomFontTextView tvScorePlus;

    @BindView(R.id.tv_board_info)
    CustomFontTextView tvBoardInfo;

    StreakContract.Presenter presenter;

    @Override
    public void initView() {
        pbToday.setCurrentValues(70, 1000);
    }

    @Override
    public void initData() {
//        presenter.loadData(getArguments());
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
        if (presenter == null) {
            presenter = new StreakPresenter(MyApplication.getApplication().getApiClient());
        }
        presenter.onAttach(this);
    }

    @Override
    public void detachView() {
        presenter.onDetach();
    }


    @OnClick(R.id.btn_continue)
    public void onViewClicked() {
        presenter.clickContinue();
    }

    @Override
    public void showResultTitle(int content) {
        tvTitle.setText(content);
    }

    @Override
    public void showScorePlus(int score) {
        tvScorePlus.setText(String.format("+%d points", score));
    }

    @Override
    public void showProgressGoals(float percent, int points) {
        pbToday.setCurrentValues(percent, points);
    }

    @Override
    public void showProgressStreakDay(int day) {
        pbToday.setTitleString(String.format("%d day streak"));
    }

    @Override
    public void showBoardInfo(String content) {
        tvBoardInfo.setText(content);
    }

    @Override
    public void closeStreakScreen() {

    }
}
