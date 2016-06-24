package com.magorasystems.widgets;

import android.support.annotation.LayoutRes;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class WidgetAttributes {

    @LayoutRes
    private int layoutId;

    public WidgetAttributes(@LayoutRes int layoutId) {
        this.layoutId = layoutId;
    }

    public int getLayoutId() {
        return layoutId;
    }

}
