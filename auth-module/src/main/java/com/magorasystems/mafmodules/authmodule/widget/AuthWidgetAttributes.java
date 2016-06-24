package com.magorasystems.mafmodules.authmodule.widget;

import android.support.annotation.LayoutRes;

import com.magorasystems.widgets.WidgetAttributes;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class AuthWidgetAttributes extends WidgetAttributes {

    private boolean isShowError;

    public AuthWidgetAttributes(@LayoutRes int layoutId) {
        super(layoutId);
    }

    public boolean isShowError() {
        return isShowError;
    }

    public void setShowError(boolean showError) {
        isShowError = showError;
    }
}
