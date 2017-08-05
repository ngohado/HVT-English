package com.hvt.english.ui.base;


import com.hvt.english.data.DataManager;
import com.hvt.english.data.IDataManager;
import com.hvt.english.network.ApiClient;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter<V extends BaseView> implements IBasePresenter<V> {

    protected IDataManager dataManager;

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    public BasePresenter(ApiClient apiClient) {
        dataManager = new DataManager(apiClient);
    }

    private V baseView;

    @Override
    public void onAttach(V view) {
        this.baseView = view;
    }

    @Override
    public void onDetach() {
        compositeDisposable.clear();
        baseView = null;
    }

    public V getView() {
        return baseView;
    }
}
