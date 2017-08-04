package com.hvt.english.ui.study.card;

import android.os.Bundle;

import com.hvt.english.model.Sentence;
import com.hvt.english.model.Word;
import com.hvt.english.network.ApiClient;
import com.hvt.english.ui.base.BasePresenter;
import com.hvt.english.ui.categorydetail.sectioncard.SectionCardFragment;

import static com.hvt.english.ui.categorydetail.sectioncard.SectionCardFragment.CARD_TYPE_DATA;

/**
 * Created by Hado on 7/14/17.
 */

public class CardPresenter extends BasePresenter<CardContract.View> implements CardContract.Presenter {

    public CardPresenter(ApiClient apiClient) {
        super(apiClient);
    }

    @Override
    public void loadData(Bundle data) {
        int cardType = data.getInt(CARD_TYPE_DATA);
        if (cardType == SectionCardFragment.CardType.WORD.ordinal()) {
            Word word = data.getParcelable(CardFragment.MEANING_DATA);
            getView().configTextContentSize(30);
            getView().showImage(true, word.image);
            getView().showStudyContent(word);
            getView().showSound(word.audio != null);
        } else if (cardType == SectionCardFragment.CardType.SENTENCE.ordinal()) {
            Sentence sentence = data.getParcelable(CardFragment.MEANING_DATA);
            getView().configTextContentSize(25);
            getView().showStudyContent(sentence);
            getView().showImage(false, null);
            getView().showSound(sentence.audio != null);
        }
    }
}
