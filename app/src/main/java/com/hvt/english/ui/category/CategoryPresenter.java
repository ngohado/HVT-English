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

public class CategoryPresenter extends BasePresenter<CategoryView> {

    private List<Category> categories = new ArrayList<>();

    public CategoryPresenter(ApiClient apiClient) {
        super(apiClient);
    }

    public void loadCategories() {
        dataManager.getCategories()
                .doOnSubscribe(disposable -> getView().showLoading())
                .doFinally(() -> getView().hideLoading())
                .subscribe(data -> {
                    categories.clear();
                    categories.addAll(data);
                    getView().displayCategories(categories);
                }, throwable -> getView().showError(R.string.categories_error_load));
    }

    public void prepareNavigateToCategoryDetail(int position) {
        if (position < categories.size()) {
            getView().navigateToDetailCategory(categories.get(position).getId());
        }
    }

}
