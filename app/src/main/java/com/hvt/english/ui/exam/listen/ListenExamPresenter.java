package com.hvt.english.ui.exam.listen;

import com.hvt.english.model.Question;
import com.hvt.english.network.ApiClient;
import com.hvt.english.ui.base.BasePresenter;

/**
 * Created by doannh on 7/18/17.
 */

public class ListenExamPresenter extends BasePresenter<ListenExamView> {

    private Question question;

    public ListenExamPresenter(ApiClient apiClient) {
        super(apiClient);
    }

    public void handleAnswer(String answer) {

    }

    public void showSuggestion() {

    }

    public void tryAgain() {
        getView().displayQuestion(question);
    }
}
