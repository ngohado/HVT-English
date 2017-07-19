package com.hvt.english.ui.streak;

import com.hvt.english.network.ApiClient;
import com.hvt.english.ui.base.BasePresenter;

/**
 * Created by Hado on 7/13/17.
 */

public class StreakPresenter extends BasePresenter<StreakContract.View> implements StreakContract.Presenter {
    public StreakPresenter(ApiClient apiClient) {
        super(apiClient);
    }
}
