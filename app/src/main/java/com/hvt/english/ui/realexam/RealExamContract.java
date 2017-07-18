package com.hvt.english.ui.realexam;

import com.hvt.english.model.Answer;
import com.hvt.english.model.Question;
import com.hvt.english.ui.base.BaseView;

/**
 * Created by Hado on 7/18/17.
 */

public interface RealExamContract {

    interface View extends BaseView {
        void showQuestion(Question question);

        void showResult(boolean correct);
    }

    interface Presenter {
        void initQuestion(Question question);

        void submitAnswer(Answer answer);
    }
}
