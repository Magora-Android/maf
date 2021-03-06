package com.magorasystems.mafmodules.presenter.impl;

import com.magorasystems.android.module.social.presenter.SimpleSocialPresenter;
import com.magorasystems.mafmodules.authmodule.module.outpit.AuthViewOutput;
import com.magorasystems.mafmodules.authmodule.view.impl.StringAuthView;
import com.magorasystems.android.module.social.interactor.SimpleSocialInteractor;
import com.magorasystems.android.module.social.presenter.AbstractSocialPresenter;
import com.magorasystems.android.module.social.router.SocialRouter;
import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;

import javax.inject.Inject;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SimpleSocialPresenterImpl
        extends AbstractSocialPresenter<String, StringAuthInfo, SimpleSocialInteractor, StringAuthView, SocialRouter<String>, AuthViewOutput>
        implements SimpleSocialPresenter {

    @Inject
    public SimpleSocialPresenterImpl(SimpleSocialInteractor interactor) {
        super(interactor);
    }

    @Override
    public void onNext(StringAuthInfo stringAuthInfo) {
        super.onNext(stringAuthInfo);
        showContent();
    }

    @Override
    protected AuthViewOutput newEvent(StringAuthInfo model) {
        return new AuthViewOutput(model);
    }
}
