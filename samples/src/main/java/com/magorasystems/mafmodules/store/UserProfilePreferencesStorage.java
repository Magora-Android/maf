package com.magorasystems.mafmodules.store;

import com.google.gson.Gson;
import com.magorasystems.mafmodules.model.UserProfile;
import com.magorasystems.mafmodules.profile.store.PreferencesProfileStorable;

import me.henrytao.rxsharedpreferences.RxSharedPreferences;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class UserProfilePreferencesStorage extends PreferencesProfileStorable<String, UserProfile> {

    public UserProfilePreferencesStorage(RxSharedPreferences preferences, Gson gson) {
        super(preferences, gson, UserProfile.class, true);
    }
}
