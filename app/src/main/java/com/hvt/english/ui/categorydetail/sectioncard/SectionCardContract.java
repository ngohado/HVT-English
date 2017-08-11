package com.hvt.english.ui.categorydetail.sectioncard;


import android.os.Bundle;

import com.hvt.english.model.Sentence;
import com.hvt.english.model.Word;
import com.hvt.english.ui.base.BaseView;
import com.hvt.english.ui.base.IBasePresenter;

import java.util.ArrayList;

/**
 * Created by doannh on 7/20/17.
 */

public interface SectionCardContract {
    interface View extends BaseView {
        void showTitle(String title);

        void showButtonStart(String text);

        void showContent(String content);

        void openNewScreenCorresponding(Class clazz, Bundle bundle);

        void showEmptyData();
    }

    interface Presenter extends IBasePresenter<View> {
        void loadWord(ArrayList<Word> words);

        void loadSentence(ArrayList<Sentence> sentences);

        void loadPractice();

        void loadTest();

        void clickStart(Bundle argument);
    }

}
