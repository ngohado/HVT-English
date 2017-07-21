package com.hvt.english.ui.exam.listen;

import android.os.Bundle;

import com.hvt.english.model.Meaning;
import com.hvt.english.network.ApiClient;
import com.hvt.english.ui.base.BasePresenter;
import com.hvt.english.ui.exam.main.ExamActivity;

/**
 * Created by doannh on 7/18/17.
 */

public class ListenExamPresenter extends BasePresenter<ListenExamContract.View> implements ListenExamContract.Presenter {

    private Meaning question;

    public ListenExamPresenter(ApiClient apiClient) {
        super(apiClient);
    }

    @Override
    public void loadQuestionPractice(Bundle data) {
        Meaning question = data.getParcelable(ExamActivity.DATA_MEANING);
        this.question = question;
        getView().showQuestionPractice(question);
    }

    @Override
    public void submitAnswer(String answerVoice) {
        getView().showResult(question.content.equalsIgnoreCase(answerVoice));
    }

    @Override
    public void clickSound() {

    }
}
