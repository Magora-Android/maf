package com.magorasystems.mafmodules.view.impl;

import android.view.View;

import com.magorasystems.mafmodules.model.UserProfile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.ref.WeakReference;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class UserProfileLceViewImpl implements UserProfileLceView {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserProfileLceViewImpl.class);

    private final WeakReference<View> progressView;
    private final WeakReference<View> contentView;

    public UserProfileLceViewImpl(View progressView, View contentView) {
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
            content.setEnabled(false);
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
            content.setEnabled(true);
        }
    }

    @Override
    public void setModel(UserProfile model) {
        LOGGER.debug("model {} ", model);
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
