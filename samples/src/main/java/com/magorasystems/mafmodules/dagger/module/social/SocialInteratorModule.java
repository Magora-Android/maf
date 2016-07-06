package com.magorasystems.mafmodules.dagger.module.social;

import com.magorasystems.mafmodules.common.dagger.module.BaseModule;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.interactor.SimpleSocialInteractor;
import com.magorasystems.mafmodules.interactor.impl.SimpleSocialInteractorImpl;
import com.magorasystems.mafmodules.provider.social.SimpleSocialProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@Module
public class SocialInteratorModule implements BaseModule {

    @Provides
    protected SimpleSocialInteractor providerSocialInterator(SchedulersUtils.CoreScheduler scheduler,
                                                             SimpleSocialProvider provider) {
        return new SimpleSocialInteractorImpl(scheduler, provider);
    }
}
