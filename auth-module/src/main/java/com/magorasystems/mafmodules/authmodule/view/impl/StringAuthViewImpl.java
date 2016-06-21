package com.magorasystems.mafmodules.authmodule.view.impl;

import android.view.View;

import com.magorasystems.mafmodules.common.mvp.view.BaseModelView;
import com.magorasystems.mafmodules.common.utils.ColorUtils;
import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.ref.WeakReference;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class StringAuthViewImpl implements StringAuthView {

    private static final Logger LOGGER = LoggerFactory.getLogger(StringAuthViewImpl.class);

    private WeakReference<View> progressView;
    private WeakReference<View> contentView;
    private BaseModelView<StringAuthInfo> modelView;

    public StringAuthViewImpl(View shadowView, View contentView, BaseModelView<StringAuthInfo> view) {
        this.progressView = new WeakReference<>(shadowView);
        this.contentView = new WeakReference<>(contentView);
        this.modelView = view;
    }

    @Override
    public void showProgress() {
        View view = progressView.get();
        if (view != null) {
            view.setLayerType(View.LAYER_TYPE_HARDWARE, ColorUtils.getSaturatedPaint(0));
        }
    }

    @Override
    public void showContent() {
        View view = progressView.get();
        if (view != null) {
            view.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }
        view = contentView.get();
        if (view != null) {
            view.setEnabled(true);
        }
    }

    @Override
    public void setModel(StringAuthInfo model) {
        LOGGER.debug("setModel: user is not null {}", model != null);
        if(modelView != null) {
            modelView.setModel(model);
        }
    }

    @Override
    public void detachView() {
        if (progressView != null) {
            progressView.clear();
            progressView = null;
        }
        if (contentView != null) {
            contentView.clear();
            contentView = null;
        }
    }

    @Override
    public void showError(Throwable e) {
        LOGGER.error("something wrong ", e);
        if(modelView != null) {
            modelView.showError(e);
        }
    }
}
