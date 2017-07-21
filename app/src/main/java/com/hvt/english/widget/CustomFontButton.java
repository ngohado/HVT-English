package com.hvt.english.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import com.hvt.english.Constant;
import com.hvt.english.util.font.Font;
import com.hvt.english.util.font.FontCache;

/**
 * Created by doannh on 7/21/17.
 */

public class CustomFontButton extends AppCompatButton {

    public CustomFontButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context, attrs);
    }

    public CustomFontButton(Context context, AttributeSet attrs, int defStyleAttr) {
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
        int textStyle = attrs.getAttributeIntValue(Constant.ANDROID_SCHEMA, "textStyle", Typeface.NORMAL);
        Typeface customFont = selectTypeface(context, textStyle);
        setTypeface(customFont);
    }

}
