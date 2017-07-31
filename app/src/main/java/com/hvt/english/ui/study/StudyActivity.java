package com.hvt.english.ui.study;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.github.channguyen.rsv.RangeSliderView;
import com.hvt.english.R;
import com.hvt.english.ui.base.BaseActivity;
import com.hvt.english.ui.categorydetail.adapter.SectionAdapter;
import com.hvt.english.ui.streak.StreakActivity;
import com.hvt.english.util.DialogUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Hado on 7/13/17.
 */

public class StudyActivity extends BaseActivity implements StudyContract.View, ViewPager.OnPageChangeListener {

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
        vpCards.addOnPageChangeListener(this);
        adapter = new SectionAdapter(getSupportFragmentManager());
        vpCards.setAdapter(adapter);
    }

    @Override
    public void initData() {
        presenter.loadData(getIntent().getExtras());
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
        adapter.setData(cards);
        adapter.notifyDataSetChanged();
        rsvSmall.setRangeCount(cards.size());
    }

    @Override
    public void showStreakScreen(int point, int type) {
        finish();
        StreakActivity.navigate(this, StreakActivity.STUDY, point);
    }

    @Override
    public void showDialogConfirmExit() {
        DialogUtils.showDialog(this, "Exit class", "You're studying!! Are you want to exit class?", "Exit", sweetAlertDialog -> {
            sweetAlertDialog.dismiss();
            finish();
        });
    }

    @Override
    public void changeCard(int atPosition) {
        rsvSmall.setCurrentIndex(atPosition);
        vpCards.setCurrentItem(atPosition, true);
    }


    @OnClick({R.id.btn_back, R.id.btn_next, R.id.tv_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                presenter.clickBack(vpCards.getCurrentItem());
                break;
            case R.id.btn_next:
                presenter.clickNext(vpCards.getCurrentItem());
                break;
            case R.id.tv_close:
                showDialogConfirmExit();
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //do nothing
    }

    @Override
    public void onPageSelected(int position) {
        changeCard(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //do nothing
    }
}
