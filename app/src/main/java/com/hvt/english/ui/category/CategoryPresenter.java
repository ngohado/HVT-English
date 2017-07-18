package com.hvt.english.ui.category;

import com.hvt.english.R;
import com.hvt.english.model.Category;
import com.hvt.english.network.ApiClient;
import com.hvt.english.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hado on 7/13/17.
 */

public class CategoryPresenter extends BasePresenter<CategoryContract.View> implements CategoryContract.Presenter {

    private List<Category> categories = new ArrayList<>();

    public CategoryPresenter(ApiClient apiClient) {
        super(apiClient);
    }

    @Override
    public void loadCategories() {
        dataManager.getCategories()
                .doOnSubscribe(disposable -> {
                    getView().showLoading();
                    compositeDisposable.add(disposable);
                })
                .doFinally(() -> getView().hideLoading())
                .subscribe(data -> {
                    categories.clear();
                    categories.addAll(data);
                    getView().showCategories(categories);
                }, throwable -> getView().showError(R.string.categories_error_load));
    }

    @Override
    public void clickCategory(int position) {
        getView().openDetailCategoryUI(categories.get(position).getId());
    }
}
