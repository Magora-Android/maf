package com.magorasystems.mafmodules.network.manager;

import android.content.Context;
import android.util.Log;

import com.magorasystems.mafmodules.common.utils.NetworkUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.inject.Inject;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S. Bolkonsky
 */
public class NetworkConnectionManager {

    private final Context context;
    private final int count;
    private final long delay;

    public static Builder builder() {
        return new Builder();
    }

    @Inject
    public NetworkConnectionManager(Context context) {
        this(builder().context(context)
                .count(2)
                .delay(2000L));
    }

    protected NetworkConnectionManager(Builder builder) {
        context = builder.context;
        count = builder.count;
        delay = builder.delay;
    }


    public boolean isActiveInternetConnection() {
        if (NetworkUtils.isNetworkAvailable(context)) {
            try {
                HttpURLConnection urlc = (HttpURLConnection) (new URL("http://www.google.com").openConnection());
                urlc.setRequestProperty("User-Agent", "Test");
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(1500);
                urlc.connect();
                return (urlc.getResponseCode() == 200);
            } catch (IOException e) {
                Log.e("network", "Error checking internet connection", e);
            }
        } else {
            Log.d("network", "No network available!");
        }
        return false;
    }

    public int getCount() {
        return count;
    }

    public long getDelay() {
        return delay;
    }


    /**
     * {@code NetworkConnectionManager} builder static inner class.
     */
    public static final class Builder {
        private Context context;
        private int count;
        private long delay;

        public Builder() {
        }

        /**
         * Sets the {@code context} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code context} to set
         * @return a reference to this Builder
         */
        public Builder context(Context val) {
            context = val;
            return this;
        }

        /**
         * Sets the {@code count} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code count} to set
         * @return a reference to this Builder
         */
        public Builder count(int val) {
            count = val;
            return this;
        }

        /**
         * Sets the {@code delay} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code delay} to set
         * @return a reference to this Builder
         */
        public Builder delay(long val) {
            delay = val;
            return this;
        }

        /**
         * Returns a {@code NetworkConnectionManager} built from the parameters previously set.
         *
         * @return a {@code NetworkConnectionManager} built with parameters of this {@code NetworkConnectionManager.Builder}
         */
        public NetworkConnectionManager build() {
            return new NetworkConnectionManager(this);
        }
    }
}
