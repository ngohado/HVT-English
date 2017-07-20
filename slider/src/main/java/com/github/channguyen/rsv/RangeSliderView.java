package com.github.channguyen.rsv;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

public class RangeSliderView extends View {

    private static final String TAG = RangeSliderView.class.getSimpleName();

    private static final int DEFAULT_PAINT_STROKE_WIDTH = 5;

    private static final int DEFAULT_FILLED_COLOR = Color.parseColor("#FFA500");

    private static final int DEFAULT_EMPTY_COLOR = Color.parseColor("#C3C3C3");

    private static final float DEFAULT_BAR_HEIGHT_PERCENT = 0.10f;

    private static final float DEFAULT_SLOT_RADIUS_PERCENT = 0.125f;

    private static final float DEFAULT_SLIDER_RADIUS_PERCENT = 0.25f;

    private static final int DEFAULT_RANGE_COUNT = 5;

    private static final int DEFAULT_HEIGHT_IN_DP = 50;

    protected Paint paintNormal;
    protected Paint paintReal;
    protected Paint paint2;
    protected int color2;
    protected Paint ripplePaint;

    protected float radius;

    protected float slotRadius;

    private int currentIndex;

    private float currentSlidingX;

    private float currentSlidingY;

    private float selectedSlotX;

    private float selectedSlotY;

    private boolean gotSlot = false;

    private float[] slotPositions;

    private int filledColor = DEFAULT_FILLED_COLOR;

    private int emptyColor = DEFAULT_EMPTY_COLOR;

    private float barHeightPercent = DEFAULT_BAR_HEIGHT_PERCENT;

    private int rangeCount = DEFAULT_RANGE_COUNT;

    private int barHeight;

    private OnSlideListener listener;

    private float rippleRadius = 0.0f;

    private float downX;

    private float downY;

    private Path innerPath = new Path();

    private Path outerPath = new Path();

    private float slotRadiusPercent = DEFAULT_SLOT_RADIUS_PERCENT;

    private float sliderRadiusPercent = DEFAULT_SLIDER_RADIUS_PERCENT;

    private int layoutHeight;

    Shader shader;

    public RangeSliderView(Context context) {
        this(context, null);
    }

