package com.hvt.english.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Admin on 05-Jul-17.
 */

public class SlideDisabledViewPager extends ViewPager {

    public SlideDisabledViewPager(Context context) {
        super(context);
    }

    public SlideDisabledViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return false;
    }
}