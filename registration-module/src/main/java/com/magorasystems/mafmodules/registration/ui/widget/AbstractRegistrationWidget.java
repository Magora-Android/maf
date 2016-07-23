package com.magorasystems.mafmodules.registration.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;

import com.magorasystems.widgets.auth.GenericAuthWidget;
import com.magorasystems.widgets.model.UserViewModel;

import java.io.Serializable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class AbstractRegistrationWidget<ID extends Serializable,
        INPUT extends UserViewModel<ID>,
        RESULT extends UserViewModel<ID>> extends GenericAuthWidget<INPUT, RESULT>
        implements ValidationRegistrationWidget<ID, INPUT, RESULT> {

    protected AbstractRegistrationWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected AbstractRegistrationWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected AbstractRegistrationWidget(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
