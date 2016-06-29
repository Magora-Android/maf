package com.magorasystems.mafmodules.common.module.base;

import com.magorasystems.mafmodules.common.module.input.ViewInput;
import com.magorasystems.mafmodules.common.module.output.ViewOutput;
import com.magorasystems.mafmodules.common.mvp.presenter.BaseLifecyclePresenter;
import com.magorasystems.mafmodules.common.mvp.router.BaseRouter;
import com.magorasystems.mafmodules.common.mvp.view.BaseView;

import rx.subscriptions.CompositeSubscription;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class AbstractModulePresenter<R extends BaseRouter, VI extends ViewInput<?, ?>,
        VO extends ViewOutput<?>, I extends ModuleInput<VI, R>> implements ModulePresenter<R, VI, VO, I> {

    private I moduleInput;

    protected final CompositeSubscription subscription = new CompositeSubscription();

    @Override
    public void input(I input) {
        this.moduleInput = input;
    }

    protected final I getModuleInput() {
        return moduleInput;
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
        if (subscription.hasSubscriptions()) {
            subscription.unsubscribe();
            subscription.clear();
        }
    }
}
