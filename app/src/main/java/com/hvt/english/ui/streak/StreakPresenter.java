package com.hvt.english.ui.streak;

import android.os.Bundle;

import com.hvt.english.Constant;
import com.hvt.english.R;
import com.hvt.english.network.ApiClient;
import com.hvt.english.ui.base.BasePresenter;
import com.hvt.english.util.SharedPrefUtil;

/**
 * Created by Hado on 7/13/17.
 */

public class StreakPresenter extends BasePresenter<StreakContract.View> implements StreakContract.Presenter {

    public StreakPresenter(ApiClient apiClient) {
        super(apiClient);
    }

    @Override
    public void loadData(Bundle data) {
        int points = data.getInt(StreakFragment.POINT_DATA);
        getView().showScorePlus(points);

        int todayPoint = dataManager.getTodayPoints();

        int type = data.getInt(StreakFragment.TYPE_DATA);
        getView().showResultTitle(type == StreakFragment.PRACTICE ? R.string.streak_title_practice_complete : R.string.streak_title_exam_complete);

        float percent = Math.round(todayPoint / SharedPrefUtil.getInstance().getInt(Constant.GOALS_SCORE_DATA));
        getView().showProgressGoals(Math.min(percent, 100), todayPoint);

        int streakDay = dataManager.getStreakDay();
        getView().showProgressStreakDay(streakDay);

        if (todayPoint < SharedPrefUtil.getInstance().getInt(Constant.GOALS_SCORE_DATA)) {
            getView().showBoardInfo(String.format("You need %d point to complete today's target",
                    SharedPrefUtil.getInstance().getInt(Constant.GOALS_SCORE_DATA) - todayPoint));
        } else {
            getView().showBoardInfo(String.format("You're now on a %d day streak", streakDay));
        }
    }

    @Override
    public void clickContinue() {
        getView().closeStreakScreen();
    }
}
