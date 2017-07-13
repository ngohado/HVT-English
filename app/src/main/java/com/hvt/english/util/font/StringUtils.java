package com.hvt.english.util.font;

import android.widget.TextView;

/**
 * Created by Hado on 7/5/17.
 */

public class StringUtils {
    public static void setText(TextView textView, String string) {
        if (string == null) {
            textView.setText("");
        } else {
            textView.setText(string);
        }
    }
}
