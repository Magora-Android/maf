package com.magorasystems.mafmodules.common.mvp.presenter;

import android.support.annotation.Nullable;

import com.magorasystems.mafmodules.common.module.output.ViewOutput;
import com.magorasystems.mafmodules.common.mvp.router.BaseRouter;
import com.magorasystems.mafmodules.common.mvp.view.BaseView;

import java.lang.ref.WeakReference;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S. Bolkonsky
 */
public abstract class CommonPresenter<V extends BaseView, R extends BaseRouter, M, VO extends ViewOutput<? extends M>> implements BasePresenter<V, R, M, VO> {

    private WeakReference<V> viewRef;
    private WeakReference<R> routerRef;

    /**
     * Create instance of WeakReferences with your type of View extends {@link BaseView}
     *
     * @param view argument for WeakReference. This {@code view} must be extends {@link BaseView}
     * @see #detachView(boolean) (BaseView)
     */
    @Override
    public void attachView(V view) {
        viewRef = new WeakReference<>(view);
    }

    /**
     * Clear WeakReference for this presenter and set it (instance of WeakReference) to null
     *
     * @param retainInstance retain instance (is not used here)
     * @see #attachView(BaseView)
     */
    @Override
    public void detachView(boolean retainInstance) {
        if (viewRef != null) {
            viewRef.clear();
            viewRef = null;
        }
    }

    /**
     * Create instance of WeakReferences with your type of Router extends {@link BaseRouter}
     *
     * @param router argument for WeakReference. This {@code router} must be extends {@link BaseRouter}
     * @see #removeRouter()
     */
    @Override
    public void setRouter(R router) {
        routerRef = new WeakReference<>(router);
    }

    /**
     * Clear {@link WeakReference} with router and set it (instance of WeakReference) to null
     *
     * @see #setRouter(BaseRouter)
     */
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
     *
     * @see #attachView(BaseView)
     * @see #detachView(boolean)
     * @see #getView()
     */
    public boolean isViewAttached() {
        return viewRef != null && viewRef.get() != null;
    }

    /**
     * @return instance of Router extends {@link BaseRouter} from WeakReference <br>
     * or <b>null</b> if weakReference is null
     * @see #removeRouter()
     */
    @Nullable
    public R getRouter() {
        return routerRef != null ? routerRef.get() : null;
    }

    /**
     * @return instance of View extends {@link BaseView} from WeakReference <br>
     * or <b>null</b> if weakReference is null
     * @see #detachView(boolean)
     */
    @Nullable
    public V getView() {
        return viewRef == null ? null : viewRef.get();
    }

    /**
     * Getting view extends {@link BaseView} from WeakReference <br>
     * and if {@code view} is not null, than call {@code view.showError(e)}
     *
     * @param e your throwable
     * @see #getView()
     * @see #attachView(BaseView)
     * @see #detachView(boolean)
     */
    protected final void showError(Throwable e) {
        V view = getView();
        if (null != view) {
            view.showError(e);
        }
    }

}
