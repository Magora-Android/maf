package com.magorasystems.mafmodules.profile.store;

import com.google.gson.Gson;
import com.magorasystems.mafmodule.security.store.AbstractJsonPreferencesStorage;

import me.henrytao.rxsharedpreferences.RxSharedPreferences;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class PreferencesProfileStorable<ID, P> extends AbstractJsonPreferencesStorage<ID, P> {

    private static final String PREFIX = "profile_";

    @Override
    protected String getKeyValue(ID key) {
        return PREFIX + String.valueOf(key);
    }

    public PreferencesProfileStorable(RxSharedPreferences preferences, Gson gson, Class<P> clazz, boolean isDebug) {
        super(preferences, gson, clazz, isDebug);
    }
}
