package com.hvt.english.ui.realexam;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.github.channguyen.rsv.RangeSliderView;
import com.hvt.english.Constant;
import com.hvt.english.R;
import com.hvt.english.model.Answer;
import com.hvt.english.model.Question;
import com.hvt.english.ui.base.BaseActivity;
import com.hvt.english.ui.streak.StreakActivity;
import com.hvt.english.util.DialogUtils;
import com.hvt.english.util.font.StringUtils;
import com.hvt.english.widget.CustomFontRadioButton;
import com.hvt.english.widget.CustomFontTextView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class RealExamActivity extends BaseActivity implements RealExamContract.View {

    public static final String DATA_CATEGORY_ID = "DATA_CATEGORY_ID";

    @BindView(R.id.tv_close)
    CustomFontTextView tvClose;
    @BindView(R.id.rsv_small)
    RangeSliderView rsvSmall;
    @BindView(R.id.tv_remaining)
    CustomFontTextView tvRemaining;
    @BindView(R.id.tv_second)
    CustomFontTextView tvSecond;
    @BindView(R.id.tv_question)
    CustomFontTextView tvQuestion;
    @BindView(R.id.rdb_answer_a)
    CustomFontRadioButton rdbAnswerA;
    @BindView(R.id.rdb_answer_b)
    CustomFontRadioButton rdbAnswerB;
    @BindView(R.id.rdb_answer_c)
    CustomFontRadioButton rdbAnswerC;
    @BindView(R.id.rdb_answer_d)
    CustomFontRadioButton rdbAnswerD;
    @BindView(R.id.layout_total)
    RelativeLayout layoutContainer;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.group_answer)
    RadioGroup radioGroup;

    @BindView(R.id.iv_word)
    ImageView ivQuestion;

    Drawable defaultColor;

    RealExamContract.Presenter presenter;

    public static void navigate(Context context, int categoryId) {
        Intent intent = new Intent(context, RealExamActivity.class);
        intent.putExtra(DATA_CATEGORY_ID, categoryId);
        context.startActivity(intent);
    }

    @Override
    public void attachView() {
        if (presenter == null) {
            presenter = new RealExamPresenter(null);
        }
        presenter.onAttach(this);
    }

    @Override
    public void detachView() {
        presenter.onDetach();
    }

    @Override
    public void initView() {
        defaultColor = layoutContainer.getBackground();
    }

    @Override
    public void initData() {
        int categoryId = getIntent().getIntExtra(DATA_CATEGORY_ID, 0);
        presenter.initQuestion(categoryId);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_real_exam;
    }

    @Override
    public void showQuestion(Question question) {
        btnSubmit.setEnabled(true);
        StringUtils.setText(tvQuestion, question.question);

        rdbAnswerA.setTag(question.getAnswerA());
        rdbAnswerB.setTag(question.getAnswerB());
        rdbAnswerC.setTag(question.getAnswerC());
        rdbAnswerD.setTag(question.getAnswerD());

        StringUtils.setText(rdbAnswerA, question.getAnswerA().content);
        StringUtils.setText(rdbAnswerB, question.getAnswerB().content);
        StringUtils.setText(rdbAnswerC, question.getAnswerC().content);
        StringUtils.setText(rdbAnswerD, question.getAnswerD().content);
        StringUtils.setText(tvSecond, String.valueOf(Constant.TIME_PER_QUESTION - 1));
        presenter.startCountDown();
    }

    @Override
    public void showResult(boolean correct) {
        layoutContainer.setBackgroundColor(getResources().getColor(correct ? R.color.exam_color_correct : R.color.exam_color_incorrect));
        Observable.interval(2, TimeUnit.SECONDS)
                .take(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(count -> {
                    layoutContainer.setBackground(defaultColor);
                    radioGroup.clearCheck();
                    presenter.nextQuestion();
                });
    }

    @Override
    public void updateRemain(long count, String remainString) {
        StringUtils.setText(tvSecond, String.valueOf(count));
        StringUtils.setText(tvRemaining, remainString);
        if (count == 0) {
            Answer answer;
            if (rdbAnswerA.isChecked()) {
                answer = (Answer) rdbAnswerA.getTag();
            } else if (rdbAnswerB.isChecked()) {
                answer = (Answer) rdbAnswerB.getTag();
            } else if (rdbAnswerC.isChecked()) {
                answer = (Answer) rdbAnswerC.getTag();
            } else if (rdbAnswerD.isChecked()) {
                answer = (Answer) rdbAnswerD.getTag();
            } else {
                answer = null;
            }
            btnSubmit.setEnabled(false);
            presenter.submitAnswer(answer);
        }
    }

    @Override
    public void showStreakScreen(int point, int type) {
        finish();
        StreakActivity.navigate(this, type, point);
    }

    @Override
    public void updateProgressCount(int maxCount) {
        rsvSmall.setRangeCount(maxCount);
    }

    @Override
    public void updateProgressBar(int current) {
        rsvSmall.setCurrentIndex(current);
    }

    @OnClick({R.id.tv_close, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_close:
                DialogUtils.showDialog(this, "Exit class", "You're in the exam!! Are you want to exit?", "Exit", sweetAlertDialog -> {
                    sweetAlertDialog.dismiss();
                    finish();
                });
                break;
            case R.id.btn_submit:
                presenter.exitCountDown();
                Answer answer;
                if (rdbAnswerA.isChecked()) {
                    answer = (Answer) rdbAnswerA.getTag();
                } else if (rdbAnswerB.isChecked()) {
                    answer = (Answer) rdbAnswerB.getTag();
                } else if (rdbAnswerC.isChecked()) {
                    answer = (Answer) rdbAnswerC.getTag();
                } else if (rdbAnswerD.isChecked()) {
                    answer = (Answer) rdbAnswerD.getTag();
                } else {
                    answer = null;
                }
                btnSubmit.setEnabled(false);
                presenter.submitAnswer(answer);
                break;
        }
    }
}
