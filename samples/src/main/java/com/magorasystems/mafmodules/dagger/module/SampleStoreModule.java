package com.magorasystems.mafmodules.dagger.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.magorasystems.mafmodule.security.store.AuthPreferencesStorage;
import com.magorasystems.mafmodule.security.store.SimplePreferencesTokenStorage;
import com.magorasystems.mafmodules.common.dagger.module.BaseModule;
import com.magorasystems.mafmodules.dagger.scope.ApplicationScope;
import com.magorasystems.mafmodules.network.config.SimpleTokenConfig;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import me.henrytao.rxsharedpreferences.RxSharedPreferences;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@Module
public class SampleStoreModule implements BaseModule {

    @Provides
    @ApplicationScope
    @Named(QUALIFIER_PREFERENCES)
    protected Gson providerPreferencesGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @ApplicationScope
    protected AuthPreferencesStorage providerAuthPreferencesStorage(RxSharedPreferences preferences,
                                                                    @Named(QUALIFIER_PREFERENCES) Gson gson) {
        return new AuthPreferencesStorage(preferences, gson, true);
    }

    @Provides
    @ApplicationScope
    protected SimplePreferencesTokenStorage providerPreferencesTokenStorage(RxSharedPreferences preferences,
                                                                            @Named(QUALIFIER_PREFERENCES) Gson gson) {
        return new SimplePreferencesTokenStorage(preferences, gson, SimpleTokenConfig.HEADER_FIELD_NAME, true);
    }
}
