package com.magorasystems.mafmodules.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.magorasystems.mafmodules.R;
import com.magorasystems.mafmodules.authmodule.fragment.AuthorizationFragment;
import com.magorasystems.mafmodules.authmodule.widget.AuthWidget;
import com.magorasystems.mafmodules.common.utils.ColorUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class AuthorizationFragmentImpl extends AuthorizationFragment {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationFragmentImpl.class);

    public static AuthorizationFragmentImpl makeFragment() {
        final AuthorizationFragmentImpl fragment = new AuthorizationFragmentImpl();
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
    public void showError(Throwable e) {
        LOGGER.error("some error ", e);
        showErrorDialog(e.getMessage(), (v, i) -> {

        });
    }

    @Override
    @OnClick(R.id.button_sign_in)
    public void onSignIn() {
        super.onSignIn();
    }

    @OnClick(R.id.text_password_recover)
    public void onPasswordRecover() {
        LOGGER.debug("onPasswordRecover");
    }


    @Override
    public void showProgress() {
        updateDecorView(ColorUtils.getSaturatedPaint(0.0f));
        super.showProgress();
    }

    @Override
    public void showContent() {
        updateDecorView(null);
        super.showContent();
    }

    @Override
    protected View getRecoverPassword() {
        return textPasswordRecover;
    }

    @Override
    protected AuthWidget getAuthWidget() {
        return authWidget;
    }

    @Override
    protected View getSignInView() {
        return buttonSignIn;
    }

    @Override
    protected View getProgressView() {
        return progressView;
    }
}
