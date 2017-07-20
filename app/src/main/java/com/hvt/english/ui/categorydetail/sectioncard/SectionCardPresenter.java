package com.hvt.english.ui.categorydetail.sectioncard;


import android.support.v4.app.Fragment;

import com.hvt.english.model.Sentence;
import com.hvt.english.model.Word;
import com.hvt.english.network.ApiClient;
import com.hvt.english.ui.base.BasePresenter;
import com.hvt.english.util.font.StringUtils;

import java.util.ArrayList;

/**
 * Created by doannh on 7/20/17.
 */

public class SectionCardPresenter extends BasePresenter<SectionCardContract.View> implements SectionCardContract.Presenter {

    public SectionCardPresenter(ApiClient apiClient) {
        super(apiClient);
    }

    @Override
    public void loadWord(ArrayList<Word> words) {
        getView().showTitle("New words");
        getView().showContent(StringUtils.wordsToString(words));
        getView().showButtonStart("Learn new words");
    }

    @Override
    public void loadSentence(ArrayList<Sentence> sentences) {
        getView().showTitle("New sentences");
        getView().showContent(StringUtils.sentencesToString(sentences));
        getView().showButtonStart("Learn new sentences");
    }

    @Override
    public void loadPractice() {
        getView().showTitle("Practice section");
        getView().showContent("Let's practice what you have learned");
        getView().showButtonStart("Join");
    }

    @Override
    public void loadTest() {
        getView().showTitle("Test section");
        getView().showContent("Now take the exam!!!");
        getView().showButtonStart("Join");
    }

    @Override
    public void clickStart(int type) {
        Fragment fragment = null;

        getView().openNewScreenCorresponding(fragment);
    }
}
