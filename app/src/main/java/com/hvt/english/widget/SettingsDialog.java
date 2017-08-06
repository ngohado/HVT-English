package com.hvt.english.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.WindowManager;
import android.widget.EditText;

import com.hvt.english.R;
import com.hvt.english.util.SharedPrefUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Hado on 8/6/17.
 */

public class SettingsDialog extends Dialog {

    @BindView(R.id.edt_goals)
    EditText edtGoals;

    public static SettingsDialog newInstance(Context context) {
        return new SettingsDialog(context);
    }

    public SettingsDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_dialog);
        ButterKnife.bind(this);
        edtGoals.setText(SharedPrefUtil.getInstance().getGoals() + "");
    }

    @OnClick(R.id.btn_submit)
    public void updateGoalsOnClick() {
        SharedPrefUtil.getInstance().setGoals(Integer.parseInt(edtGoals.getText().toString()));
        SweetAlertDialog dialog = new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE);
        dialog.setTitleText("Successfully");
        dialog.setContentText("Now your daily goals is " + edtGoals.getText().toString());
        dialog.setConfirmText("Ok");
        dialog.show();
        dismiss();
    }

    @Override
    public void show() {
        super.show();
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(layoutParams);
    }
}
