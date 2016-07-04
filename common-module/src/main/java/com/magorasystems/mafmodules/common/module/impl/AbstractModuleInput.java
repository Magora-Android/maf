package com.magorasystems.mafmodules.common.module.impl;

import com.magorasystems.mafmodules.common.module.base.ModuleInput;
import com.magorasystems.mafmodules.common.module.input.ViewInput;
import com.magorasystems.mafmodules.common.mvp.router.BaseRouter;

import java.lang.ref.WeakReference;

/**
 * @param <V> type of WeakReference for input view, that must be extends {@link ViewInput}
 * @param <R> type of WeakReference for router, that must be extends {@link BaseRouter}
 * @author Valentin S.Bolkonsky
 *         Developed by Magora Team (magora-systems.com). 2016.
 */
public class AbstractModuleInput<V extends ViewInput<?, ?>, R extends BaseRouter> implements ModuleInput<V, R> {

    private WeakReference<V> viewInput;
    private WeakReference<R> router;

    /**
     * New instances for WeakReferences
     * @param viewInput argument for WeakReference, that must be extends {@link ViewInput}
     * @param router argument for WeakReference, that must be extends {@link BaseRouter}
     */
    protected AbstractModuleInput(V viewInput, R router) {
        this.viewInput = new WeakReference<>(viewInput);
        this.router = new WeakReference<>(router);
    }

    /**
     * @return {@code view input} from WeakReferences if it is not null <br>
     *     or <b>null</b> if WeakReferences is null
     *     @see #clear()
     */
    @Override
    public V getViewInput() {
        return viewInput != null ? viewInput.get() : null;
    }

    /**
     * @return {@code router} from WeakReferences if it is not null <br>
     *     or <b>null</b> if WeakReferences is null
     *     @see #clear()
     */
    @Override
    public R getRouter() {
        return router != null ? router.get() : null;
    }

    /**
     * Clear WeakReferences for {@code inputView} and {@code router} (if already not null) and set it to null
     */
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
