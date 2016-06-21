package com.magorasystems.mafmodules.authmodule.module.base;

import android.content.Context;

import com.magorasystems.mafmodules.authmodule.module.impl.AbstractModuleInput;
import com.magorasystems.mafmodules.authmodule.router.AuthRouter;
import com.magorasystems.mafmodules.authmodule.view.input.StringAuthViewInput;
import com.magorasystems.mafmodules.common.mvp.presenter.BasePresenter;
import com.magorasystems.mafmodules.common.mvp.router.BaseRouter;
import com.magorasystems.mafmodules.common.mvp.view.BaseView;
import com.magorasystems.mafmodules.common.utils.component.HasComponent;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class AbstractModulePresenter<COMPONENT, I extends AbstractModuleInput<StringAuthViewInput, AuthRouter>> implements ModulePresenter<COMPONENT, I> {

    private I moduleInput;

    @Override
    public void input(I input) {
        this.moduleInput = input;
    }

    protected final I getModuleInput() {
        return moduleInput;
    }

    protected final void injectComponent(Context context, Class<COMPONENT> clazz) {
        COMPONENT component = (COMPONENT) ((HasComponent<?>) context).getComponent(clazz.getSimpleName());
        inject(component);
    }

    @Override
    public void stop() {
        getPresenter().detachView(false);
        getPresenter().removeRouter();
    }

    protected abstract BasePresenter<? extends BaseView, ? extends BaseRouter> getPresenter();
}
