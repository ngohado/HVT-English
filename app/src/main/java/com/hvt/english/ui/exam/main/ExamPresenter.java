package com.hvt.english.ui.exam.main;

import android.os.Bundle;

import com.hvt.english.Constant;
import com.hvt.english.model.Meaning;
import com.hvt.english.network.ApiClient;
import com.hvt.english.ui.base.BasePresenter;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import io.reactivex.Observable;

/**
 * Created by Hado on 7/13/17.
 */

public class ExamPresenter extends BasePresenter<ExamContract.View> implements ExamContract.Presenter {

    public ExamPresenter(ApiClient apiClient) {
        super(apiClient);
    }

    public List<Meaning> questions;

    private int currentQuestion = -1;

    private int currentScore = 0;

    @Override
    public void loadData(Bundle bundle) {
        questions = bundle.getParcelableArrayList(ExamActivity.DATA_MEANING_EXAM);
        Collections.shuffle(questions);
        getView().updateProgressCount(questions.size());
        nextQuestion();
    }

    @Override
    public void updateResult(Meaning meaning, boolean isCorrect) {
        Meaning currentQuestion = Observable.fromIterable(questions)
                .filter(m -> m.id == meaning.id)
                .blockingFirst();
        currentQuestion.correct = isCorrect;
        if (isCorrect) {
            currentScore += Constant.SCORE_PER_QUESTION_PRACTICE_DEF;
            getView().updateScoreView(currentScore);
        }
    }

    @Override
    public void clickNext() {
        if (currentQuestion == questions.size() - 1) {
            getView().updateProgressBar(currentQuestion);
            dataManager.savePoints(currentScore);
            getView().showGoalToday(currentScore);
        } else {
            nextQuestion();
        }
    }

    private void nextQuestion() {
        currentQuestion++;
        Meaning nextQuestion = questions.get(currentQuestion);
        if (new Random().nextInt() % 2 == 0) {
            getView().showListenQuestion(nextQuestion);
        } else {
            getView().showVoiceQuestion(nextQuestion);
        }
        getView().updateProgressBar(currentQuestion);
    }
}
