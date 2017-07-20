package com.hvt.english.ui.study;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.github.channguyen.rsv.RangeSliderView;
import com.hvt.english.R;
import com.hvt.english.ui.base.BaseActivity;
import com.hvt.english.ui.categorydetail.adapter.SectionAdapter;
import com.hvt.english.ui.study.card.CardFragment;
import com.hvt.english.util.DialogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Hado on 7/13/17.
 */

public class StudyActivity extends BaseActivity implements StudyContract.View {

    @BindView(R.id.rsv_small)
    RangeSliderView rsvSmall;
    @BindView(R.id.vp_cards)
    ViewPager vpCards;
    @BindView(R.id.btn_back)
    Button btnBack;
    @BindView(R.id.btn_next)
    Button btnNext;

    SectionAdapter adapter;

    StudyContract.Presenter presenter;

    @Override
    public void initView() {
        vpCards.setClipToPadding(false);
        vpCards.setPadding(100, 0, 100, 0);
        vpCards.setPageMargin(25);
        adapter = new SectionAdapter(getSupportFragmentManager());
        vpCards.setAdapter(adapter);
    }

    @Override
    public void initData() {
        showStudyContent(new ArrayList<>());
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_study;
    }

    @Override
    public void attachView() {
        if (presenter == null) {
            presenter = new StudyPresenter(null);
        }
        presenter.onAttach(this);
    }

    @Override
    public void detachView() {
        presenter.onDetach();
    }

    @Override
    public void showStudyContent(List<Fragment> cards) {
        cards.add(new CardFragment());
        cards.add(new CardFragment());
        cards.add(new CardFragment());
        cards.add(new CardFragment());
        cards.add(new CardFragment());
        cards.add(new CardFragment());
        cards.add(new CardFragment());
        cards.add(new CardFragment());
        cards.add(new CardFragment());
        cards.add(new CardFragment());
        adapter.setData(cards);
        adapter.notifyDataSetChanged();
        rsvSmall.setRangeCount(cards.size());
    }

    @OnClick({R.id.btn_back, R.id.btn_next, R.id.tv_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                rsvSmall.setCurrentIndex(rsvSmall.getCurrentIndex() - 1);
                vpCards.setCurrentItem(vpCards.getCurrentItem() - 1, true);
                break;
            case R.id.btn_next:
                rsvSmall.setCurrentIndex(rsvSmall.getCurrentIndex() + 1);
                vpCards.setCurrentItem(vpCards.getCurrentItem() + 1, true);
                break;
            case R.id.tv_close:
                DialogUtils.showDialog(this, "Exit class", "You're studying!! Are you want to exit class?", "Exit",new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                        finish();
                    }
                });
                break;
        }
    }
}
