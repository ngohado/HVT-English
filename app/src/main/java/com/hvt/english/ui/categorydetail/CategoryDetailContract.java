package com.hvt.english.ui.categorydetail;


import android.support.v4.app.Fragment;

import com.hvt.english.ui.base.BaseView;
import com.hvt.english.ui.base.IBasePresenter;

import java.util.List;


public interface CategoryDetailContract {
    interface View extends BaseView {
        void showSections(List<Fragment> sections);

        void showCategoryImage(String image);
    }

    interface Presenter extends IBasePresenter<View> {
        void loadDataSections(int categoryId);
    }
}
