package com.hvt.english.ui.exam.listen;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.hvt.english.MyApplication;
import com.hvt.english.R;
import com.hvt.english.model.Meaning;
import com.hvt.english.ui.base.BaseActivity;
import com.hvt.english.ui.base.BaseFragment;
import com.hvt.english.ui.exam.ContinueQuestionListener;
import com.hvt.english.ui.exam.main.ExamActivity;
import com.hvt.english.util.DialogUtils;
import com.hvt.english.widget.CustomFontButton;
import com.hvt.english.widget.CustomFontEditText;
import com.hvt.english.widget.CustomFontTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;
import butterknife.Unbinder;

/**
 * Created by doannh on 7/18/17.
 */

public class ListenExamFragment extends BaseFragment implements ListenExamContract.View {

    ListenExamContract.Presenter presenter;
    @BindView(R.id.iv_sound)
    ImageView ivSound;
    @BindView(R.id.iv_idea)
    ImageButton ivIdea;
    @BindView(R.id.tv_content)
    CustomFontTextView tvContent;
    @BindView(R.id.edt_compose_answer)
    CustomFontEditText edtComposeAnswer;
    @BindView(R.id.btn_submit)
    CustomFontButton btnSubmit;
    @BindView(R.id.btn_continue)
    CustomFontButton btnContinue;
    @BindView(R.id.layout_total)
    RelativeLayout layoutContainer;

    ContinueQuestionListener continueQuestionListener;


    public static ListenExamFragment newInstance(Meaning meaning) {
        ListenExamFragment fragment = new ListenExamFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ExamActivity.DATA_MEANING, meaning);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void setContinueQuestionListener(ContinueQuestionListener continueQuestionListener) {
        this.continueQuestionListener = continueQuestionListener;
    }

    @Override
    public void attachView() {
        if (presenter == null) {
            presenter = new ListenExamPresenter(MyApplication.getApplication().getApiClient());
        }
        presenter.onAttach(this);
    }

    @Override
    public void detachView() {
        presenter.onDetach();
    }

    @Override
    public void initView() {
        btnContinue.setVisibility(View.INVISIBLE);
    }

    @Override
    public void initData() {
        presenter.loadQuestionPractice(getArguments());
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_listen_practice;
    }

    @Override
    public Unbinder bindingView(View view) {
        return ButterKnife.bind(this, view);
    }

    @Override
    public void showResult(boolean correct) {
        layoutContainer.setBackgroundColor(getResources().getColor(correct ? R.color.exam_color_correct : R.color.exam_color_incorrect));
        btnContinue.setVisibility(View.VISIBLE);
        DialogUtils.showDialogResult(getContext(), correct);
    }

    @Override
    public void updateMainView(Meaning question, boolean correct) {
        if (continueQuestionListener != null) {
            continueQuestionListener.resultAnswer(question, correct);
        }
    }

    @Override
    public void showPlaySound(boolean show) {
//        ivSound.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void showQuestionPractice(Meaning meaning) {
        tvContent.setText(meaning.content);
    }

    @Override
    public void showMeaningOfSentence(String meaning) {
        tvContent.setText(meaning);
        tvContent.setVisibility(View.VISIBLE);
        ivIdea.setVisibility(View.INVISIBLE);
    }

    @Override
    public void playSound(String text) {
//        SoundUtils.playSound(getContext(), url, () -> ivSound.setEnabled(true));
        ((BaseActivity) getActivity()).speakOut(text);
    }


    @OnTouch(R.id.iv_idea)
    public boolean buttonIdeaOnTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                tvContent.setVisibility(View.VISIBLE);
                break;
            case MotionEvent.ACTION_UP:
                tvContent.setVisibility(View.INVISIBLE);
                break;
        }
        return false;
    }

    @OnClick({R.id.iv_sound, R.id.btn_submit, R.id.btn_continue})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_sound:
//                ivSound.setEnabled(false);
                presenter.clickSound();
                break;
            case R.id.btn_submit:
                presenter.submitAnswer(edtComposeAnswer.getText().toString());
                break;
            case R.id.btn_continue:
                if (continueQuestionListener != null) {
                    continueQuestionListener.onContinue();
                }
                break;

        }
    }
}
