package com.magorasystems.mafmodules.dagger.module.social;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.magorasystems.mafmodule.security.store.AuthPreferencesStorage;
import com.magorasystems.mafmodule.security.store.SimplePreferencesTokenStorage;
import com.magorasystems.mafmodules.common.dagger.module.BaseModule;
import com.magorasystems.mafmodules.dagger.scope.SocialScope;
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
public class SocialStoreModule implements BaseModule {


    @Provides
    @SocialScope
    @Named(QUALIFIER_PREFERENCES)
    protected Gson providerPreferencesGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @SocialScope
    protected AuthPreferencesStorage providerAuthPreferencesStorage(RxSharedPreferences preferences,
                                                                    @Named(QUALIFIER_PREFERENCES) Gson gson) {
        return new AuthPreferencesStorage(preferences, gson, true);
    }

    @Provides
    @SocialScope
    protected SimplePreferencesTokenStorage providerPreferencesTokenStorage(RxSharedPreferences preferences,
                                                                            @Named(QUALIFIER_PREFERENCES) Gson gson) {
        return new SimplePreferencesTokenStorage(preferences, gson, SimpleTokenConfig.HEADER_FIELD_NAME, true);
    }
}
