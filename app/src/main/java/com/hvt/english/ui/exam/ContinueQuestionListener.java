package com.hvt.english.ui.exam;

import com.hvt.english.model.Meaning;

/**
 * Created by Hado on 7/26/17.
 */

public interface ContinueQuestionListener {
    void resultAnswer(Meaning question, boolean correct);

    void onContinue();
}
