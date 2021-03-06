package com.hvt.english.ui.exam.voice;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hvt.english.MyApplication;
import com.hvt.english.R;
import com.hvt.english.model.Meaning;
import com.hvt.english.ui.base.BaseFragment;
import com.hvt.english.ui.exam.ContinueQuestionListener;
import com.hvt.english.ui.exam.main.ExamActivity;
import com.hvt.english.util.SoundUtils;
import com.hvt.english.widget.CustomFontTextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Hado on 7/13/17.
 */

public class VoiceExamFragment extends BaseFragment implements VoiceExamContract.View {

    private final int REQ_CODE_SPEECH_INPUT = 100;

    @BindView(R.id.iv_sound)
    ImageView ivSound;
    @BindView(R.id.iv_idea)
    ImageButton ivIdea;
    @BindView(R.id.tv_content)
    CustomFontTextView tvContent;
    @BindView(R.id.tv_try_again)
    CustomFontTextView tvTryAgain;
    @BindView(R.id.btn_speech)
    ImageButton btnSpeech;
    @BindView(R.id.layout_total)
    RelativeLayout layoutTotal;

    private VoiceExamContract.Presenter presenter;

    ContinueQuestionListener continueQuestionListener;

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
        layoutTotal.setBackgroundColor(getResources().getColor(correct ? R.color.exam_color_correct : R.color.exam_color_incorrect));
        tvTryAgain.setVisibility(correct ? View.INVISIBLE : View.VISIBLE);
    }

    @Override
    public void updateMainView(Meaning question, boolean correct) {
        if (continueQuestionListener != null) {
            continueQuestionListener.resultAnswer(question, correct);
        }
    }


    public void setContinueQuestionListener(ContinueQuestionListener continueQuestionListener) {
        this.continueQuestionListener = continueQuestionListener;
    }


    @Override
    public void showPlaySound(boolean show) {
        ivSound.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void showIdea(boolean show) {
        ivIdea.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void showContent(boolean show) {
        tvContent.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void showQuestionPractice(Meaning meaning) {
        tvContent.setText(meaning.content);
    }

    @Override
    public void showGoogleRecognizeSpeech() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getContext().getApplicationContext(),
                    "This feature do not support to your device",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void playSound(String url) {
        SoundUtils.playSound(getContext(), url, () -> ivSound.setEnabled(true));
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

    @OnClick({R.id.iv_sound, R.id.btn_speech, R.id.btn_continue})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_sound:
                ivSound.setEnabled(false);
                presenter.clickSound();
                break;
            case R.id.btn_speech:
                presenter.clickSpeech();
                break;
            case R.id.btn_continue:
                if (continueQuestionListener != null) {
                    continueQuestionListener.onContinue();
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    presenter.submitAnswer(result);
                }
                break;
            }

        }
    }
}
