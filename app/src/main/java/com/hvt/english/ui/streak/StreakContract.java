package com.hvt.english.ui.streak;

import android.os.Bundle;

import com.hvt.english.ui.base.BaseView;
import com.hvt.english.ui.base.IBasePresenter;

/**
 * Created by doannh on 7/19/17.
 */

public interface StreakContract {

    interface View extends BaseView {
        void showResultTitle(int string);

        void showScorePlus(int score);

        void showProgressGoals(float percent, int points);

        void showProgressStreakDay(int day);

        void showBoardInfo(String content);

        void closeStreakScreen();
    }


    interface Presenter extends IBasePresenter<View> {
        void loadData(Bundle data);

        void clickContinue();
    }
}

