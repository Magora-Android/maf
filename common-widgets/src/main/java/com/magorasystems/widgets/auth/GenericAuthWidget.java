package com.magorasystems.widgets.auth;

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
import com.magorasystems.widgets.BaseFrameWidget;
import com.magorasystems.widgets.ValidationTextRule;
import com.magorasystems.widgets.ValidationWidget;
import com.magorasystems.widgets.WidgetAttributes;
import com.magorasystems.widgets.WidgetAttributesReader;
import com.magorasystems.widgets.WidgetTextUtils;
import com.magorasystems.widgets.model.BaseViewModel;

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
public abstract class GenericAuthWidget<INPUT, RESULT extends BaseViewModel> extends BaseFrameWidget<INPUT, RESULT> implements
        IdentityWidget<INPUT, RESULT>,
        ValidationWidget<ValidationTextRule> {

    /**
     * Static method for start validation for text field.
     *
     * @param textView     - validation field
     * @param pattern      - text regexp
     * @param minLength    - count of chars (0 - ignore)
     * @param subscription
     * @param func
     * @return
     */
    public static Observable<Boolean> validator(TextView textView, Pattern pattern, int minLength,
                                                CompositeSubscription subscription, Action1<Boolean> func) {
        final Observable<Boolean> validator = WidgetTextUtils.validationTextView(textView, pattern, minLength);
        subscription.add(validator
                .doOnNext(func)
                .subscribe());
        return validator;
    }

    protected final CompositeSubscription subscription = new CompositeSubscription();

    protected Collection<ValidationTextRule> textRules;

    public GenericAuthWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GenericAuthWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public GenericAuthWidget(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected WidgetAttributes readWidgetAttributes(Context context, AttributeSet attributeSet) {
        return new AuthWidgetAttributesReader(new WidgetAttributesReader(context, attributeSet)).read();
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

    protected void setError(boolean isValid, TextInputLayout textInputLayout, String message) {
        if (textInputLayout == null) {
            return;
        }
        final boolean isShowError = ((AuthWidgetAttributes) getWidgetAttributes()).isShowError();
        if (isShowError) {
            textInputLayout.setError(isValid ? null : message);
            textInputLayout.setErrorEnabled(!isValid);
        }
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
