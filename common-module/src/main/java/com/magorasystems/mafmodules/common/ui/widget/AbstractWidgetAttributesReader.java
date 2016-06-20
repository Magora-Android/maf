package com.magorasystems.mafmodules.common.ui.widget;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class AbstractWidgetAttributesReader implements AttributesReader {

    private final Context context;
    private final AttributeSet attributeSet;

    protected AbstractWidgetAttributesReader(Context context, AttributeSet attributeSet) {
        this.context = context;
        this.attributeSet = attributeSet;
    }

    public Context getContext() {
        return context;
    }

    public AttributeSet getAttributeSet() {
        return attributeSet;
    }
}
