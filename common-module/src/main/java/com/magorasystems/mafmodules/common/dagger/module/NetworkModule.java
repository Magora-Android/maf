package com.magorasystems.mafmodules.common.dagger.module;

import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.magorasystems.mafmodules.common.application.ApplicationSettings;
import com.magorasystems.mafmodules.network.client.LoggerOkHttpClientFactory;
import com.magorasystems.mafmodules.network.config.ServerEndpoint;
import com.magorasystems.mafmodules.network.config.SimpleServerEndpoint;
import com.magorasystems.mafmodules.network.config.SimpleTokenConfig;
import com.magorasystems.mafmodules.network.interceptor.HeaderInterceptor;
import com.magorasystems.mafmodules.network.manager.NetworkConnectionManager;
import com.magorasystems.mafmodules.network.store.StringApiTokenStorage;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@Module
public class NetworkModule implements BaseModule {

    @Provides
    @Singleton
    public NetworkConnectionManager providerNetworkConnectionManager(Context application, ApplicationSettings settings) {
        return NetworkConnectionManager.builder()
                .context(application)
                .count(settings.getNetworkRetryCount())
                .delay(settings.getNetworkDelayAttempt())
                .build();
    }

    @Provides
    @Singleton
    public Cache providerOkHttpCache(Context application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    public Gson providerGson() {
        GsonBuilder gsonBuilder = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    public HeaderInterceptor providerHeaderInterceptor(StringApiTokenStorage tokenStorable) {
        return new HeaderInterceptor(SimpleTokenConfig.HEADER_FIELD_NAME, tokenStorable);
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(Cache cache, HeaderInterceptor headerInterceptor, ApplicationSettings settings) {
        return LoggerOkHttpClientFactory.builder()
                .registerInterceptor(headerInterceptor)
                .isDebug(true)
                .maxRequestsCount(settings.getMaxRequestCount())
                .cache(cache)
                .build()
                .create();
    }

    @Provides
    @Singleton
    public ServerEndpoint providerServerEndpoint(ApplicationSettings settings) {
        return new SimpleServerEndpoint.Builder()
                .host(settings.getApiHost())
                .path(settings.getApiPath())
                .version(settings.getApiVersion())
                .build();
    }
}
