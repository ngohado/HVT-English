package com.hvt.english.ui.realexam;

import com.hvt.english.model.Answer;
import com.hvt.english.model.Question;
import com.hvt.english.ui.base.BaseView;
import com.hvt.english.ui.base.IBasePresenter;

/**
 * Created by Hado on 7/18/17.
 */

public interface RealExamContract {

    interface View extends BaseView {
        void showQuestion(Question question);

        void showResult(boolean correct);

        void updateRemain(long count, String remainingString);

        void showStreakScreen(int point, int type);

        void updateProgressCount(int maxCount);

        void updateProgressBar(int current);
    }

    interface Presenter extends IBasePresenter<View> {
        void initQuestion(int categoryID);

        void nextQuestion();

        void submitAnswer(Answer answer);

        void startCountDown();

        void exitCountDown();
    }
}
