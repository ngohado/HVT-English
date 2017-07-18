package com.hvt.english.ui.realexam;

import android.view.View;

import com.hvt.english.R;
import com.hvt.english.model.Question;
import com.hvt.english.ui.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Hado on 7/18/17.
 */

public class RealExamFragment extends BaseFragment implements RealExamContract.View {

    @Override
    public void attachView() {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_real_exam;
    }

    @Override
    public Unbinder bindingView(View view) {
        return ButterKnife.bind(this, view);
    }

    @Override
    public void showQuestion(Question question) {

    }

    @Override
    public void showResult(boolean correct) {

    }
}
