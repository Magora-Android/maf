package com.magorasystems.mafmodules.profile.store;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.magorasystems.mafmodules.common.utils.store.PreferencesStorable;

import org.apache.commons.lang3.StringUtils;

import me.henrytao.rxsharedpreferences.RxSharedPreferences;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class PreferencesProfileStorable<ID, P> extends PreferencesStorable<ID, P> {

    private static final String PREFIX = "profile_";

    protected final Gson gson;
    private final Class<P> clazz;
    protected boolean isDebug;

    public PreferencesProfileStorable(RxSharedPreferences preferences, Gson gson, Class<P> clazz, boolean isDebug) {
        super(preferences);
        this.gson = gson;
        this.clazz = clazz;
        this.isDebug = isDebug;
    }

    @Override
    public P restoreObject(ID key) {
        final String keyValue = getKeyValue(key);
        final String jsonValue = rxSharedPreferences.getString(keyValue, null);
        if (StringUtils.isBlank(jsonValue)) {
            //object not stored yet.
            return null;
        }
        return fromJson(jsonValue);
    }

    @Override
    public void storeObject(ID key, P object) {
        final String keyValue = getKeyValue(key);
        if (object == null) {
            //object is null
            rxSharedPreferences.putString(keyValue, null);
            return;
        }
        rxSharedPreferences.putString(keyValue, toJson(object));
    }

    @Override
    public void remove(ID key) {
        rxSharedPreferences.putString(getKeyValue(key), null);
    }

    protected String getKeyValue(ID key) {
      return PREFIX + String.valueOf(key);
    }

    protected final P fromJson(String jsonValue) {
        if (gson == null) {
            return null;
        }
        try {
            return gson.fromJson(jsonValue, clazz);
        } catch (JsonSyntaxException e) {
            if (isDebug) {
                e.printStackTrace();
            }
            return null;
        }
    }

    protected final String toJson(P object) {
        if (object == null) {
            return null;
        }
        return gson.toJson(object);
    }
}
