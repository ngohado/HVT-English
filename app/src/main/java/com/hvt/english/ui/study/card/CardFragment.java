package com.hvt.english.ui.study.card;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hvt.english.MyApplication;
import com.hvt.english.R;
import com.hvt.english.model.Meaning;
import com.hvt.english.model.Sentence;
import com.hvt.english.model.Word;
import com.hvt.english.ui.base.BaseFragment;
import com.hvt.english.ui.categorydetail.sectioncard.SectionCardFragment;
import com.hvt.english.widget.CustomFontTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Hado on 7/14/17.
 */

public class CardFragment extends BaseFragment implements CardContract.View {

    public static final String MEANING_DATA = "MEANING_DATA";

    @BindView(R.id.iv_sound)
    ImageView ivSound;
    @BindView(R.id.iv_word)
    ImageView ivWord;
    @BindView(R.id.iv_word_frame)
    android.support.v7.widget.CardView ivWordFrame;
    @BindView(R.id.tv_content)
    CustomFontTextView tvContent;
    @BindView(R.id.tv_meaning)
    CustomFontTextView tvMeaning;
    @BindView(R.id.cv_total)
    CardView cvTotal;

    CardContract.Presenter presenter;

    public static CardFragment newInstance(Word data) {
        CardFragment fragment = new CardFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(SectionCardFragment.CARD_TYPE_DATA, SectionCardFragment.CardType.WORD.ordinal());
        bundle.putParcelable(MEANING_DATA, data);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static CardFragment newInstance(Sentence data) {
        CardFragment fragment = new CardFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(SectionCardFragment.CARD_TYPE_DATA, SectionCardFragment.CardType.WORD.ordinal());
        bundle.putParcelable(MEANING_DATA, data);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        presenter.loadData(getArguments());
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_card;
    }

    @Override
    public Unbinder bindingView(View view) {
        return ButterKnife.bind(this, view);
    }

    @Override
    public void attachView() {
        if (presenter == null) {
            presenter = new CardPresenter(MyApplication.getApplication().getApiClient());
        }
        presenter.onAttach(this);
    }

    @Override
    public void detachView() {
        presenter.onDetach();
    }

    @OnClick(R.id.iv_sound)
    public void onViewClicked() {

    }

    @Override
    public void showStudyContent(Meaning meaning) {
        tvContent.setText(meaning.content);
        tvMeaning.setText(meaning.meaning);
    }

    @Override
    public void showSound(boolean show) {
        ivSound.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void showImage(boolean show, String url) {
        ivWord.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
        if (show) {
            Glide.with(this).load(url).into(ivWord);
        }
    }

    @Override
    public void configTextContentSize(int size) {
        tvContent.setTextSize(getResources().getDimension(size));
    }
}
