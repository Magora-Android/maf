package com.magorasystems.mafmodule.security.store;

import com.google.gson.Gson;
import com.magorasystems.mafmodules.network.config.SimpleTokenConfig;

import me.henrytao.rxsharedpreferences.RxSharedPreferences;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SimplePreferencesTokenStorage extends TokenPreferencesStorage<SimpleTokenConfig> {

    public SimplePreferencesTokenStorage(RxSharedPreferences preferences, Gson gson, String defaultKey, boolean isDebug) {
        super(preferences, gson, SimpleTokenConfig.class, defaultKey, isDebug);
    }
}
