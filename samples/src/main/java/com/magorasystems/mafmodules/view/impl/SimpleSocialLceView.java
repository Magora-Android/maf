package com.magorasystems.mafmodules.view.impl;

import android.view.View;

import com.magorasystems.mafmodules.authmodule.view.impl.StringAuthView;
import com.magorasystems.mafmodules.common.utils.ColorUtils;
import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.ref.WeakReference;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SimpleSocialLceView implements StringAuthView {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleSocialLceView.class);

    private final WeakReference<View> progressView;
    private final WeakReference<View> contentView;

    public SimpleSocialLceView(View progressView, View contentView) {
        this.progressView = new WeakReference<>(progressView);
        this.contentView = new WeakReference<>(contentView);
    }

    @Override
    public void showProgress() {
        final View progress = progressView.get();
        if (progress != null) {
            progress.setVisibility(View.VISIBLE);
        }
        final View content = contentView.get();
        if (content != null) {
            content.setLayerType(View.LAYER_TYPE_HARDWARE, ColorUtils.getSaturatedPaint(0f));
        }
    }

    @Override
    public void showContent() {
        final View progress = progressView.get();
        if (progress != null) {
            progress.setVisibility(View.INVISIBLE);
        }
        final View content = contentView.get();
        if (content != null) {
            content.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }
    }

    @Override
    public void setModel(StringAuthInfo model) {
        LOGGER.debug("setModel {} ", model);
    }

    @Override
    public void detachView() {
        progressView.clear();
        contentView.clear();
    }

    @Override
    public void showError(Throwable e) {
        LOGGER.error("something wrong ", e);
    }
}
