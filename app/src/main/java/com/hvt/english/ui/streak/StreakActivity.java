package com.hvt.english.ui.streak;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hvt.english.MyApplication;
import com.hvt.english.R;
import com.hvt.english.ui.base.BaseActivity;
import com.hvt.english.widget.CustomFontTextView;
import com.shinelw.library.ColorArcProgressBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Hado on 7/13/17.
 */

public class StreakActivity extends BaseActivity implements StreakContract.View {

    public static final String POINT_DATA = "POINT_DATA";
    public static final String TYPE_DATA = "TYPE_DATA";

    public static final int EXAM = 1;
    public static final int PRACTICE = 2;
    public static final int STUDY = 3;

    public static void navigate(Context context, int previousScreen, int scoreAddition) {
        Intent intent = new Intent(context, StreakActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(TYPE_DATA, previousScreen);
        bundle.putInt(POINT_DATA, scoreAddition);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

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
        presenter.loadData(getIntent().getExtras());
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_streak;
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
        pbToday.setTitleString(String.format("%d day streak", day));
    }

    @Override
    public void showBoardInfo(String content) {
        tvBoardInfo.setText(content);
    }

    @Override
    public void closeStreakScreen() {
        finish();
    }
}
