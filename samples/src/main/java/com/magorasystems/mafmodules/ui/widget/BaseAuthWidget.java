package com.magorasystems.mafmodules.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.widget.TextView;

import com.magorasystems.mafmodules.authmodule.performance.AuthViewModel;
import com.magorasystems.mafmodules.common.ui.widget.BaseLinearWidget;
import com.magorasystems.mafmodules.common.ui.widget.ValidationWidget;
import com.magorasystems.mafmodules.common.ui.widget.WidgetTestUtils;

import java.util.regex.Pattern;

import rx.Observable;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S. Bolkonsky
 */
public abstract class BaseAuthWidget<M extends AuthViewModel, T> extends BaseLinearWidget<M, T> implements
        ValidationWidget {

    protected final CompositeSubscription subscription = new CompositeSubscription();

    public BaseAuthWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseAuthWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BaseAuthWidget(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void destroyWidget() {
        super.destroyWidget();
        unsubscribe();
    }


    private void unsubscribe() {
        if (subscription.hasSubscriptions()) {
            subscription.unsubscribe();
        }
    }

    public static Observable<Boolean> validator(TextView textView, Pattern pattern, int minLength,
                                                CompositeSubscription subscription, Action1<Boolean> func) {
        final Observable<Boolean> validator = WidgetTestUtils.validationTextView(textView, pattern, minLength);
        subscription.add(validator
                .doOnNext(func)
                .subscribe());
        return validator;
    }


    public static void setError(boolean isValid, TextInputLayout textInputLayout, String message) {
        textInputLayout.setError(isValid ? null : message);
        textInputLayout.setErrorEnabled(!isValid);
    }
}
