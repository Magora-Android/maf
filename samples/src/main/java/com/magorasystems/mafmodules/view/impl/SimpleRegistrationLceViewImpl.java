package com.magorasystems.mafmodules.view.impl;

import android.support.annotation.NonNull;
import android.view.View;

import com.magorasystems.mafmodules.common.mvp.view.impl.AbstractLceView;
import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SimpleRegistrationLceViewImpl extends AbstractLceView<StringAuthInfo>
        implements SimpleRegistrationLceView {

    public SimpleRegistrationLceViewImpl(View progressView, View contentView) {
        super(progressView, contentView);
    }

    @Override
    protected void showContent(@NonNull View view) {
        view.setEnabled(true);
        final View progressView = getProgressView();
        if (progressView != null) {
            progressView.setVisibility(View.GONE);
        }
    }

    @Override
    protected void showProgress(@NonNull View view) {
        view.setVisibility(View.VISIBLE);
        final View contentView = getContentView();
        if (contentView != null) {
            contentView.setEnabled(false);
        }
    }
}
