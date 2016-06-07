package com.magorasystems.mafmodules.network.client;

import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class LoggerOkHttpClientFactory {

    private final boolean isDebug;
    private final long connectTimeout;
    private final long readTimeout;
    private final long writeTimeout;
    private final int maxRequestsCount;
    private final Collection<Interceptor> interceptors;

    private LoggerOkHttpClientFactory(Builder builder) {
        isDebug = builder.isDebug;
        connectTimeout = builder.connectTimeout;
        readTimeout = builder.readTimeout;
        writeTimeout = builder.writeTimeout;
        maxRequestsCount = builder.maxRequestsCount;
        interceptors = builder.interceptors == null ? Lists.newArrayList() :
                Lists.newArrayList(builder.interceptors);
    }

    public OkHttpClient create() {
        final Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(maxRequestsCount);
        final OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .writeTimeout(writeTimeout, TimeUnit.SECONDS)
                .dispatcher(dispatcher);

        if (isDebug) {
            final HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(httpLoggingInterceptor);
        }
        if (interceptors != null) {
            for (Interceptor interceptor : interceptors) {
                builder.addInterceptor(interceptor);
            }
        }
        return builder.build();
    }

    public static Builder builder() {
        return new Builder();
    }


    /**
     * {@code LoggerOkHttpClientFactory} builder static inner class.
     */
    public static final class Builder {
        private boolean isDebug;
        private long connectTimeout;
        private long readTimeout;
        private long writeTimeout;
        private int maxRequestsCount;
        private Collection<Interceptor> interceptors;

        public Builder() {
            connectTimeout = 10_000;
            readTimeout = 10_000;
            writeTimeout = 10_000;
        }

        /**
         * Sets the {@code isDebug} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code isDebug} to set
         * @return a reference to this Builder
         */
        public Builder isDebug(boolean val) {
            isDebug = val;
            return this;
        }

        /**
         * Sets the {@code connectTimeout} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code connectTimeout} to set
         * @return a reference to this Builder
         */
        public Builder connectTimeout(long val) {
            connectTimeout = val;
            return this;
        }

        /**
         * Sets the {@code readTimeout} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code readTimeout} to set
         * @return a reference to this Builder
         */
        public Builder readTimeout(long val) {
            readTimeout = val;
            return this;
        }

        /**
         * Sets the {@code writeTimeout} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code writeTimeout} to set
         * @return a reference to this Builder
         */
        public Builder writeTimeout(long val) {
            writeTimeout = val;
            return this;
        }

        /**
         * Sets the {@code maxRequestsCount} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code maxRequestsCount} to set
         * @return a reference to this Builder
         */
        public Builder maxRequestsCount(int val) {
            maxRequestsCount = val;
            return this;
        }

        /**
         * Sets the {@code interceptors} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code interceptors} to set
         * @return a reference to this Builder
         */
        public Builder registerInterceptor(Interceptor val) {
            if (interceptors == null) {
                interceptors = Lists.newArrayList();
            }
            interceptors.add(val);
            return this;
        }

        /**
         * Returns a {@code LoggerOkHttpClientFactory} built from the parameters previously set.
         *
         * @return a {@code LoggerOkHttpClientFactory} built with parameters of this {@code LoggerOkHttpClientFactory.Builder}
         */
        public LoggerOkHttpClientFactory build() {
            return new LoggerOkHttpClientFactory(this);
        }
    }
}
