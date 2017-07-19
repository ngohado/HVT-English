package com.hvt.english.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.hvt.english.R;
import com.hvt.english.util.font.FontCache;


//
// BaseAndroid - FontTextView
//
// Created by Vin on 4/19/17.
// Copyright (c) 2017 Ominext. All rights reserved.
//

public class FontTextView extends AppCompatTextView {

    private String customFont;

    public FontTextView(Context context) {
        super(context);
        setCustomFont(context, null, 0);
    }

    public FontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, attrs, 0);
    }

    public FontTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setCustomFont(context, attrs, defStyleAttr);
    }

    /**
     * @param ctx
     * @param attrs
     */
    private void setCustomFont(Context ctx, AttributeSet attrs, int def) {

        if (!isInEditMode()) {
            TypedArray a = ctx.obtainStyledAttributes(attrs,
                    R.styleable.FontTextView, 0, 0);

            // get font name
            if (customFont == null)
                customFont = a.getString(R.styleable.FontTextView_font);

            // set default name of font
            if (customFont == null) {
                return;
            }

            setCustomFont(ctx, customFont);

            a.recycle();
        }
    }

    /**
     * set custom font for text view
     *
     * @param ctx
     * @param nameOfFont
     * @return
     */
    public boolean setCustomFont(Context ctx, String nameOfFont) {

        Typeface typeface = FontCache.getTypeface(ctx, nameOfFont);

        if (typeface == null) {
            return false;
        }

        setTypeface(typeface);

        return true;
    }

    public void setCustomFont(String customFont) {
        this.customFont = customFont;
    }
}
