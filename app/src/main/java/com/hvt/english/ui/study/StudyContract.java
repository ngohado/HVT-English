package com.hvt.english.ui.study;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.hvt.english.ui.base.BaseView;
import com.hvt.english.ui.base.IBasePresenter;

import java.util.List;

/**
 * Created by Hado on 7/20/17.
 */

public interface StudyContract {
    interface View extends BaseView {
        void showStudyContent(List<Fragment> cards);

        void showStreakScreen(int point, int type);

        void showDialogConfirmExit();

        void changeCard(int atPosition);

    }

    interface Presenter extends IBasePresenter<View> {
        void loadData(Bundle data);

        void clickNext(int currentPosition);

        void clickBack(int currentPosition);
    }
}
