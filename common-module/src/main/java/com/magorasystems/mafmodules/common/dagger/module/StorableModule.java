package com.magorasystems.mafmodules.common.dagger.module;

import android.content.Context;
import android.content.SharedPreferences;

import com.magorasystems.mafmodules.network.store.SimpleMemoryTokenStorable;
import com.magorasystems.mafmodules.network.store.StringApiTokenStorage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.henrytao.rxsharedpreferences.RxSharedPreferences;

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

    @Provides
    @Singleton
    public RxSharedPreferences providerPreferenceStorage(Context context) {
        LOGGER.debug("create new instance for RxSharedPreferences");
        final SharedPreferences preferences = context.getSharedPreferences(context.getPackageName() + "PREFS", Context.MODE_PRIVATE);
        return new RxSharedPreferences(preferences);
    }
}
