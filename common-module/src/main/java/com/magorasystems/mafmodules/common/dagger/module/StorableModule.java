package com.magorasystems.mafmodules.common.dagger.module;

import android.content.Context;

import com.magorasystems.mafmodules.network.store.SimpleMemoryTokenStorable;
import com.magorasystems.mafmodules.network.store.StringApiTokenStorage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger LOGGER = LoggerFactory.getLogger(StorableModule.class);

    @Provides
    @Singleton
    public StringApiTokenStorage providerMemoryTokenStorable(Context context) {
        LOGGER.debug("create new instance for StringApiTokenStorage");
        return new SimpleMemoryTokenStorable();
    }
}
