package com.magorasystems.mafmodules.common.module.base;

import com.magorasystems.mafmodules.common.module.input.ViewInput;
import com.magorasystems.mafmodules.common.mvp.router.BaseRouter;

/**
 * @param <V> type of View Input, that must be extends from {@link ViewInput}
 * @param <R> type of Router, that must be extends from {@link BaseRouter}
 * @author Valentin S.Bolkonsky
 *         Developed by Magora Team (magora-systems.com). 2016.
 */
public interface ModuleInput<V extends ViewInput<?, ?>, R extends BaseRouter> {

    V getViewInput();

    R getRouter();

    void clear();
}
