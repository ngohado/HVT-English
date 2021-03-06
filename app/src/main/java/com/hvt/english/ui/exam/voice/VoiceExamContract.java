package com.hvt.english.ui.exam.voice;

import android.os.Bundle;

import com.hvt.english.model.Meaning;
import com.hvt.english.ui.base.IBasePresenter;
import com.hvt.english.ui.exam.BaseExamView;

import java.util.ArrayList;

/**
 * Created by Hado on 7/13/17.
 */

public interface VoiceExamContract {
    interface View extends BaseExamView {
        void showPlaySound(boolean show);

        void showIdea(boolean show);

        void showContent(boolean show);

        void showQuestionPractice(Meaning meaning);

        void showGoogleRecognizeSpeech();

        void playSound(String url);
    }

    interface Presenter extends IBasePresenter<View> {
        void loadQuestionPractice(Bundle data);

        void submitAnswer(ArrayList<String> answersVoice);

        void clickSound();

        void clickSpeech();
    }
}
