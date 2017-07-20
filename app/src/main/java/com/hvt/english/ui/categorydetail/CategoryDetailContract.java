package com.hvt.english.ui.categorydetail;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.hvt.english.ui.base.BaseView;
import com.hvt.english.ui.base.IBasePresenter;

import java.util.List;

/**
 * Created by doannh on 7/20/17.
 */

public interface CategoryDetailContract {
    interface View extends BaseView {
        void showSections(List<Fragment> sections);
    }

    interface Presenter extends IBasePresenter<View> {
        void loadDataSections(Bundle data);
    }
}
