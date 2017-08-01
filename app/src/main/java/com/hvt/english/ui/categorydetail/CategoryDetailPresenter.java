package com.hvt.english.ui.categorydetail;

import android.support.v4.app.Fragment;

import com.hvt.english.network.ApiClient;
import com.hvt.english.ui.base.BasePresenter;
import com.hvt.english.ui.categorydetail.sectioncard.SectionCardFragment;

import java.util.ArrayList;
import java.util.List;


public class CategoryDetailPresenter extends BasePresenter<CategoryDetailContract.View> implements CategoryDetailContract.Presenter {

    public CategoryDetailPresenter(ApiClient apiClient) {
        super(apiClient);
    }

    @Override
    public void loadDataSections(int categoryId) {
        dataManager.getDataSectionRemote(categoryId)
                .doOnSubscribe(disposable -> {
                    compositeDisposable.add(disposable);
                    getView().showLoading();
                })
                .doFinally(() -> getView().hideLoading())
                .subscribe(section -> {
                    //TODO: Show category image

                    List<Fragment> fragments = new ArrayList<>();
                    fragments.add(SectionCardFragment.newInstance(section, SectionCardFragment.CardType.WORD));
                    fragments.add(SectionCardFragment.newInstance(section, SectionCardFragment.CardType.SENTENCE));
                    fragments.add(SectionCardFragment.newInstance(section, SectionCardFragment.CardType.PRACTICE));
                    fragments.add(SectionCardFragment.newInstance(section, SectionCardFragment.CardType.TEST));
                    getView().showSections(fragments);
                });
    }
}
