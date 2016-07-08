package com.magorasystems.mafmodules.dagger.module.social;

import com.magorasystems.mafmodules.common.dagger.module.BaseModule;
import com.magorasystems.android.module.social.interactor.SimpleSocialInteractor;
import com.magorasystems.android.module.social.presenter.SimpleSocialPresenter;
import com.magorasystems.mafmodules.presenter.impl.SimpleSocialPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@Module
public class SocialPresenterModule implements BaseModule {

    @Provides
    protected SimpleSocialPresenter providerSocialPresenter(SimpleSocialInteractor simpleSocialInteractor) {
        return new SimpleSocialPresenterImpl(simpleSocialInteractor);
    }

}
