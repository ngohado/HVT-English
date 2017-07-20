package com.hvt.english.util;

import android.content.Context;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Hado on 7/20/17.
 */

public class DialogUtils {
    public static void showDialog(Context context, String title, String content, String confirm, SweetAlertDialog.OnSweetClickListener listener) {
        SweetAlertDialog dialog = new SweetAlertDialog(context, SweetAlertDialog.NORMAL_TYPE);
        dialog.setTitleText(title);
        dialog.setContentText(content);
        dialog.setCancelText("Cancel");
        dialog.setConfirmText(confirm);
        dialog.setConfirmClickListener(listener);
        dialog.show();
    }
}
