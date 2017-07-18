package com.hvt.english.ui.exam.voice;

import com.hvt.english.model.Question;
import com.hvt.english.network.ApiClient;
import com.hvt.english.ui.base.BasePresenter;

/**
 * Created by Hado on 7/13/17.
 */

public class VoiceExamPresenter extends BasePresenter<VoiceExamView> {

    private Question question;

    public VoiceExamPresenter(ApiClient apiClient) {
        super(apiClient);
    }

    public void showQuestion(Question question) {
        this.question = question;
        getView().displayQuestion(question);
    }

    public void handleAnswer(String answerVoice) {

    }

    public void showSuggestion() {

    }

    public void tryAgain() {
        getView().displayQuestion(question);
    }
}
