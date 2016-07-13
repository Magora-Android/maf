package com.magorasystems.mafmodules.authmodule.view.impl;

import android.support.annotation.NonNull;
import android.view.View;

import com.magorasystems.mafmodules.common.mvp.view.BaseModelView;
import com.magorasystems.mafmodules.common.mvp.view.impl.AbstractLceView;
import com.magorasystems.mafmodules.common.utils.ColorUtils;
import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class StringAuthViewImpl extends AbstractLceView<StringAuthInfo> implements StringAuthView {

    private static final Logger LOGGER = LoggerFactory.getLogger(StringAuthViewImpl.class);

    private BaseModelView<StringAuthInfo> modelView;

    public StringAuthViewImpl(View shadowView, View contentView, BaseModelView<StringAuthInfo> view) {
        super(shadowView, contentView);
        this.modelView = view;
    }

    @Override
    public void setModel(StringAuthInfo model) {
        LOGGER.debug("setModel: user is not null {}", model != null);
        if (modelView != null) {
            modelView.setModel(model);
        }
    }

    @Override
    protected void showContent(@NonNull View view) {
        view.setEnabled(true);
        final View progressView = getProgressView();
        if (progressView != null) {
            progressView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }
    }

    @Override
    protected void showProgress(@NonNull View view) {
        view.setLayerType(View.LAYER_TYPE_HARDWARE, ColorUtils.getSaturatedPaint(0));
        final View contentView = getContentView();
        if (contentView != null) {
            contentView.setEnabled(false);
        }
    }

    @Override
    public void showError(Throwable e) {
        LOGGER.error("something wrong ", e);
        if (modelView != null) {
            modelView.showError(e);
        }
    }
}
