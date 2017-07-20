package com.hvt.english.ui.categorydetail.sectioncard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;

import com.hvt.english.R;
import com.hvt.english.model.Section;
import com.hvt.english.model.Sentence;
import com.hvt.english.model.Word;
import com.hvt.english.ui.base.BaseFragment;
import com.hvt.english.widget.CustomFontTextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by doannh on 7/20/17.
 */

public class SectionCardFragment extends BaseFragment implements SectionCardContract.View {

    public enum CardType {
        WORD,
        SENTENCE,
        PRACTICE,
        TEST
    }

    public static final String CARD_TYPE_DATA = "CARD_TYPE_DATA";
    public static final String CARD_SECTION_DATA = "CARD_SECTION_DATA";

    @BindView(R.id.tv_state)
    CustomFontTextView tvState;

    @BindView(R.id.tv_content)
    CustomFontTextView tvContent;

    @BindView(R.id.btn_start)
    Button btnStart;

    SectionCardContract.Presenter presenter;

    public static SectionCardFragment newInstance(Section section, CardType type) {
        SectionCardFragment fragment = new SectionCardFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(CARD_TYPE_DATA, type.ordinal());

        if (type.ordinal() == CardType.WORD.ordinal()) {
            ArrayList<Word> words = new ArrayList<>();
            words.addAll(section.words);
            bundle.putParcelableArrayList(CARD_SECTION_DATA, words);
        } else if (type.ordinal() == CardType.SENTENCE.ordinal()) {
            ArrayList<Sentence> sentences = new ArrayList<>();
            sentences.addAll(section.sentences);
            bundle.putParcelableArrayList(CARD_SECTION_DATA, sentences);
        }

        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void attachView() {
        if (presenter == null) {
            presenter = new SectionCardPresenter(null);
        }
        presenter.onAttach(this);
    }

    @Override
    public void detachView() {
        presenter.onDetach();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        Bundle data = getArguments();
        int cardType = data.getInt(CARD_TYPE_DATA);
        if (cardType == CardType.WORD.ordinal()) {
            presenter.loadWord(data.getParcelableArrayList(CARD_SECTION_DATA));
        } else if (cardType == CardType.SENTENCE.ordinal()) {
            presenter.loadSentence(data.getParcelableArrayList(CARD_SECTION_DATA));
        } else if (cardType == CardType.PRACTICE.ordinal()) {
            presenter.loadPractice();
        } else { //TEST
            presenter.loadTest();
        }
    }

    @Override
    public void showTitle(String title) {
        tvState.setText(title);
    }

    @Override

    public void showButtonStart(String text) {
        btnStart.setText(text);
    }

    @Override
    public void showContent(String content) {
        tvContent.setText(content);
    }

    @Override
    public void openNewScreenCorresponding(Fragment fragment) {

    }

    @OnClick(R.id.btn_start)
    public void buttonStartClicked() {
        presenter.clickStart(getArguments().getInt(CARD_TYPE_DATA));
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_section_card;
    }

    @Override
    public Unbinder bindingView(View view) {
        return ButterKnife.bind(this, view);
    }
}
