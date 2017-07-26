package com.hvt.english.ui.exam.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.github.channguyen.rsv.RangeSliderView;
import com.hvt.english.MyApplication;
import com.hvt.english.R;
import com.hvt.english.model.Meaning;
import com.hvt.english.ui.base.BaseActivity;
import com.hvt.english.ui.exam.ContinueQuestionListener;
import com.hvt.english.ui.exam.listen.ListenExamFragment;
import com.hvt.english.ui.exam.voice.VoiceExamFragment;
import com.hvt.english.ui.streak.StreakActivity;

import butterknife.BindView;


public class ExamActivity extends BaseActivity implements ExamContract.View, ContinueQuestionListener {

    public static final String DATA_MEANING = "DATA_MEANING";

    public static final String DATA_WORD_EXAM = "DATA_WORD_EXAM";

    public static final String DATA_SENTENCE_EXAM = "DATA_SENTENCE_EXAM";

    ExamContract.Presenter presenter;

    @BindView(R.id.rsv_small)
    RangeSliderView rsvSmall;

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        presenter.loadData(getIntent().getExtras());
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_exam;
    }

    @Override
    public void attachView() {
        if (presenter == null) {
            presenter = new ExamPresenter(MyApplication.getApplication().getApiClient());
        }
        presenter.onAttach(this);
    }

    @Override
    public void detachView() {
        presenter.onDetach();
    }

    @Override
    public void showVoiceQuestion(Meaning question) {
        changeFragment(VoiceExamFragment.newInstance(question));
    }

    @Override
    public void showListenQuestion(Meaning question) {
        changeFragment(ListenExamFragment.newInstance(question));
    }

    private void changeFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }

    @Override
    public void updateProgressBar(int current) {
        rsvSmall.setCurrentIndex(current);
    }

    @Override
    public void updateScoreView(int score) {

    }

    @Override
    public void updateProgressCount(int maxCount) {
        rsvSmall.setRangeCount(maxCount);
    }

    @Override
    public void showGoalToday(int scoreArchive) {
        finish();
        StreakActivity.navigate(this, StreakActivity.EXAM, scoreArchive);
    }

    @Override
    public void resultAnswer(Meaning question, boolean correct) {
        presenter.updateResult(question, correct);
    }

    @Override
    public void onContinue() {
        presenter.clickNext();
    }
}
