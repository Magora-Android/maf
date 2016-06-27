package com.magorasystems.mafmodules.dagger.module;

import android.content.Context;

import com.magorasystems.mafmodule.security.network.RefreshTokenApiClient;
import com.magorasystems.mafmodules.common.dagger.module.BaseModule;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.dagger.scope.ProfileScope;
import com.magorasystems.mafmodules.network.UserProfileApiClientWrapper;
import com.magorasystems.mafmodules.network.store.StringApiTokenStorage;
import com.magorasystems.mafmodules.provider.SimpleRestProfileProvider;
import com.magorasystems.mafmodules.provider.impl.SimpleRestProfileProviderImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@Module
public class ProfileProviderModule implements BaseModule {

    @Provides
    @ProfileScope
    protected SimpleRestProfileProvider providerProfileDataProvider(Context application,
                                                                    SchedulersUtils.CoreScheduler scheduler,
                                                                    UserProfileApiClientWrapper wrapper,
                                                                    RefreshTokenApiClient refreshTokenApiClient,
                                                                    StringApiTokenStorage tokenStorable) {
        return new SimpleRestProfileProviderImpl(application, scheduler, wrapper, refreshTokenApiClient, tokenStorable);
    }
}
