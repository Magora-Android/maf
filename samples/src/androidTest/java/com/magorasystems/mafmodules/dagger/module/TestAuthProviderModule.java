package com.magorasystems.mafmodules.dagger.module;

import android.content.Context;

import com.magorasystems.mafmodules.application.SampleApplication;
import com.magorasystems.mafmodules.authmodule.provider.impl.SimpleAuthProvider;
import com.magorasystems.mafmodules.common.dagger.module.BaseModule;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.network.AuthApiClientWrapper;
import com.magorasystems.mafmodules.network.provider.AuthRestProvider;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@Module
public class TestAuthProviderModule implements BaseModule {

    @Provides
    @Named(QUALIFIER_COMBAT)
    public SimpleAuthProvider providerAuthRestProvider(Context application,
                                                       SchedulersUtils.CoreScheduler scheduler,
                                                       AuthApiClientWrapper clientWrapper) {
        return new AuthRestProvider(SampleApplication.get(application), scheduler, clientWrapper);
    }

    @Provides
    @Named(QUALIFIER_MOCK)
    protected SimpleAuthProvider providerMockAuthRestProvider(Context application,
                                                              SchedulersUtils.CoreScheduler scheduler,
                                                              @Named(QUALIFIER_MOCK) AuthApiClientWrapper clientWrapper) {
        return new AuthRestProvider(SampleApplication.get(application), scheduler, clientWrapper);
    }
}
