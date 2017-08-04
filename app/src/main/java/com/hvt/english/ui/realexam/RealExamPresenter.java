package com.hvt.english.ui.realexam;

import android.util.Pair;

import com.hvt.english.Constant;
import com.hvt.english.model.Answer;
import com.hvt.english.model.Question;
import com.hvt.english.network.ApiClient;
import com.hvt.english.ui.base.BasePresenter;
import com.hvt.english.ui.streak.StreakActivity;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;


/**
 * Created by Hado on 7/18/17.
 */

public class RealExamPresenter extends BasePresenter<RealExamContract.View> implements RealExamContract.Presenter {

    private List<Question> questions;
    private int currentQuestion = 0;
    private int currentPoint = 0;

    public RealExamPresenter(ApiClient apiClient) {
        super(apiClient);
    }

    @Override
    public void initQuestion(int categoryId) {
        dataManager.getQuestion(categoryId)
                .doOnSubscribe(disposable -> {
                    compositeDisposable.add(disposable);
                    getView().showLoading();
                })
                .doFinally(() -> getView().hideLoading())
                .subscribe(questions -> {
                    this.questions = questions;
                    getView().updateProgressCount(questions.size());
                    getView().showQuestion(questions.get(currentQuestion));
                }, error -> getView().showError(error.getMessage()));
    }

    @Override
    public void nextQuestion() {
        currentQuestion++;
        if (currentQuestion < questions.size()) {
            getView().updateProgressBar(currentQuestion);
            getView().showQuestion(questions.get(currentQuestion));
        } else {
            dataManager.savePoints(currentPoint);
            getView().showStreakScreen(currentPoint, StreakActivity.EXAM);
        }
    }

    @Override
    public void submitAnswer(Answer answer) {
        if (answer == null) {
            getView().showResult(false);
        } else {
            if (answer.isCorrect) currentPoint += Constant.SCORE_PER_QUESTION_EXAM_DEF;

            getView().showResult(answer.isCorrect);
        }
    }

    @Override
    public void startCountDown() {
        compositeDisposable.add(Observable.interval(1, TimeUnit.SECONDS)
                .take(Constant.TIME_PER_QUESTION)
                .map(count -> {
                    long mod = count % 4;
                    StringBuilder remainString = new StringBuilder("remaining");
                    for (int i = 0; i < mod; i++) {
                        remainString.append(".");
                    }

                    long realCount = 30 - count;
                    return new Pair<>(realCount, remainString.toString());
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(pair -> getView().updateRemain(pair.first, pair.second)));
    }

    @Override
    public void exitCountDown() {
        compositeDisposable.clear();
    }
}
