package com.hvt.english.ui.study.card;

import android.os.Bundle;

import com.hvt.english.model.Meaning;
import com.hvt.english.ui.base.BaseView;
import com.hvt.english.ui.base.IBasePresenter;

/**
 * Created by Hado on 7/20/17.
 */

public interface CardContract {
    interface View extends BaseView {
        void showStudyContent(Meaning meaning);

        void showSound(boolean show);

        void showImage(boolean show, String url);

        void configTextContentSize(int size);
    }

    interface Presenter extends IBasePresenter<View> {
        void loadData(Bundle data);
    }

}
