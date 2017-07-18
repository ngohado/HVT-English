package com.hvt.english.ui.category;

import com.hvt.english.model.Category;
import com.hvt.english.ui.base.BaseView;

import java.util.List;

/**
 * Created by Hado on 7/18/17.
 */

public interface CategoryContract {

    interface View extends BaseView {
        void showCategories(List<Category> categories);

        void openDetailCategoryUI(int categoryID);
    }

    interface Presenter {
        void loadCategories();

        void clickCategory(int position);
    }

}
