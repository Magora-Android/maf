package com.magorasystems.mafmodules.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import android.widget.TextView;

import com.google.common.collect.Lists;
import com.magorasystems.mafmodules.authmodule.performance.AuthViewModel;
import com.magorasystems.mafmodules.common.ui.widget.BaseLinearWidget;
import com.magorasystems.mafmodules.common.ui.widget.ValidationTextRule;
import com.magorasystems.mafmodules.common.ui.widget.ValidationWidget;
import com.magorasystems.mafmodules.common.ui.widget.WidgetTestUtils;

import java.util.Collection;
import java.util.List;
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
        ValidationWidget<ValidationTextRule> {

    protected final CompositeSubscription subscription = new CompositeSubscription();

    protected Collection<ValidationTextRule> textRules;

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

    @Override
    public Observable<Boolean> validation() {
        if (textRules == null || textRules.isEmpty()) {
            return Observable.just(true);
        }
        final List<Observable<Boolean>> validators = Lists.newArrayList();
        for (ValidationTextRule rule : textRules) {
            @IdRes int res = rule.getResourceId();
            final View view = findViewById(res);
            if (view != null && view instanceof TextView) {
                final ViewParent parent = view.getParent();
                final TextInputLayout textInputLayout = parent != null ? (TextInputLayout) parent : null;
                validators.add(validator((TextView) view, rule.getPattern() != null ?
                                Pattern.compile(rule.getPattern()) : null, rule.getMinLength(),
                        subscription, isValid -> setError((rule.isShow() ? isValid : true), textInputLayout, rule.getErrorMessage())));
            }
        }
        return Observable.combineLatest(validators, args -> {
            for (Object obj : args) {
                if (obj instanceof Boolean) {
                    if (!(Boolean) obj) {
                        return false;
                    }
                }
            }
            return true;
        });
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
        if (textInputLayout == null) {
            return;
        }
        textInputLayout.setError(isValid ? null : message);
        textInputLayout.setErrorEnabled(!isValid);
    }

    @Override
    public void updateRules(Collection<ValidationTextRule> rules) {
        if (rules == null) {
            this.textRules = Lists.newArrayList();
            return;
        }
        this.textRules = Lists.newArrayList(rules);
    }
}
