package com.magorasystems.mafmodules.common.mvp.view.impl;

import android.support.annotation.NonNull;
import android.view.View;

import com.magorasystems.mafmodules.common.mvp.view.BaseLceView;

import java.lang.ref.WeakReference;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class AbstractLceView<M> implements BaseLceView<M> {

    private WeakReference<View> progressView;
    private WeakReference<View> contentView;

    protected AbstractLceView(View progressView, View contentView) {
        this.progressView = new WeakReference<>(progressView);
        this.contentView = new WeakReference<>(contentView);
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
    public void showProgress() {
        final View view = getProgressView();
        if (view != null) {
            showProgress(view);
        }
    }

    @Override
    public void showContent() {
        final View view = getContentView();
        if (view != null) {
            showContent(view);
        }
    }

    @Override
    public void setModel(M model) {

    }

    @Override
    public void showError(Throwable e) {

    }

    protected final View getProgressView() {
        return progressView.get();
    }

    protected final View getContentView() {
        return contentView.get();
    }

    protected void showContent(@NonNull View view) {
        view.setEnabled(true);
        final View progressView = getProgressView();
        if (progressView != null) {
            progressView.setVisibility(View.GONE);
        }
    }

    protected void showProgress(@NonNull View view) {
        view.setVisibility(View.VISIBLE);
        final View contentView = getContentView();
        if (contentView != null) {
            contentView.setEnabled(false);
        }
    }


}
