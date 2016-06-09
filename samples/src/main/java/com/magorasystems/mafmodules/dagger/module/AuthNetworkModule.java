package com.magorasystems.mafmodules.dagger.module;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.magorasystems.mafmodules.BuildConfig;
import com.magorasystems.mafmodules.common.dagger.module.BaseModule;
import com.magorasystems.mafmodules.network.AuthApiClient;
import com.magorasystems.mafmodules.network.AuthApiClientWrapper;
import com.magorasystems.mafmodules.network.AuthRestApiFactory;
import com.magorasystems.mafmodules.network.RestApiFactory;
import com.magorasystems.mafmodules.network.client.LoggerOkHttpClientFactory;
import com.magorasystems.mafmodules.network.config.ServerEndpoint;
import com.magorasystems.mafmodules.network.config.SimpleServerEndpoint;
import com.magorasystems.mafmodules.network.config.SimpleTokenConfig;
import com.magorasystems.mafmodules.network.interceptor.HeaderInterceptor;
import com.magorasystems.mafmodules.network.store.SimpleMemoryTokenStorable;
import com.magorasystems.mafmodules.network.store.StringApiTokenStorage;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@Module
public class AuthNetworkModule implements BaseModule {

    @Provides
    @Singleton
    protected Cache providerOkHttpCache(Context application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    protected Gson providerGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    protected HeaderInterceptor providerHeaderInterceptor(StringApiTokenStorage tokenStorable) {
        return new HeaderInterceptor(SimpleTokenConfig.HEADER_FIELD_NAME, tokenStorable);
    }

    @Provides
    @Singleton
    protected OkHttpClient provideOkHttpClient(Cache cache, HeaderInterceptor headerInterceptor) {
        return LoggerOkHttpClientFactory.builder()
                .registerInterceptor(headerInterceptor)
                .isDebug(true)
                .maxRequestsCount(BuildConfig.REQUESTS_COUNT)
                .cache(cache)
                .build()
                .create();
    }

    @Provides
    @Singleton
    protected ServerEndpoint providerServerEndpoint() {
        return new SimpleServerEndpoint.Builder()
                .host(BuildConfig.REST_API_HOST)
                .path(BuildConfig.REST_API_PATH)
                .version(BuildConfig.REST_API_VERSION)
                .build();
    }

    @Provides
    protected AuthApiClient providerAuthRestClient(ServerEndpoint serverEndpoint, Gson gson, OkHttpClient client) {
        return new RestApiFactory.Builder<>(AuthRestApiFactory.class)
                .client(client)
                .endpoint(serverEndpoint)
                .registerCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .registerConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create();
    }

    @Provides
    protected AuthApiClientWrapper providerAuthClientWrapper(AuthApiClient apiClient) {
        return new AuthApiClientWrapper(apiClient);
    }
}
