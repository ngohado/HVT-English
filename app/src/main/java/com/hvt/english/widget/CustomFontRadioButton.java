package com.hvt.english.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;

import com.hvt.english.util.font.Font;
import com.hvt.english.util.font.FontCache;

import static com.hvt.english.Constant.ANDROID_SCHEMA;

/**
 * Created by Hado on 7/26/17.
 */

public class CustomFontRadioButton extends AppCompatRadioButton {
    public CustomFontRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context, attrs);
    }

    public CustomFontRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context, attrs);
    }

    private Typeface selectTypeface(Context context, int style) {
        switch (style) {
            case Typeface.BOLD:
                return FontCache.getTypeface(context, Font.FIRASANS_BOLD);

            case Typeface.ITALIC:
                return FontCache.getTypeface(context, Font.FIRASANS_ITALIC);

            case Typeface.BOLD_ITALIC:
                return FontCache.getTypeface(context, Font.FIRASANS_BOLD_ITALIC);

            case Typeface.NORMAL:
            default:
                return FontCache.getTypeface(context, Font.FIRASANS);
        }
    }

    private void applyCustomFont(Context context, AttributeSet attrs) {
        int textStyle = attrs.getAttributeIntValue(ANDROID_SCHEMA, "textStyle", Typeface.NORMAL);
        Typeface customFont = selectTypeface(context, textStyle);
        setTypeface(customFont);
    }

}
