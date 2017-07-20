package com.hvt.english.ui.study;

import android.os.Bundle;

import com.hvt.english.network.ApiClient;
import com.hvt.english.ui.base.BasePresenter;

/**
 * Created by Hado on 7/14/17.
 */

public class StudyPresenter extends BasePresenter<StudyContract.View> implements StudyContract.Presenter {

    public StudyPresenter(ApiClient apiClient) {
        super(apiClient);
    }

    @Override
    public void loadData(Bundle data) {

    }
}
