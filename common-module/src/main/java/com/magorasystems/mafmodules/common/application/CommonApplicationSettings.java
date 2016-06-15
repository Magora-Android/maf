package com.magorasystems.mafmodules.common.application;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class CommonApplicationSettings implements ApplicationSettings {

    private final int maxRequestCount;
    private final String apiHost;
    private final String apiPath;
    private final String apiVersion;
    private final int networkRetryCount;
    private final long networkDelayAttempt;

    private CommonApplicationSettings(Builder builder) {
        maxRequestCount = builder.maxRequestCount;
        apiHost = builder.apiHost;
        apiPath = builder.apiPath;
        apiVersion = builder.apiVersion;
        networkRetryCount = builder.networkRetryCount;
        networkDelayAttempt = builder.networkDelayAttempt;
    }


    @Override
    public int getMaxRequestCount() {
        return maxRequestCount;
    }

    @Override
    public String getApiHost() {
        return apiHost;
    }

    @Override
    public String getApiPath() {
        return apiPath;
    }

    @Override
    public String getApiVersion() {
        return apiVersion;
    }

    @Override
    public int getNetworkRetryCount() {
        return networkRetryCount;
    }

    @Override
    public long getNetworkDelayAttempt() {
        return networkDelayAttempt;
    }


    /**
     * {@code CommonApplicationSettings} builder static inner class.
     */
    public static final class Builder {
        private int maxRequestCount;
        private String apiHost;
        private String apiPath;
        private String apiVersion;
        private int networkRetryCount;
        private long networkDelayAttempt;

        public Builder() {
            maxRequestCount = 1;
        }

        /**
         * Sets the {@code maxRequestCount} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code maxRequestCount} to set
         * @return a reference to this Builder
         */
        public Builder maxRequestCount(int val) {
            maxRequestCount = val;
            return this;
        }

        /**
         * Sets the {@code apiHost} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code apiHost} to set
         * @return a reference to this Builder
         */
        public Builder apiHost(String val) {
            apiHost = val;
            return this;
        }

        /**
         * Sets the {@code apiPath} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code apiPath} to set
         * @return a reference to this Builder
         */
        public Builder apiPath(String val) {
            apiPath = val;
            return this;
        }

        /**
         * Sets the {@code apiVersion} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code apiVersion} to set
         * @return a reference to this Builder
         */
        public Builder apiVersion(String val) {
            apiVersion = val;
            return this;
        }

        /**
         * Sets the {@code networkRetryCount} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code networkRetryCount} to set
         * @return a reference to this Builder
         */
        public Builder networkRetryCount(int val) {
            networkRetryCount = val;
            return this;
        }

        /**
         * Sets the {@code networkDelayAttempt} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code networkDelayAttempt} to set
         * @return a reference to this Builder
         */
        public Builder networkDelayAttempt(long val) {
            networkDelayAttempt = val;
            return this;
        }

        /**
         * Returns a {@code CommonApplicationSettings} built from the parameters previously set.
         *
         * @return a {@code CommonApplicationSettings} built with parameters of this {@code CommonApplicationSettings.Builder}
         */
        public CommonApplicationSettings build() {
            return new CommonApplicationSettings(this);
        }
    }
}
