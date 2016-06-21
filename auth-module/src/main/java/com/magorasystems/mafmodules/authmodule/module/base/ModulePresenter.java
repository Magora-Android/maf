package com.magorasystems.mafmodules.authmodule.module.base;

import com.magorasystems.mafmodules.authmodule.module.impl.AbstractModuleInput;
import com.magorasystems.mafmodules.authmodule.view.input.ViewInput;
import com.magorasystems.mafmodules.authmodule.view.outpit.AuthViewOutput;
import com.magorasystems.mafmodules.authmodule.view.outpit.ViewOutput;
import com.magorasystems.mafmodules.common.mvp.router.BaseRouter;
import com.magorasystems.mafmodules.common.utils.component.Injectable;

import rx.Observable;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface ModulePresenter<COMPONENT, R extends BaseRouter, VI extends ViewInput<?, ?>, VO extends ViewOutput<?>, I extends AbstractModuleInput<VI, R>> extends Injectable<COMPONENT> {

    void start();

    void stop();

    void input(I input);

    void output(Observable<AuthViewOutput> output);

    void destroy(boolean retainInstance);
}
