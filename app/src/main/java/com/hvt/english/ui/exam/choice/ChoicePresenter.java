package com.hvt.english.ui.exam.choice;

import com.hvt.english.model.Question;
import com.hvt.english.network.ApiClient;
import com.hvt.english.ui.base.BasePresenter;

/**
 * Created by Hado on 7/13/17.
 */

public class ChoicePresenter extends BasePresenter<ChoiceView> {
    private Question question;

    public ChoicePresenter(ApiClient apiClient) {
        super(apiClient);
    }

    public void showQuestion(Question question) {
        this.question = question;
        getView().displayQuestion(question);
    }

    /**
     *
     * @param answer: 1 - A, 2 - B, 3 - C, 4 - D
     */
    public void handleAnswer(int answer) {

    }

    public void showSuggestion() {

    }

    public void tryAgain() {
        getView().displayQuestion(question);
    }
}
