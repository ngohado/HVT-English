package com.hvt.english.ui.exam.main;

import com.hvt.english.model.Meaning;
import com.hvt.english.ui.base.BaseView;
import com.hvt.english.ui.base.IBasePresenter;

/**
 * Created by Hado on 7/13/17.
 */

public interface ExamContract {
    interface View extends BaseView {
        void showVoiceQuestion(Meaning question);

        void showListenQuestion(Meaning question);

        void updateProgressBar(int current, int total);

        void updateScoreView(int score);

        void showGoalToday(int scoreArchive);
    }

    interface Presenter extends IBasePresenter<View> {
        void loadQuestions(int categoryId);

        void updateResult(Meaning meaning, boolean isCorrect);

        void clickNext();
    }


}
