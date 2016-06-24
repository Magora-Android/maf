package com.magorasystems.mafmodules.authmodule.widget;

import android.content.res.TypedArray;

import com.magorasystems.mafmodules.authmodule.R;
import com.magorasystems.widgets.AttributesReader;
import com.magorasystems.widgets.WidgetAttributes;
import com.magorasystems.widgets.WidgetAttributesReader;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class AuthWidgetAttributesReader implements AttributesReader {

    private WidgetAttributesReader widgetAttributesReader;

    public AuthWidgetAttributesReader(WidgetAttributesReader widgetAttributesReader) {
        this.widgetAttributesReader = widgetAttributesReader;
    }

    @Override
    public WidgetAttributes read() {
        final WidgetAttributes widgetAttributes = widgetAttributesReader.read();
        final AuthWidgetAttributes attributes = new AuthWidgetAttributes(widgetAttributes.getLayoutId());
        final TypedArray a = widgetAttributesReader.getContext().obtainStyledAttributes(widgetAttributesReader.getAttributeSet(),
                R.styleable.BaseAuthWidget);
        attributes.setShowError(a.getBoolean(R.styleable.BaseAuthWidget_is_show_errors, false));
        a.recycle();
        return attributes;
    }
}
