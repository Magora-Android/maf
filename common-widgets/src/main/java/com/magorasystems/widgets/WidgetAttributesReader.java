package com.magorasystems.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class WidgetAttributesReader extends AbstractWidgetAttributesReader {

    public WidgetAttributesReader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    public WidgetAttributes read() {
        final TypedArray a = getContext().obtainStyledAttributes(getAttributeSet(), R.styleable.BaseWidget);
        @LayoutRes int layoutId = a.getResourceId(R.styleable.BaseWidget_root_layout, 0);
        if (layoutId == 0) {
            throw new IllegalArgumentException("BaseWidget_root_layout wrong");
        }
        final WidgetAttributes widgetAttributes = new WidgetAttributes(layoutId);
        a.recycle();
        return widgetAttributes;
    }
}
