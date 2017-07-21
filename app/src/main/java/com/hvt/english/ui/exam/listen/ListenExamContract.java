package com.hvt.english.ui.exam.listen;

import android.os.Bundle;

import com.hvt.english.model.Meaning;
import com.hvt.english.ui.base.IBasePresenter;
import com.hvt.english.ui.exam.BaseExamView;

/**
 * Created by doannh on 7/18/17.
 */

public interface ListenExamContract {

    interface View extends BaseExamView {
        void showQuestionPractice(Meaning meaning);
    }

    interface Presenter extends IBasePresenter<View> {
        void loadQuestionPractice(Bundle data);

        void submitAnswer(String answerVoice);

        void clickSound();
    }
}
