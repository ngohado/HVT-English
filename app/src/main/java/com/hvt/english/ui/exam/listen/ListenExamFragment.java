package com.hvt.english.ui.exam.listen;

import android.os.Bundle;
import android.view.View;

import com.hvt.english.MyApplication;
import com.hvt.english.model.Meaning;
import com.hvt.english.ui.base.BaseFragment;
import com.hvt.english.ui.exam.main.ExamActivity;

import butterknife.Unbinder;

/**
 * Created by doannh on 7/18/17.
 */

public class ListenExamFragment extends BaseFragment implements ListenExamContract.View {

    ListenExamContract.Presenter presenter;

    public static ListenExamFragment newInstance(Meaning meaning) {
        ListenExamFragment fragment = new ListenExamFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ExamActivity.DATA_MEANING, meaning);
        fragment.setArguments(bundle);
        return fragment;
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

    }

    @Override
    public void initData() {
        presenter.loadQuestionPractice(getArguments());
    }

    @Override
    public int getLayoutID() {
        return 0;
    }

    @Override
    public Unbinder bindingView(View view) {
        return null;
    }

    @Override
    public void showResult(boolean correct) {

    }

    @Override
    public void showQuestionPractice(Meaning meaning) {

    }
}
