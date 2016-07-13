package com.magorasystems.mafmodules.module;

import android.content.Context;

import com.magorasystems.mafmodules.common.module.input.InteractiveView;
import com.magorasystems.mafmodules.common.mvp.view.BaseView;
import com.magorasystems.mafmodules.dagger.component.SampleComponent;
import com.magorasystems.mafmodules.module.input.SimpleRegistrationViewInput;
import com.magorasystems.mafmodules.module.output.SimpleRegistrationViewOutput;
import com.magorasystems.mafmodules.presenter.SimpleRegistrationPresenter;
import com.magorasystems.mafmodules.router.SimpleRegistrationRouter;
import com.magorasystems.mafmodules.view.SimpleRegistrationInteractiveView;
import com.magorasystems.mafmodules.view.impl.SimpleRegistrationLceView;
import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SimpleRegistrationPresenterModuleImpl extends AbstractRegistrationPresenterModule<SampleComponent, StringAuthInfo,
        SimpleRegistrationRouter, SimpleRegistrationViewInput,
        SimpleRegistrationViewOutput, SimpleRegistrationModuleInput> implements SimpleRegistrationPresenterModule {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleSocialPresenterModuleImpl.class);


    private SimpleRegistrationPresenter presenter;

    public SimpleRegistrationPresenterModuleImpl(Context context, SimpleRegistrationPresenter presenter) {
        this.presenter = presenter;
        injectComponent(context, SampleComponent.class);
    }

    @Override
    public void input(SimpleRegistrationModuleInput input) {
        super.input(input);
        getPresenter().attachView(getPassiveView());
        getPresenter().setRouter(getRouter());
    }

    @Override
    public void start() {
        super.start();
        final SimpleRegistrationInteractiveView interactiveView = getInteractiveView();
        if (interactiveView != null) {
            interactiveView.model()
                    .subscribe(getPresenter()::registration);
            interactiveView.validation()
                    .subscribe();
            interactiveView.actionGoToAuthorization()
                    .subscribe(getPresenter()::toAuthorization);
        }
        output(getPresenter().output());
        super.start();
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        output(getPresenter().output());
    }

    @Override
    protected SimpleRegistrationPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void inject(SampleComponent sampleComponent) {
        sampleComponent.inject(this);
    }

    @Override
    protected SimpleRegistrationInteractiveView getInteractiveView() {
        InteractiveView<?> interactiveView = super.getInteractiveView();
        if (interactiveView != null) {
            if (interactiveView instanceof SimpleRegistrationInteractiveView) {
                return (SimpleRegistrationInteractiveView) interactiveView;
            }
        }
        return null;
    }

    @Override
    protected SimpleRegistrationLceView getPassiveView() {
        BaseView baseView = super.getPassiveView();
        if (baseView != null) {
            if (baseView instanceof SimpleRegistrationLceView) {
                return (SimpleRegistrationLceView) baseView;
            }
        }
        return null;
    }
}
