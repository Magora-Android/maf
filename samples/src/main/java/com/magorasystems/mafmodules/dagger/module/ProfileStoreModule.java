package com.magorasystems.mafmodules.dagger.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.magorasystems.mafmodules.common.dagger.module.BaseModule;
import com.magorasystems.mafmodules.dagger.scope.ProfileScope;
import com.magorasystems.mafmodules.store.UserProfilePreferencesStorage;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import me.henrytao.rxsharedpreferences.RxSharedPreferences;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@Module
public class ProfileStoreModule implements BaseModule {

    public static final String PREFERENCES = "preferences";

    @Provides
    @ProfileScope
    @Named(PREFERENCES)
    protected Gson providerPreferencesGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @ProfileScope
    protected UserProfilePreferencesStorage providerPreferencesStorage(RxSharedPreferences preferences,
                                                                       @Named(PREFERENCES) Gson gson) {
        return new UserProfilePreferencesStorage(preferences, gson);
    }
}
