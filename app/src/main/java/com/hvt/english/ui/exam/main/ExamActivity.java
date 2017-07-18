package com.hvt.english.ui.exam.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.hvt.english.MyApplication;
import com.hvt.english.R;
import com.hvt.english.model.Meaning;
import com.hvt.english.ui.base.BaseActivity;
import com.hvt.english.ui.exam.listen.ListenExamFragment;
import com.hvt.english.ui.exam.voice.VoiceExamFragment;


public class ExamActivity extends BaseActivity implements ExamContract.View {

    public static final String DATA_MEANING = "DATA_MEANING";

    public static final String DATA_CATEGORY_ID = "DATA_CATEGORY_ID";

    ExamContract.Presenter presenter;

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        int categoryId = getIntent().getIntExtra(DATA_CATEGORY_ID, 0);
        presenter.loadQuestions(categoryId);
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
    public void updateProgressBar(int current, int total) {

    }

    @Override
    public void updateScoreView(int score) {

    }

    @Override
    public void showGoalToday(int scoreArchive) {

    }
}
