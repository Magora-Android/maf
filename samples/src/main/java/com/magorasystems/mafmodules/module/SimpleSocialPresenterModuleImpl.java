package com.magorasystems.mafmodules.module;

import android.content.Context;

import com.magorasystems.mafmodules.authmodule.module.outpit.AuthViewOutput;
import com.magorasystems.mafmodules.authmodule.view.impl.StringAuthView;
import com.magorasystems.mafmodules.dagger.component.SampleComponent;
import com.magorasystems.mafmodules.module.input.SimpleSocialViewInput;
import com.magorasystems.mafmodules.module.input.SocialInteractiveView;
import com.magorasystems.mafmodules.presenter.impl.SimpleSocialPresenter;
import com.magorasystems.mafmodules.router.SocialRouter;
import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;
import com.mgrmobi.sdk.social.base.SocialType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SimpleSocialPresenterModuleImpl extends AbstractSocialPresenterModule<SampleComponent, String, StringAuthInfo,
        SocialRouter<String>, SimpleSocialViewInput,
        SimpleSocialModuleInput, AuthViewOutput> implements SimpleSocialPresenterModule {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleSocialPresenterModuleImpl.class);

    private SimpleSocialPresenter presenter;

    public SimpleSocialPresenterModuleImpl(Context context, SimpleSocialPresenter presenter) {
        this.presenter = presenter;
        injectComponent(context, SampleComponent.class);
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
            interactiveView.model(SocialType.VK)
                    .subscribe(getPresenter()::authorizationBySocial);
        }

    }

    @Override
    protected SimpleSocialPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void inject(SampleComponent sampleComponent) {
        sampleComponent.inject(this);
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
