package com.hvt.english.ui.exam.voice;

import android.view.View;

import com.hvt.english.R;
import com.hvt.english.model.Question;
import com.hvt.english.ui.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Hado on 7/13/17.
 */

public class VoiceExamFragment extends BaseFragment implements VoiceExamView {
    @Override
    public void initView() {

    }

    @Override
    public void initData() {

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
    public void displayQuestion(Question question) {

    }

    @Override
    public void displayResult(boolean correct) {

    }

    @Override
    public void displaySuggestion(String suggestion) {

    }

    @Override
    public void attachView() {

    }

    @Override
    public void detachView() {

    }
}
