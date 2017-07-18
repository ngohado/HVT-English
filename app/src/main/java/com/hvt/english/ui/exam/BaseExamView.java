package com.hvt.english.ui.exam;

import com.hvt.english.model.Question;
import com.hvt.english.ui.base.BaseView;

/**
 * Created by doannh on 7/18/17.
 */

public interface BaseExamView extends BaseView {
    void displayQuestion(Question question);

    void displayResult(boolean correct);

    void displaySuggestion(String suggestion);
}
