package com.magorasystems.mafmodules.dagger.module;

import com.magorasystems.mafmodules.common.dagger.module.BaseModule;
import com.magorasystems.mafmodules.network.store.SimpleMemoryTokenStorable;

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
    protected SimpleMemoryTokenStorable providerMemoryTokenStorable() {
        return new SimpleMemoryTokenStorable();
    }
}
