package com.magorasystems.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.LightingColorFilter;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.magorasystems.widgets.utils.ColorUtils;


/**
 * @author bobrischev
 *         Developed by Magora Team (magora-systems.com). 2016.
 */
public class ProgressBarAccentTinted extends ProgressBar {

    public ProgressBarAccentTinted(Context context) {
        super(context);
        init();
    }

    public ProgressBarAccentTinted(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProgressBarAccentTinted(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ProgressBarAccentTinted(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        getIndeterminateDrawable().setColorFilter(new LightingColorFilter(0xFF000000,
                ColorUtils.getThemeColor(getContext(), R.attr.colorAccent)));
    }

    public void applyTint(@ColorInt int newTint) {
        getIndeterminateDrawable().setColorFilter(new LightingColorFilter(0xFF000000, newTint));
    }
}