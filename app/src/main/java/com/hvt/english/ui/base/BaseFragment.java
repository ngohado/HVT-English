package com.hvt.english.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment implements BaseView, SwipeRefreshLayout.OnRefreshListener {

    private Unbinder mUnBinder;

    protected BaseActivity parentActivity;

    protected View swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutID(), container, false);
        mUnBinder = bindingView(view);
        swipeRefreshLayout = view.findViewById(getSwipeRefreshLayout());
        if (swipeRefreshLayout != null) {
            ((SwipeRefreshLayout) swipeRefreshLayout).setOnRefreshListener(this);
        }
        return view;
    }

    public int getSwipeRefreshLayout() {
        return 0;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            parentActivity = (BaseActivity) context;
        }
    }

    public abstract void initView();

    public abstract void initData();

    public abstract int getLayoutID();

    public abstract Unbinder bindingView(View view);


    @Override
    public void onDestroy() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();
    }

    @Override
    public void showLoading() {
        if (swipeRefreshLayout != null) {
            ((SwipeRefreshLayout) swipeRefreshLayout).setRefreshing(true);
        }
    }

    @Override
    public void hideLoading() {
        if (swipeRefreshLayout != null) {
            ((SwipeRefreshLayout) swipeRefreshLayout).setRefreshing(false);
        }
    }

    @Override
    public void showError(String message) {
        parentActivity.showError(message);
    }

    @Override
    public void showError(int message) {
        parentActivity.showError(message);
    }

    @Override
    public void showToast(String message) {
        parentActivity.showToast(message);
    }

    @Override
    public void showToast(int message) {
        parentActivity.showToast(message);
    }

    @Override
    public void hideKeyboard() {
        parentActivity.hideKeyboard();
    }

    @Override
    public void onRefresh() {

    }
}
