package com.magorasystems.mafmodules.common.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;

import com.magorasystems.mafmodules.common.R;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class WidgetAttributesReader {

    protected WidgetAttributesReader() {

    }

    public static WidgetAttributes read(Context context, AttributeSet attributeSet) {
        final TypedArray a = context.obtainStyledAttributes(attributeSet, R.styleable.BaseWidget);
        @LayoutRes int layoutId = a.getResourceId(R.styleable.BaseWidget_root_layout, 0);
        if (layoutId == 0) {
            throw new IllegalArgumentException("BaseWidget_root_layout wrong");
        }
        final WidgetAttributes widgetAttributes = WidgetAttributes.create(layoutId);
        a.recycle();
        return widgetAttributes;
    }
}
