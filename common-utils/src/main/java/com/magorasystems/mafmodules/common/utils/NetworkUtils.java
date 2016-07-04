package com.magorasystems.mafmodules.common.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Proud to Code for Magora Systems/magora-systems.com/magora-systems.ru
 *
 * @author Valentin S. Bolkonsky
 */
public class NetworkUtils {

    private NetworkUtils() {

    }

    /**
     * Check network connection. Needed permission {@link android.Manifest.permission#ACCESS_NETWORK_STATE}
     *
     * @param context for access to {@link Context#getSystemService(Class)}
     * @return <b>true</b> if default network is currently active
     */
    public static boolean isNetworkAvailable(Context context) {
        final ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return null != activeNetwork;
    }
}
