package com.hvt.english.ui.study;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.hvt.english.Constant;
import com.hvt.english.model.Sentence;
import com.hvt.english.model.Word;
import com.hvt.english.network.ApiClient;
import com.hvt.english.ui.base.BasePresenter;
import com.hvt.english.ui.categorydetail.sectioncard.SectionCardFragment;
import com.hvt.english.ui.study.card.CardFragment;

import java.util.ArrayList;
import java.util.List;

import static com.hvt.english.ui.categorydetail.sectioncard.SectionCardFragment.CARD_SECTION_DATA;
import static com.hvt.english.ui.categorydetail.sectioncard.SectionCardFragment.CARD_TYPE_DATA;

/**
 * Created by Hado on 7/14/17.
 */

public class StudyPresenter extends BasePresenter<StudyContract.View> implements StudyContract.Presenter {

    public StudyPresenter(ApiClient apiClient) {
        super(apiClient);
    }

    private List<Fragment> cards = new ArrayList<>();
    private int typeCard = 0;

    @Override
    public void loadData(Bundle data) {
        typeCard = data.getInt(CARD_TYPE_DATA);
        cards.clear();
        if (typeCard == SectionCardFragment.CardType.WORD.ordinal()) {
            ArrayList<Word> words = data.getParcelableArrayList(CARD_SECTION_DATA);
            for (Word w : words) {
                cards.add(CardFragment.newInstance(w));
            }
        } else if (typeCard == SectionCardFragment.CardType.SENTENCE.ordinal()) {
            ArrayList<Sentence> sentences = data.getParcelableArrayList(CARD_SECTION_DATA);
            for (Sentence s : sentences) {
                cards.add(CardFragment.newInstance(s));
            }
        }
        getView().showStudyContent(cards);
    }

    @Override
    public void clickNext(int currentPosition) {
        if (currentPosition == cards.size() - 1) {
            getView().showStreakScreen(cards.size() * Constant.SCORE_STUDY_DEF, typeCard);
        } else {
            getView().changeCard(currentPosition + 1);
        }
    }

    @Override
    public void clickBack(int currentPosition) {
        if (currentPosition == 0) {
            getView().showDialogConfirmExit();
        } else {
            getView().changeCard(currentPosition - 1);
        }
    }
}
