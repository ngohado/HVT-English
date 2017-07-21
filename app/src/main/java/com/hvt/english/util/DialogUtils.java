package com.hvt.english.util;

import android.content.Context;

import java.util.Random;

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

    public static void showDialogResult(Context context, boolean correct) {
        SweetAlertDialog dialog = new SweetAlertDialog(context, correct ? SweetAlertDialog.SUCCESS_TYPE : SweetAlertDialog.ERROR_TYPE);
        dialog.setTitleText(correct ? "Correct" : "Wrong");
        String content = "";
        Random random = new Random();
        int randomType = random.nextInt(4);
        if (correct) {
            switch (randomType) {
                case 0:
                    content = "You're awesome!!!";
                    break;
                case 1:
                    content = "Good job guy!!!";
                    break;
                case 2:
                    content = "Excellent!!!";
                    break;
                case 3:
                    content = "Have you mastered english???";
                    break;
            }
        } else {
            switch (randomType) {
                case 0:
                    content = "Shame on you ^^ Just kidding :)";
                    break;
                case 1:
                    content = "Let's study again...";
                    break;
                case 2:
                    content = "It's easy!!! What's wrong with you???";
                    break;
                case 3:
                    content = "What's wrong with your keyboard???";
                    break;
            }
        }
        dialog.setContentText(content);
        dialog.setConfirmText("OK");
        dialog.show();
    }
}
