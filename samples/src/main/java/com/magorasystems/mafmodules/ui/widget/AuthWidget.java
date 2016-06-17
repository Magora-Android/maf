package com.magorasystems.mafmodules.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.util.Patterns;
import android.widget.TextView;

import com.magorasystems.mafmodules.R;
import com.magorasystems.mafmodules.authmodule.performance.AuthViewModel;
import com.magorasystems.mafmodules.common.ui.widget.ValidationTextRule;
import com.magorasystems.mafmodules.common.utils.PatternsUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

import butterknife.BindView;
import rx.Observable;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S. Bolkonsky
 */
public class AuthWidget extends BaseAuthWidget<AuthViewModel, AuthViewModel> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthWidget.class);

    @BindView(R.id.text_email)
    protected TextView textEmail;
    @BindView(R.id.text_input_email)
    protected TextInputLayout textInputEmail;
    @BindView(R.id.text_password)
    protected TextView textPassword;
    @BindView(R.id.text_input_password)
    protected TextInputLayout textInputPassword;

    protected Collection<ValidationTextRule> textRules;

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
