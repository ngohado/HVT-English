package com.hvt.english.ui.categorydetail.sectioncard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;

import com.hvt.english.R;
import com.hvt.english.model.Meaning;
import com.hvt.english.model.Section;
import com.hvt.english.model.Sentence;
import com.hvt.english.model.Word;
import com.hvt.english.ui.base.BaseFragment;
import com.hvt.english.ui.realexam.RealExamActivity;
import com.hvt.english.widget.CustomFontTextView;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SectionCardFragment extends BaseFragment implements SectionCardContract.View {

    public enum CardType {
        WORD,
        SENTENCE,
        PRACTICE,
        TEST
    }

    public static final String CARD_TYPE_DATA = "CARD_TYPE_DATA";
    public static final String CARD_SECTION_DATA = "CARD_SECTION_DATA";
    public static final String CARD_CATEGORY_DATA = "CARD_CATEGORY_DATA";
    public static final String CARD_COLOR_DATA = "CARD_COLOR_DATA";

    @BindView(R.id.tv_state)
    CustomFontTextView tvState;

    @BindView(R.id.tv_content)
    CustomFontTextView tvContent;

    @BindView(R.id.btn_start)
    Button btnStart;

    SectionCardContract.Presenter presenter;

    public static SectionCardFragment newInstance(Section section, CardType type, int color) {
        SectionCardFragment fragment = new SectionCardFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(CARD_COLOR_DATA, color);
        bundle.putInt(CARD_TYPE_DATA, type.ordinal());
        bundle.putInt(CARD_CATEGORY_DATA, section.id);

        if (type.ordinal() == CardType.WORD.ordinal()) {
            ArrayList<Word> words = new ArrayList<>();
            words.addAll(section.words);
            bundle.putParcelableArrayList(CARD_SECTION_DATA, words);
        } else if (type.ordinal() == CardType.SENTENCE.ordinal()) {
            ArrayList<Sentence> sentences = new ArrayList<>();
            sentences.addAll(section.sentences);
            bundle.putParcelableArrayList(CARD_SECTION_DATA, sentences);
        } else if (type.ordinal() == CardType.PRACTICE.ordinal()) {
            Collections.shuffle(section.words);
            Collections.shuffle(section.sentences);

            ArrayList<Word> halfWords = new ArrayList<>();
            halfWords.addAll(section.words.subList(0, section.words.size() / 2));

            ArrayList<Sentence> halfSentences = new ArrayList<>();
            halfSentences.addAll(section.sentences.subList(0, section.sentences.size() / 2));
            ArrayList<Meaning> data = new ArrayList<>();
            data.addAll(halfWords);
            data.addAll(halfSentences);
            bundle.putParcelableArrayList(CARD_SECTION_DATA, data);
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
        int color = getArguments().getInt(CARD_COLOR_DATA);
        btnStart.setBackgroundColor(color);
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
    public void openNewScreenCorresponding(Class clazz, Bundle bundle) {
        if (clazz == null) {
            RealExamActivity.navigate(getContext(), bundle.getInt(CARD_CATEGORY_DATA));
            return;
        }
        Intent intent = new Intent(getContext(), clazz);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void showEmptyData() {
        new AlertDialog.Builder(getContext())
                .setTitle("Empty data")
                .setMessage("This section is empty data, please select other section!")
                .setPositiveButton("Ok", null)
                .show();
    }


    @OnClick(R.id.btn_start)
    public void buttonStartClicked() {
        presenter.clickStart(getArguments());
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
