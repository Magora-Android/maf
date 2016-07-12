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

    protected abstract void showContent(@NonNull View view);

    protected abstract void showProgress(@NonNull View view);

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
        final View view = progressView.get();
        if (view != null) {
            showProgress(view);
        }
    }

    @Override
    public void showContent() {
        final View view = contentView.get();
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
}
