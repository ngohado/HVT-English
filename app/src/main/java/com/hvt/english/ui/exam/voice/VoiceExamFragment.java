package com.hvt.english.ui.exam.voice;

import android.os.Bundle;
import android.view.View;

import com.hvt.english.MyApplication;
import com.hvt.english.R;
import com.hvt.english.model.Meaning;
import com.hvt.english.ui.base.BaseFragment;
import com.hvt.english.ui.exam.main.ExamActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Hado on 7/13/17.
 */

public class VoiceExamFragment extends BaseFragment implements VoiceExamContract.View {

    private VoiceExamContract.Presenter presenter;

    public static VoiceExamFragment newInstance(Meaning meaning) {
        VoiceExamFragment fragment = new VoiceExamFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ExamActivity.DATA_MEANING, meaning);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        presenter.loadQuestionPractice(getArguments());
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_voice_exam;
    }

    @Override
    public Unbinder bindingView(View view) {
        return ButterKnife.bind(this, view);
    }


    @Override
    public void attachView() {
        if (presenter == null) {
            presenter = new VoiceExamPresenter(MyApplication.getApplication().getApiClient());
        }
        presenter.onAttach(this);
    }

    @Override
    public void detachView() {
        presenter.onDetach();
    }

    @Override
    public void showResult(boolean correct) {

    }

    @Override
    public void showQuestionPractice(Meaning meaning) {

    }
}
