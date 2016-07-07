package com.magorasystems.mafmodule.security.store;

import com.google.gson.Gson;
import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;

import me.henrytao.rxsharedpreferences.RxSharedPreferences;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class AuthPreferencesStorage extends AbstractJsonPreferencesStorage<String, StringAuthInfo> {

    private static final String PREFIX = "_auth_";


    public AuthPreferencesStorage(RxSharedPreferences preferences, Gson gson, boolean isDebug) {
        super(preferences, gson, StringAuthInfo.class, isDebug);
    }

    @Override
    protected String getKeyValue(String key) {
        return PREFIX + key;
    }
}
