package com.magorasystems.mafmodules.ui.fragment;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;

import com.google.common.collect.Lists;
import com.magorasystems.mafmodules.R;
import com.magorasystems.mafmodules.authmodule.fragment.AuthorizationModuleFragment;
import com.magorasystems.mafmodules.authmodule.widget.AuthWidget;
import com.magorasystems.widgets.ValidationTextRule;
import com.magorasystems.widgets.WidgetUtils;
import com.magorasystems.mafmodules.common.utils.PatternsUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;

import butterknife.BindView;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class AuthorizationModuleFragmentImpl extends AuthorizationModuleFragment {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationModuleFragmentImpl.class);

    public static AuthorizationModuleFragment makeFragment() {
        final AuthorizationModuleFragment fragment = new AuthorizationModuleFragmentImpl();
        final Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.widget_authorization)
    protected AuthWidget authWidget;
    @BindView(R.id.button_sign_in)
    protected View buttonSignIn;
    @BindView(R.id.text_password_recover)
    protected TextView textPasswordRecover;
    @BindView(R.id.progress_layout)
    protected View progressView;

    @Override
    protected AuthWidget getAuthWidget() {
        return authWidget;
    }

    @Override
    protected View getSignInView() {
        return buttonSignIn;
    }

    @Override
    protected View getRecoverPassword() {
        return textPasswordRecover;
    }

    @Override
    protected Collection<ValidationTextRule> getRules() {
        final List<ValidationTextRule> rules = Lists.newArrayList();
        rules.add(new ValidationTextRule.Builder()
                .isShow(true)
                .resourceId(com.magorasystems.mafmodules.authmodule.R.id.text_email)
                .errorMessage(getString(R.string.error_email_no_valid))
                .pattern(Patterns.EMAIL_ADDRESS.pattern())
                .build());
        rules.add(new ValidationTextRule.Builder()
                .isShow(true)
                .resourceId(R.id.text_password)
                .errorMessage(getString(R.string.error_password_no_valid,
                        getResources().getInteger(R.integer.auth_password_min_length)))
                .minLength(getResources().getInteger(R.integer.auth_password_min_length))
                .pattern(PatternsUtils.patternPassword.pattern())
                .build());
        return rules;
    }

    @Override
    public void onAfterAuth() {
        LOGGER.debug("onAfterAuth");
        WidgetUtils.hideSoftKeyboard(getActivity());
    }

    @Override
    public void onRecoverPassword() {
        LOGGER.debug("onRecoverPassword");
    }

    @Override
    protected int getResourceLayout() {
        return R.layout.fragment_authorization;
    }

    @Override
    protected View getProgressView() {
        return progressView;
    }

}
