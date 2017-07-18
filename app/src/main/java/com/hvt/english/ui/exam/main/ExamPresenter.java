package com.hvt.english.ui.exam.main;

import com.hvt.english.model.Question;
import com.hvt.english.network.ApiClient;
import com.hvt.english.ui.base.BasePresenter;

import java.util.List;

/**
 * Created by Hado on 7/13/17.
 */

public class ExamPresenter extends BasePresenter<ExamView> {

    public ExamPresenter(ApiClient apiClient) {
        super(apiClient);
    }

    public List<Question> questions;

    public void loadQuestions(int categoryId) {

    }

    public void nextQuestion() {

    }

    public void endExam() {

    }
}
