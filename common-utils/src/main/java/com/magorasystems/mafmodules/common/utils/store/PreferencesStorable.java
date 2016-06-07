package com.magorasystems.mafmodules.common.utils.store;

import me.henrytao.rxsharedpreferences.RxSharedPreferences;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S. Bolkonsky
 */
public abstract class PreferencesStorable<K,T> implements Storable<K, T> {

    protected final RxSharedPreferences rxSharedPreferences;

    public PreferencesStorable(final RxSharedPreferences preferences) {
        this.rxSharedPreferences = preferences;
    }
}
