package com.magorasystems.mafmodules.authmodule.module.base;

import android.content.Context;

import com.magorasystems.mafmodules.authmodule.module.impl.AbstractModuleInput;
import com.magorasystems.mafmodules.authmodule.view.input.ViewInput;
import com.magorasystems.mafmodules.authmodule.view.outpit.ViewOutput;
import com.magorasystems.mafmodules.common.mvp.presenter.BaseLifecyclePresenter;
import com.magorasystems.mafmodules.common.mvp.router.BaseRouter;
import com.magorasystems.mafmodules.common.mvp.view.BaseView;
import com.magorasystems.mafmodules.common.utils.component.HasComponent;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class AbstractModulePresenter<COMPONENT, R extends BaseRouter, VI extends ViewInput<?, ?>,
        VO extends ViewOutput<?>, I extends AbstractModuleInput<VI, R>> implements ModulePresenter<COMPONENT, R, VI, VO, I> {

    private I moduleInput;

    @Override
    public void input(I input) {
        this.moduleInput = input;
    }

    protected final I getModuleInput() {
        return moduleInput;
    }

    @SuppressWarnings("unchecked")
    protected final void injectComponent(Context context, Class<COMPONENT> clazz) {
        COMPONENT component = (COMPONENT) ((HasComponent<?>) context).getComponent(clazz.getSimpleName());
        inject(component);
    }

    @Override
    public void start() {
        getPresenter().onStart();
    }

    @Override
    public void stop() {
        getPresenter().onStop();
    }

    protected abstract BaseLifecyclePresenter<? extends BaseView, ? extends BaseRouter> getPresenter();

    @Override
    public void destroy(boolean retainInstance) {
        getPresenter().detachView(retainInstance);
        getPresenter().removeRouter();
        moduleInput.clear();
        moduleInput = null;
    }
}