    public RangeSliderView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public RangeSliderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RangeSliderView);
            TypedArray sa = context.obtainStyledAttributes(attrs, new int[]{android.R.attr.layout_height});
            try {
                layoutHeight = sa.getLayoutDimension(
                        0, ViewGroup.LayoutParams.WRAP_CONTENT);
                rangeCount = a.getInt(
                        R.styleable.RangeSliderView_rangeCount, DEFAULT_RANGE_COUNT);
                filledColor = a.getColor(
                        R.styleable.RangeSliderView_filledColor, DEFAULT_FILLED_COLOR);
                emptyColor = a.getColor(
                        R.styleable.RangeSliderView_emptyColor, DEFAULT_EMPTY_COLOR);
                barHeightPercent = a.getFloat(
                        R.styleable.RangeSliderView_barHeightPercent, DEFAULT_BAR_HEIGHT_PERCENT);
                barHeightPercent = a.getFloat(
                        R.styleable.RangeSliderView_barHeightPercent, DEFAULT_BAR_HEIGHT_PERCENT);
                slotRadiusPercent = a.getFloat(
                        R.styleable.RangeSliderView_slotRadiusPercent, DEFAULT_SLOT_RADIUS_PERCENT);
                sliderRadiusPercent = a.getFloat(
                        R.styleable.RangeSliderView_sliderRadiusPercent, DEFAULT_SLIDER_RADIUS_PERCENT);
            } finally {
                a.recycle();
                sa.recycle();
            }
        }

        setBarHeightPercent(barHeightPercent);
        setRangeCount(rangeCount);
        setSlotRadiusPercent(slotRadiusPercent);

        slotPositions = new float[rangeCount];
        paintNormal = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintNormal.setStrokeWidth(7);
        paintNormal.setStyle(Paint.Style.FILL_AND_STROKE);
        paintNormal.setStrokeCap(Paint.Cap.ROUND);

        paintReal = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintReal.setStrokeWidth(22);
        paintReal.setStyle(Paint.Style.FILL_AND_STROKE);
        paintReal.setStrokeCap(Paint.Cap.ROUND);

        paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint2.setStrokeWidth(DEFAULT_PAINT_STROKE_WIDTH);
        paint2.setStyle(Paint.Style.FILL_AND_STROKE);
        paint2.setColor(emptyColor);
        color2 = getResources().getColor(R.color.color3);

        ripplePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        ripplePaint.setStrokeWidth(2.0f);
        ripplePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                getViewTreeObserver().removeOnPreDrawListener(this);

                // Update radius after we got new height
                updateRadius(getHeight());

                // Compute drawing position again
                preComputeDrawingPosition();

                // Ready to draw now
                return true;
            }
        });
        currentIndex = 0;
    }

    private void updateRadius(int height) {
        barHeight = 3;
        radius = height * sliderRadiusPercent;
        slotRadius = 15;
    }


    public void setRangeCount(int rangeCount) {
        if (rangeCount < 2) {
            throw new IllegalArgumentException("rangeCount must be >= 2");
        }
        slotPositions = new float[rangeCount];
        this.rangeCount = rangeCount;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int index) {
        if (index < 0 || index >= rangeCount) {
            return;
        }
        int oldIndex = currentIndex;
        currentIndex = index;
        ValueAnimator animator = ValueAnimator.ofFloat(slotPositions[oldIndex], slotPositions[currentIndex]);
        animator.setDuration(500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentSlidingX = (float) animation.getAnimatedValue();
                if (slotPositions[rangeCount - 1] - currentSlidingX < 20) {
                    paint2.setColor(color2);
                } else {
                    paint2.setColor(emptyColor);
                }
                invalidate();
            }
        });
        animator.start();

    }

    public void setBarHeightPercent(float percent) {
        if (percent <= 0.0 || percent > 1.0) {
            throw new IllegalArgumentException("Bar height percent must be in (0, 1]");
        }
        this.barHeightPercent = percent;
    }

    public void setSlotRadiusPercent(float percent) {
        if (percent <= 0.0 || percent > 1.0) {
            throw new IllegalArgumentException("Slot radius percent must be in (0, 1]");
        }
        this.slotRadiusPercent = percent;
    }


    /**
     * Perform all the calculation before drawing, should only run once
     */
    private void preComputeDrawingPosition() {
        int w = getWidthWithPadding();
        int h = getHeightWithPadding();

        /** Space between each slot */
        int spacing = w / rangeCount;

        /** Center vertical */
        int y = getPaddingTop() + h / 2;
        currentSlidingY = y;
        selectedSlotY = y;
        /**
         * Try to center it, so start by half
         * <pre>
         *
         *  Example for 4 slots
         *
         *  ____o____|____o____|____o____|____o____
         *  --space--
         *
         * </pre>
         */
        int x = getPaddingLeft() + (spacing / 2);

        /** Store the position of each slot index */
        for (int i = 0; i < rangeCount; ++i) {
            slotPositions[i] = x;
            if (i == currentIndex) {
                currentSlidingX = x;
                selectedSlotX = x;
            }
            x += spacing;
        }
        shader = new LinearGradient(slotPositions[0], 0, slotPositions[rangeCount - 1], 0, new int[]{getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2), getResources().getColor(R.color.color3)}, null, Shader.TileMode.CLAMP);
        paintReal.setShader(shader);
    }

    public void setInitialIndex(int index) {
        currentIndex = index;
        currentSlidingX = selectedSlotX = slotPositions[currentIndex];
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    /**
     * Measures height according to the passed measure spec
     *
     * @param measureSpec int measure spec to use
     * @return int pixel size
     */
    private int measureHeight(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        int result;
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            final int height;
            if (layoutHeight == ViewGroup.LayoutParams.WRAP_CONTENT) {
                height = dpToPx(getContext(), DEFAULT_HEIGHT_IN_DP);
            } else if (layoutHeight == ViewGroup.LayoutParams.MATCH_PARENT) {
                height = getMeasuredHeight();
            } else {
                height = layoutHeight;
            }
            result = height + getPaddingTop() + getPaddingBottom() + (2 * DEFAULT_PAINT_STROKE_WIDTH);
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    /**
     * Measures width according to the passed measure spec
     *
     * @param measureSpec int measure spec to use
     * @return int pixel size
     */
    private int measureWidth(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        int result;
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = specSize + getPaddingLeft() + getPaddingRight() + (2 * DEFAULT_PAINT_STROKE_WIDTH) + (int) (2 * radius);
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    private void drawEmptySlots(Canvas canvas) {
        int h = getHeightWithPadding();
        int y = getPaddingTop() + (h >> 1);
        canvas.drawCircle(slotPositions[rangeCount - 1], y, slotRadius, paint2);
    }

    public int getHeightWithPadding() {
        return getHeight() - getPaddingBottom() - getPaddingTop();
    }

    public int getWidthWithPadding() {
        return getWidth() - getPaddingLeft() - getPaddingRight();
    }


    private void drawBar(Canvas canvas, int from, int to, int color, Paint p) {
        p.setColor(color);
        int h = getHeightWithPadding();
        int y = getPaddingTop() + (h >> 1);
        canvas.drawLine(from, y, Math.max(to, slotPositions[0] + 1), y, p);
    }


    @Override
    public void onDraw(Canvas canvas) {
        int w = getWidthWithPadding();
        int h = getHeightWithPadding();
        int spacing = w / rangeCount;
        int border = (spacing >> 1);
        int x0 = getPaddingLeft() + border;
        int y0 = getPaddingTop() + (h >> 1);
        drawEmptySlots(canvas);

        /** Draw empty bar */
        drawBar(canvas, (int) slotPositions[0], (int) slotPositions[rangeCount - 1], emptyColor, paintNormal);

        /** Draw filled bar */
        drawBar(canvas, x0, (int) currentSlidingX, filledColor, paintReal);
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        ss.saveIndex = this.currentIndex;
        return ss;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        this.currentIndex = ss.saveIndex;
    }

    static class SavedState extends BaseSavedState {
        int saveIndex;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            this.saveIndex = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(this.saveIndex);
        }

        //required field that makes Parcelables from a Parcel
        public static final Parcelable.Creator<SavedState> CREATOR =
                new Parcelable.Creator<SavedState>() {
                    public SavedState createFromParcel(Parcel in) {
                        return new SavedState(in);
                    }

                    public SavedState[] newArray(int size) {
                        return new SavedState[size];
                    }
                };
    }

    /**
     * Helper method to convert pixel to dp
     *
     * @param context
     * @param px
     * @return
     */
    static int pxToDp(final Context context, final float px) {
        return (int) (px / context.getResources().getDisplayMetrics().density);
    }

    /**
     * Helper method to convert dp to pixel
     *
     * @param context
     * @param dp
     * @return
     */
    static int dpToPx(final Context context, final float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }

    /**
     * Interface to keep track sliding position
     */
    public interface OnSlideListener {

        /**
         * Notify when slider change to new index position
         *
         * @param index The index value of range count [0, rangeCount - 1]
         */
        void onSlide(int index);
    }
}
