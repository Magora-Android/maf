package com.magorasystems.mafmodules.common.dagger.module;

import com.magorasystems.mafmodules.network.store.SimpleMemoryTokenStorable;
import com.magorasystems.mafmodules.network.store.StringApiTokenStorage;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@Module
public class StorableModule implements BaseModule {

    @Provides
    @Singleton
    public StringApiTokenStorage providerMemoryTokenStorable() {
        return new SimpleMemoryTokenStorable();
    }
}
