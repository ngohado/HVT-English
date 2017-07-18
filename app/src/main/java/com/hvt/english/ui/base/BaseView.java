package com.hvt.english.ui.base;

public interface BaseView {
    void showLoading();

    void hideLoading();

    void showError(String message);

    void showError(int message);

    void showToast(String message);

    void showToast(int message);

    void hideKeyboard();

    void attachView();

    void detachView();
}
