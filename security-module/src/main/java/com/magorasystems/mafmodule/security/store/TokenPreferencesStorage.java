package com.magorasystems.mafmodule.security.store;

import com.google.gson.Gson;
import com.magorasystems.mafmodules.network.config.TokenConfig;
import com.magorasystems.mafmodules.network.store.ApiTokenStorable;

import me.henrytao.rxsharedpreferences.RxSharedPreferences;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class TokenPreferencesStorage<T extends TokenConfig> extends AbstractJsonPreferencesStorage<String, T>
        implements ApiTokenStorable<T> {

    protected String defaultKey;

    protected TokenPreferencesStorage(RxSharedPreferences preferences, Gson gson, Class<T> clazz, String defaultKey, boolean isDebug) {
        super(preferences, gson, clazz, isDebug);
        this.defaultKey = defaultKey;
    }

    @Override
    protected String getKeyValue(String key) {
        return key;
    }

    @Override
    public void clear() {
        remove(defaultKey);
    }

    @Override
    public boolean updateAccessToken(String key, String accessToken) {
        T token = restoreObject(key);
        if (token == null) {
            return false;
        }
        token.setAccessToken(accessToken);
        storeObject(key, token);
        return true;
    }

    @Override
    public int size() {
        T obj = restoreObject(defaultKey);
        return obj != null ? 1 : 0;
    }
}
