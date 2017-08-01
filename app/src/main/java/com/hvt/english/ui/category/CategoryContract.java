package com.hvt.english.ui.category;

import com.hvt.english.model.Category;
import com.hvt.english.ui.base.BaseView;
import com.hvt.english.ui.base.IBasePresenter;

import java.util.List;

public interface CategoryContract {

    interface View extends BaseView {
        void showCategories(List<Category> categories);

        void openDetailCategoryUI(int categoryID);
    }

    interface Presenter extends IBasePresenter<View> {
        void loadCategories();

        void chooseCategory(int position);
    }

}
