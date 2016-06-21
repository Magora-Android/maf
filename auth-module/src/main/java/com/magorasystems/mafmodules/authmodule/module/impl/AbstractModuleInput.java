package com.magorasystems.mafmodules.authmodule.module.impl;

import com.magorasystems.mafmodules.authmodule.module.base.ModuleInput;
import com.magorasystems.mafmodules.authmodule.view.input.ViewInput;
import com.magorasystems.mafmodules.common.mvp.router.BaseRouter;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class AbstractModuleInput<V extends ViewInput<?, ?>, R extends BaseRouter> implements ModuleInput<V, R> {

    private V viewInput;
    private R router;

    protected AbstractModuleInput(V viewInput, R router) {
        this.viewInput = viewInput;
        this.router = router;
    }

    @Override
    public V getViewInput() {
        return viewInput;
    }

    @Override
    public R getRouter() {
        return router;
    }
}
