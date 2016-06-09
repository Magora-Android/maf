package com.magorasystems.mafmodules.common.dagger.module;

import android.content.Context;

import com.magorasystems.mafmodules.common.BuildConfig;
import com.magorasystems.mafmodules.network.manager.NetworkConnectionManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@Module
public class NetworkModule implements BaseModule {

    @Provides
    @Singleton
    protected NetworkConnectionManager providerNetworkConnectionManager(Context application) {
        return NetworkConnectionManager.builder()
                .context(application)
                .count(BuildConfig.NETWORK_RETRY_COUNT)
                .delay(BuildConfig.NETWORK_DELAY_ATTEMPT)
                .build();
    }
}
