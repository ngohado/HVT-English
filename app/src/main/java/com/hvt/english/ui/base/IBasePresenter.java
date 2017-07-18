package com.hvt.english.ui.base;

/**
 * Created by Hado on 7/18/17.
 */

public interface IBasePresenter<V extends BaseView> {
    void onAttach(V view);

    void onDetach();

    V getView();
}
