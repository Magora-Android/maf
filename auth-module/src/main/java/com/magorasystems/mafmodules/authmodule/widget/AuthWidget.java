package com.magorasystems.mafmodules.authmodule.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;

import com.magorasystems.mafmodules.authmodule.R;
import com.magorasystems.mafmodules.authmodule.performance.AuthViewModel;
import com.magorasystems.mafmodules.common.utils.PatternsUtils;
import com.magorasystems.widgets.auth.GenericAuthWidget;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rx.Observable;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S. Bolkonsky
 */
public class AuthWidget extends GenericAuthWidget<AuthViewModel, AuthViewModel> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthWidget.class);

    protected TextView textEmail;
    protected TextInputLayout textInputEmail;
    protected TextView textPassword;
    protected TextInputLayout textInputPassword;

    public AuthWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AuthWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AuthWidget(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public Observable<AuthViewModel> model() {
        return Observable.just(new AuthViewModel.Builder()
                .login(textEmail.getText().toString())
                .password(textPassword.getText().toString())
                .build())
                .doOnNext(model -> {
                    textPassword.setText(null);
                    textInputPassword.setError(null);
                    textInputPassword.setErrorEnabled(false);
                });

    }

    @Override
    public void setEnabled(boolean enabled) {
        textEmail.setEnabled(enabled);
        textPassword.setEnabled(enabled);
    }

    @Override
    protected void onViewCreated(View view) {
        super.onViewCreated(view);
        textEmail = (TextView) view.findViewById(R.id.text_email);
        textInputEmail = (TextInputLayout) view.findViewById(R.id.text_input_email);
        textPassword = (TextView) view.findViewById(R.id.text_password);
        textInputPassword = (TextInputLayout) view.findViewById(R.id.text_input_password);
    }

    public Observable<Boolean> defaultValidation() {
        final Observable<Boolean> emailValidator = validatorEmail();
        final Observable<Boolean> passwordValidator = validatorPassword(getContext().getResources().getInteger(R.integer.auth_password_min_length));
        return Observable.combineLatest(emailValidator, passwordValidator,
                (mailIsValid, passwordIsValid) -> mailIsValid && passwordIsValid);
    }

    protected final Observable<Boolean> validatorEmail() {
        return validator(textEmail, Patterns.EMAIL_ADDRESS, 0,
                subscription, isValid -> setError(isValid, textInputEmail, getContext().getString(R.string.error_email_no_valid)));
    }

    protected final Observable<Boolean> validatorPassword(int minLength) {
        return validator(textPassword, PatternsUtils.patternPassword, minLength,
                subscription, isValid -> setError(isValid, textInputPassword, getContext().getString(R.string.error_password_no_valid, minLength)));
    }
}
