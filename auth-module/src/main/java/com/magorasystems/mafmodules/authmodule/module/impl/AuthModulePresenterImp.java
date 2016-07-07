package com.magorasystems.mafmodules.authmodule.module.impl;

import android.content.Context;

import com.magorasystems.mafmodules.authmodule.dagger.component.AuthComponent;
import com.magorasystems.mafmodules.authmodule.module.input.AuthInteractiveView;
import com.magorasystems.mafmodules.authmodule.module.input.StringAuthViewInput;
import com.magorasystems.mafmodules.authmodule.module.outpit.AuthViewOutput;
import com.magorasystems.mafmodules.authmodule.presenter.SimpleAuthPresenter;
import com.magorasystems.mafmodules.authmodule.router.AuthRouter;
import com.magorasystems.mafmodule.security.store.AuthPreferencesStorage;
import com.magorasystems.mafmodules.authmodule.view.impl.StringAuthView;
import com.magorasystems.mafmodules.common.module.base.AbstractModulePresenter;
import com.magorasystems.mafmodules.common.utils.component.HasComponent;
import com.magorasystems.mafmodules.common.utils.component.Injectable;

import javax.inject.Inject;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class AuthModulePresenterImp extends AbstractModulePresenter<AuthRouter, StringAuthViewInput, AuthViewOutput, AuthModuleInput>
        implements AuthModulePresenter, Injectable<AuthComponent> {

    @Inject
    protected SimpleAuthPresenter presenter;

    @Inject
    protected AuthPreferencesStorage preferencesStorage;

    @Inject
    public AuthModulePresenterImp(Context context) {
        injectComponent(context, AuthComponent.class);
    }


    @Override
    public void inject(AuthComponent authComponent) {
        authComponent.inject(this);
    }


    @Override
    public void onError(Throwable e) {
        final AuthRouter router = getRouter();
        if (router != null) {
            router.onShowError(e);
        }
    }

    @Override
    public void onNext(AuthViewOutput authViewOutput) {
        preferencesStorage.storeObject("my", authViewOutput.getModel());
        final AuthRouter router = getRouter();
        if (router != null) {
            router.onAfterAuth();
        }
    }

    @Override
    public void input(AuthModuleInput input) {
        super.input(input);
        getPresenter().setRouter(getRouter());
        getPresenter().attachView(getPassiveView());
    }

    @Override
    public void start() {
        final AuthInteractiveView interactiveView = getInteractiveView();
        if (interactiveView != null) {
            subscription.add(interactiveView.model()
                    .subscribe(getPresenter()::authorization));
            subscription.add(interactiveView.passwordRecover()
                    .subscribe(getPresenter()::passwordRecover));
            subscription.add(interactiveView.validation()
                    .subscribe());
        }
        output(getPresenter().output());
        super.start();
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
    protected StringAuthView getPassiveView() {
        final Object obj = super.getPassiveView();
        if (obj == null) {
            return null;
        }
        return (StringAuthView) obj;
    }

    @Override
    protected AuthInteractiveView getInteractiveView() {
        final Object obj = super.getInteractiveView();
        if (obj != null) {
            return (AuthInteractiveView) obj;
        }
        return null;
    }

    protected final void injectComponent(Context context, Class<AuthComponent> clazz) {
        if (context instanceof HasComponent<?>) {
            final Object component = ((HasComponent<?>) context).getComponent(clazz.getSimpleName());
            if (component != null) {
                if (component instanceof AuthComponent) {
                    inject((AuthComponent) component);
                    return;
                }
            }
        }
        throw new IllegalArgumentException("Context has not implement \"" + clazz.getSimpleName() + "\" component");
    }
}
