package com.hvt.english.ui.realexam;

import com.hvt.english.model.Answer;
import com.hvt.english.model.Question;
import com.hvt.english.network.ApiClient;
import com.hvt.english.ui.base.BasePresenter;

/**
 * Created by Hado on 7/18/17.
 */

public class RealExamPresenter extends BasePresenter<RealExamContract.View> implements RealExamContract.Presenter {

    private Question question;

    public RealExamPresenter(ApiClient apiClient) {
        super(apiClient);
    }

    @Override
    public void initQuestion(Question question) {
        this.question = question;
        getView().showQuestion(question);
    }

    @Override
    public void submitAnswer(Answer answer) {
        getView().showResult(answer.isCorrect);
    }
}
