package com.magorasystems.mafmodules.module;

import android.content.Context;

import com.magorasystems.android.module.social.module.AbstractSocialPresenterModule;
import com.magorasystems.android.module.social.module.SimpleSocialModuleInput;
import com.magorasystems.android.module.social.module.SimpleSocialPresenterModule;
import com.magorasystems.mafmodules.authmodule.module.outpit.AuthViewOutput;
import com.magorasystems.mafmodules.authmodule.view.impl.StringAuthView;
import com.magorasystems.mafmodules.dagger.component.SocialComponent;
import com.magorasystems.android.module.social.module.input.SimpleSocialViewInput;
import com.magorasystems.android.module.social.module.input.SocialInteractiveView;
import com.magorasystems.android.module.social.presenter.SimpleSocialPresenter;
import com.magorasystems.android.module.social.router.SocialRouter;
import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SimpleSocialPresenterModuleImpl extends AbstractSocialPresenterModule<SocialComponent, String, StringAuthInfo,
        SocialRouter<String>, SimpleSocialViewInput,
        SimpleSocialModuleInput, AuthViewOutput> implements SimpleSocialPresenterModule {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleSocialPresenterModuleImpl.class);


    private SimpleSocialPresenter presenter;

    public SimpleSocialPresenterModuleImpl(Context context, SimpleSocialPresenter presenter) {
        this.presenter = presenter;
        injectComponent(context, SocialComponent.class);
    }

    @Override
    public void input(SimpleSocialModuleInput input) {
        super.input(input);
        getPresenter().attachView(getPassiveView());
        getPresenter().setRouter(getRouter());
    }

    @Override
    public void start() {
        super.start();
        final SocialInteractiveView interactiveView = getInteractiveView();
        if (interactiveView != null) {
            interactiveView.model()
                    .subscribe(getPresenter()::authorizationBySocial);
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
    protected SimpleSocialPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void inject(SocialComponent socialComponent) {
        socialComponent.inject(this);
    }

    @Override
    protected StringAuthView getPassiveView() {
        final Object obj = super.getPassiveView();
        if (obj != null) {
            return (StringAuthView) obj;
        }
        return null;
    }

    @Override
    protected SocialInteractiveView getInteractiveView() {
        final Object obj = super.getInteractiveView();
        if (obj != null) {
            return (SocialInteractiveView) obj;
        }
        return null;
    }
}
