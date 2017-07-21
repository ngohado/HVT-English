package com.hvt.english.ui.categorydetail.sectioncard;


import android.os.Bundle;

import com.hvt.english.model.Sentence;
import com.hvt.english.model.Word;
import com.hvt.english.network.ApiClient;
import com.hvt.english.ui.base.BasePresenter;
import com.hvt.english.ui.exam.main.ExamActivity;
import com.hvt.english.ui.study.StudyActivity;
import com.hvt.english.util.font.StringUtils;

import java.util.ArrayList;

import static com.hvt.english.ui.categorydetail.sectioncard.SectionCardFragment.CARD_TYPE_DATA;

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
    public void clickStart(Bundle argument) {
        int cardType = argument.getInt(CARD_TYPE_DATA);

        Class clazz = null;
        Bundle bundle = new Bundle();

        if (cardType == SectionCardFragment.CardType.WORD.ordinal()
                || cardType == SectionCardFragment.CardType.SENTENCE.ordinal()) {
            clazz = StudyActivity.class;
            bundle = argument;
        } else if (cardType == SectionCardFragment.CardType.PRACTICE.ordinal()) {
            clazz = ExamActivity.class;
            bundle = argument;
        } else { //TEST

        }
        getView().openNewScreenCorresponding(clazz, bundle);
    }
}