package com.hvt.english.ui.exam.voice;

import android.os.Bundle;

import com.hvt.english.model.Meaning;
import com.hvt.english.network.ApiClient;
import com.hvt.english.ui.base.BasePresenter;
import com.hvt.english.ui.exam.main.ExamActivity;

/**
 * Created by Hado on 7/13/17.
 */

public class VoiceExamPresenter extends BasePresenter<VoiceExamContract.View> implements VoiceExamContract.Presenter {

    private Meaning question;

    public VoiceExamPresenter(ApiClient apiClient) {
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

    }
}
