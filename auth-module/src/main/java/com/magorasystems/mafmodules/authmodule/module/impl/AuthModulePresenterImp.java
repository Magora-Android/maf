package com.magorasystems.mafmodules.authmodule.module.impl;

import android.content.Context;

import com.magorasystems.mafmodules.authmodule.dagger.component.AuthComponent;
import com.magorasystems.mafmodules.authmodule.module.base.AbstractModulePresenter;
import com.magorasystems.mafmodules.authmodule.presenter.SimpleAuthPresenter;
import com.magorasystems.mafmodules.authmodule.view.input.AuthInteractiveView;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class AuthModulePresenterImp extends AbstractModulePresenter<AuthComponent, AuthModuleInput> implements AuthModulePresenter {

    @Inject
    protected SimpleAuthPresenter presenter;

    private final CompositeSubscription subscription = new CompositeSubscription();

    @Inject
    public AuthModulePresenterImp(Context context) {
        injectComponent(context, AuthComponent.class);
    }


    @Override
    public void inject(AuthComponent authComponent) {
        authComponent.inject(this);
    }

    @Override
    public void input(AuthModuleInput input) {
        super.input(input);
        getPresenter().setRouter(input.getRouter());
        getPresenter().attachView(input.getViewInput().getPassiveView());
    }

    @Override
    public void start() {
        super.start();
        final AuthInteractiveView interactiveView = getModuleInput().getViewInput()
                .getInteractiveView();
        if (interactiveView != null) {
            subscription.add(interactiveView.model()
                    .subscribe(getPresenter()::authorization));
            subscription.add(interactiveView.passwordRecover()
                    .subscribe(getPresenter()::passwordRecover));
            subscription.add(interactiveView.validation()
                    .subscribe());
        }
    }

    @Override
    public void stop() {
        super.stop();
    }

    @Override
    protected SimpleAuthPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void destroy(boolean retainInstance) {
        super.destroy(retainInstance);
        if (subscription.hasSubscriptions()) {
            subscription.unsubscribe();
            subscription.clear();
        }
    }
}
