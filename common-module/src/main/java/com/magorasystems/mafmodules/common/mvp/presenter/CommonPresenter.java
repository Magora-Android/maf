package com.magorasystems.mafmodules.common.mvp.presenter;

import android.support.annotation.Nullable;

import com.magorasystems.mafmodules.common.mvp.router.BaseRouter;
import com.magorasystems.mafmodules.common.mvp.view.BaseView;

import java.lang.ref.WeakReference;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S. Bolkonsky
 */
public abstract class CommonPresenter<V extends BaseView, R extends BaseRouter> implements BasePresenter<V, R> {

    private WeakReference<V> viewRef;
    private WeakReference<R> routerRef;

    @Override
    public void attachView(V view) {
        viewRef = new WeakReference<>(view);
    }

    @Override
    public void detachView(boolean retainInstance) {
        if (viewRef != null) {
            viewRef.clear();
            viewRef = null;
        }
    }

    @Override
    public void setRouter(R router) {
        routerRef = new WeakReference<>(router);
    }

    @Override
    public void removeRouter() {
        if (routerRef != null) {
            routerRef.clear();
            routerRef = null;
        }
    }

    /**
     * Checks if a view is attached to this presenter. You should always call this method before
     * calling {@link #getView()} to get the view instance.
     */
    public boolean isViewAttached() {
        return viewRef != null && viewRef.get() != null;
    }

    @Nullable
    public R getRouter() {
        return routerRef != null ? routerRef.get() : null;
    }

    @Nullable
    public V getView() {
        return viewRef == null ? null : viewRef.get();
    }

    protected final void showError(Throwable e) {
        V view = getView();
        if (null != view) {
            view.showError(e);
        }
    }

}
