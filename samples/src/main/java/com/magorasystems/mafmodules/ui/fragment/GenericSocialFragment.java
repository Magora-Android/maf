package com.magorasystems.mafmodules.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.magorasystems.mafmodules.authmodule.view.AuthLceView;
import com.magorasystems.mafmodules.common.module.base.ModuleInput;
import com.magorasystems.mafmodules.common.module.output.ViewOutput;
import com.magorasystems.mafmodules.common.ui.fragment.GenericModuleFragment;
import com.magorasystems.mafmodules.module.SocialPresenterModule;
import com.magorasystems.mafmodules.module.input.SocialInteractiveView;
import com.magorasystems.mafmodules.module.input.SocialViewInput;
import com.magorasystems.mafmodules.router.SocialRouter;
import com.magorasystems.protocolapi.auth.dto.response.AuthInfo;

import java.io.Serializable;

import javax.inject.Inject;

import rx.Observable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class GenericSocialFragment<COMPONENT, ID extends Serializable,
        M extends AuthInfo<ID>,
        V extends AuthLceView<ID, M>,
        VI extends SocialViewInput<ID, M, V, SocialInteractiveView>,
        MODULE extends SocialPresenterModule<ID, M, SocialRouter<ID>, VI,
                ? extends ModuleInput<VI, SocialRouter<ID>>, ? extends ViewOutput<M>>>
        extends GenericModuleFragment<COMPONENT> implements SocialRouter<ID> {

    @Inject
    protected Observable<MODULE> moduleObservable;

    protected abstract void startModule(MODULE module);

    protected abstract AuthLceView<ID, M> getPassiveView();

    protected abstract SocialInteractiveView getInteractiveView();

    private SocialPresenterModule<ID, M, SocialRouter<ID>, VI,
            ? extends ModuleInput<VI, SocialRouter<ID>>, ? extends ViewOutput<M>> modulePresenter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialization();
    }

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
    protected SocialPresenterModule<ID, M, SocialRouter<ID>, VI,
            ? extends ModuleInput<VI, SocialRouter<ID>>, ? extends ViewOutput<M>> getModulePresenter() {
        return modulePresenter;
    }


    @Override
    public String getTitle() {
        return "";
    }

    @Override
    public void detachView() {
        getPassiveView().detachView();
        getInteractiveView().destroy();
    }
}
