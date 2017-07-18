package com.hvt.english.ui.exam.main;

import com.hvt.english.model.Question;
import com.hvt.english.ui.base.BaseView;

/**
 * Created by Hado on 7/13/17.
 */

public interface ExamView extends BaseView {

    void displayChoiceQuestion(Question question);

    void displayVoiceQuestion(Question question);

    void displayListenQuestion(Question question);

}
