package com.magorasystems.mafmodules.authmodule.module.base;

import com.magorasystems.mafmodules.authmodule.view.input.ViewInput;
import com.magorasystems.mafmodules.common.mvp.router.BaseRouter;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface ModuleInput<V extends ViewInput<?, ?>, R extends BaseRouter> {

    V getViewInput();

    R getRouter();
}
