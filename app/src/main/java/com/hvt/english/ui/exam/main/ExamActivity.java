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
import com.hvt.english.util.DialogUtils;

import butterknife.BindView;
import butterknife.OnClick;


public class ExamActivity extends BaseActivity implements ExamContract.View, ContinueQuestionListener {

    public static final String DATA_MEANING = "DATA_MEANING";

    public static final String DATA_MEANING_EXAM = "CARD_SECTION_DATA";

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
        VoiceExamFragment fragment = VoiceExamFragment.newInstance(question);
        fragment.setContinueQuestionListener(this);
        changeFragment(fragment);
    }

    @Override
    public void showListenQuestion(Meaning question) {
        ListenExamFragment fragment = ListenExamFragment.newInstance(question);
        fragment.setContinueQuestionListener(this);
        changeFragment(fragment);
    }

    private void changeFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }

    @Override
    public void updateProgressBar(int current) {
        rsvSmall.setCurrentIndex(current - 1);
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
    public void showDialogConfirmExit() {
        DialogUtils.showDialog(this, "Exit practice", "You're practicing!! Are you want to exit class?", "Exit", sweetAlertDialog -> {
            sweetAlertDialog.dismiss();
            finish();
        });
    }

    @Override
    public void resultAnswer(Meaning question, boolean correct) {
        presenter.updateResult(question, correct);
    }

    @OnClick(R.id.tv_close)
    public void closeOnClick() {
        showDialogConfirmExit();
    }

    @Override
    public void onContinue() {
        presenter.clickNext();
    }
}
