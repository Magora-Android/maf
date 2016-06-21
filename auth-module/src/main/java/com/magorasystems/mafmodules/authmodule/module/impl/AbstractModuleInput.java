package com.magorasystems.mafmodules.authmodule.module.impl;

import com.magorasystems.mafmodules.authmodule.module.base.ModuleInput;
import com.magorasystems.mafmodules.authmodule.view.input.ViewInput;
import com.magorasystems.mafmodules.common.mvp.router.BaseRouter;

import java.lang.ref.WeakReference;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class AbstractModuleInput<V extends ViewInput<?, ?>, R extends BaseRouter> implements ModuleInput<V, R> {

    private WeakReference<V> viewInput;
    private WeakReference<R> router;

    protected AbstractModuleInput(V viewInput, R router) {
        this.viewInput = new WeakReference<>(viewInput);
        this.router = new WeakReference<>(router);
    }

    @Override
    public V getViewInput() {
        return viewInput != null ? viewInput.get() : null;
    }

    @Override
    public R getRouter() {
        return router != null ? router.get() : null;
    }

    @Override
    public void clear() {
        if (viewInput != null) {
            viewInput.clear();
            viewInput = null;
        }
        if (router != null) {
            router.clear();
            router = null;
        }
    }
}
