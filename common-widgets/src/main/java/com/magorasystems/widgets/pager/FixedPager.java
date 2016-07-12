package com.magorasystems.widgets.pager;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.WindowInsets;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Scroller;


import com.magorasystems.widgets.BuildConfig;

import java.lang.reflect.Field;

/**
 * Created by S.A.B. on 20.01.2016.
 */
public final class FixedPager extends ViewPager {

    public static final boolean isDebug = BuildConfig.DEBUG;

    private boolean isEnabled = true;

    public FixedPager(Context context) {
        super(context);
    }

    public FixedPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void scrollToFirst() {
        setCurrentItem(0);
    }

    public void scrollToLast() {
        if (getAdapter() != null) {
            setCurrentItem(getAdapter().getCount() - 1);
        }
    }

    /*
     *  Override next two methods to avoid Bug in ViewPager when MotionEvent cah throw IllegalArgumentException
     *  https://code.google.com/p/android/issues/detail?id=18990
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return isEnabled && super.onInterceptTouchEvent(ev);
        } catch (Exception e) {
            if (isDebug) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        try {
            return isEnabled && super.onTouchEvent(ev);
        } catch (Exception e) {
            if (isDebug) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Custom implementation to enable or not swipe :)
     *
     * @param isEnabled true to enable swipe, false otherwise.
     */
    public void setPagingEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public void setCustomAnimDuration(Context context, int duration) {
        try {
            Field mScroller;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(context, new AccelerateDecelerateInterpolator(), duration);
            mScroller.set(this, scroller);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static class FixedSpeedScroller extends Scroller {
        private final int scrollDuration;

        public FixedSpeedScroller(Context context, Interpolator interpolator, int duration) {
            super(context, interpolator);
            this.scrollDuration = duration;
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, scrollDuration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            super.startScroll(startX, startY, dx, dy, scrollDuration);
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT_WATCH)
    @Override
    public WindowInsets dispatchApplyWindowInsets(WindowInsets insets) {
        int childCount = getChildCount();
        for (int index = 0; index < childCount; index++)
            getChildAt(index).dispatchApplyWindowInsets(insets);

        return insets;
    }

    public boolean scrollToNext(boolean useSmoothScroll) {
        if (getAdapter() != null) {
            int currentItem = getCurrentItem();
            if (currentItem < getAdapter().getCount() - 1) {
                setCurrentItem(currentItem + 1, useSmoothScroll);
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return getAdapter() == null || getAdapter().getCount() == 0;
    }
}
