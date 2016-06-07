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

    public static boolean isNetworkAvailable(Context context) {
        final ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return null != activeNetwork;
    }
}
