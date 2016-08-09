package com.magorasystems.mafmodules.common.ui.fragment;

import com.magorasystems.mafmodules.common.module.base.ModuleInput;
import com.magorasystems.mafmodules.common.module.base.ModulePresenter;
import com.magorasystems.mafmodules.common.module.input.InteractiveView;
import com.magorasystems.mafmodules.common.module.input.ViewInput;
import com.magorasystems.mafmodules.common.module.output.ViewOutput;
import com.magorasystems.mafmodules.common.mvp.router.BaseRouter;
import com.magorasystems.mafmodules.common.mvp.view.BaseLceView;

import javax.inject.Inject;

import rx.Observable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class GenericSupportPresenterModuleFragment<COMPONENT,
        M, V extends BaseLceView<M>,
        VI extends ViewInput<V, ? extends InteractiveView<?>>,
        MODULE extends ModulePresenter<? extends BaseRouter, VI, ? extends ViewOutput<M>, ? extends ModuleInput<VI, ? extends BaseRouter>>>
        extends GenericModuleSupportFragment<COMPONENT> {


    @Inject
    protected Observable<MODULE> moduleObservable;

    private ModulePresenter<? extends BaseRouter, VI, ? extends ViewOutput<M>,
            ? extends ModuleInput<VI, ? extends BaseRouter>> modulePresenter;

    @Override
    protected ModulePresenter<? extends BaseRouter, ? extends ViewInput<?, ?>, ? extends ViewOutput<?>, ? extends ModuleInput<?, ?>> getModulePresenter() {
        return modulePresenter;
    }

    protected void setModulePresenter(ModulePresenter<? extends BaseRouter, VI, ? extends ViewOutput<M>,
            ? extends ModuleInput<VI, ? extends BaseRouter>> presenter) {
        this.modulePresenter = presenter;
    }

    protected abstract void startModule(MODULE module);

    protected abstract V getPassiveView();

    protected abstract InteractiveView<?> getInteractiveView();

    @Override
    public void onStart() {
        super.onStart();
        if (modulePresenter != null) {
            modulePresenter.start();
        } else {
            moduleObservable.subscribe(this::startModule);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (modulePresenter != null) {
            modulePresenter.stop();
            modulePresenter = null;
        }
    }

    @Override
    public void detachView() {
        getPassiveView().detachView();
        getInteractiveView().destroy();
    }

}
