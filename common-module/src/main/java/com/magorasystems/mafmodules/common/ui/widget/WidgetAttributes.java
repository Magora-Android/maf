package com.magorasystems.mafmodules.common.ui.widget;

import android.support.annotation.LayoutRes;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class WidgetAttributes {

    @LayoutRes
    private int layoutId;

    private WidgetAttributes() {

    }

    public static WidgetAttributes create(@LayoutRes int layoutId) {
        final WidgetAttributes widgetAttributes = new WidgetAttributes();
        widgetAttributes.setLayoutId(layoutId);
        return widgetAttributes;
    }

    public int getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }
}
