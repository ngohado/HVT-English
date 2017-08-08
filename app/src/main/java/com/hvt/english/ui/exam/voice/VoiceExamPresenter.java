package com.hvt.english.ui.exam.voice;

import android.os.Bundle;

import com.hvt.english.model.Meaning;
import com.hvt.english.network.ApiClient;
import com.hvt.english.ui.base.BasePresenter;
import com.hvt.english.ui.exam.main.ExamActivity;

import java.util.ArrayList;

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
        if (question.audio == null || question.audio.isEmpty()) {
            getView().showPlaySound(false);
            getView().showIdea(false);
            getView().showContent(true);
        } else {
            getView().showPlaySound(true);
            getView().showIdea(true);
            getView().showContent(false);
        }
        getView().showQuestionPractice(question);
    }

    @Override
    public void submitAnswer(ArrayList<String> answersVoice) {
        for (String answer : answersVoice) {
            if (question.content.equalsIgnoreCase(answer)) {
                getView().showResult(true);
                getView().updateMainView(question, true);
                return;
            }
        }
        getView().showResult(false);
        getView().updateMainView(question, false);
    }

    @Override
    public void clickSound() {
        getView().playSound(question.content);
    }

    @Override
    public void clickSpeech() {
        getView().showGoogleRecognizeSpeech();
    }
}
