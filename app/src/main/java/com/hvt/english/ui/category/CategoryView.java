package com.hvt.english.ui.category;

import com.hvt.english.model.Category;
import com.hvt.english.ui.base.BaseView;

import java.util.List;

/**
 * Created by Hado on 7/13/17.
 */

public interface CategoryView extends BaseView {
    void displayCategories(List<Category> categories);

    void navigateToDetailCategory(int categoryID);
}
