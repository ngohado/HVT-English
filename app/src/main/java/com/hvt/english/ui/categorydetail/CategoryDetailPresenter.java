package com.hvt.english.ui.categorydetail;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.hvt.english.network.ApiClient;
import com.hvt.english.ui.base.BasePresenter;
import com.hvt.english.ui.categorydetail.sectioncard.SectionCardFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by doannh on 7/20/17.
 */

public class CategoryDetailPresenter extends BasePresenter<CategoryDetailContract.View> implements CategoryDetailContract.Presenter {

    private int idCategory;

    public CategoryDetailPresenter(ApiClient apiClient) {
        super(apiClient);
    }

    @Override
    public void loadDataSections(Bundle data) {
        idCategory = data.getInt(CategoryDetailFragment.CATEGORY_ID_DATA);
        dataManager.getDataSectionRemote(idCategory)
                .doOnSubscribe(disposable -> {
                    compositeDisposable.add(disposable);
                    getView().showLoading();
                })
                .doFinally(() -> getView().hideLoading())
                .subscribe(section -> {
                    List<Fragment> fragments = new ArrayList<>();
                    fragments.add(SectionCardFragment.newInstance(section, SectionCardFragment.CardType.WORD));
                    fragments.add(SectionCardFragment.newInstance(section, SectionCardFragment.CardType.SENTENCE));
                    fragments.add(SectionCardFragment.newInstance(section, SectionCardFragment.CardType.PRACTICE));
                    fragments.add(SectionCardFragment.newInstance(section, SectionCardFragment.CardType.TEST));
                    getView().showSections(fragments);
                });
    }
}
